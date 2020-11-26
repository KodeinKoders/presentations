package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.Move
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.dom.b
import react.dom.br
import react.dom.li
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.*


fun PresentationBuilder.expectActual() = slide(
    stateCount = 5,
    outTransitions = Flip
) { (state, _) ->
    titledSlide("Platform specifics") {
            slideCode(state, "kotlin",
                """
                «l:1-«// Kotlin common code (src/commonMain/kotlin)
                expect fun saveValueLocally(value: String)»«l:2-«
                
                // Kotlin for Android (src/androidMain/kotlin)
                import «f:4«android.content.SharedPreferences»
                actual fun saveValueLocally(value: String) {
                    val sharedPreferences = …
                    sharedPreferences.edit { putString("MyString", value) }
                }»«l:3-«
                
                // Kotlin for iOS (src/iosMain/kotlin)
                import «f:4«platform.Foundation.NSUserDefaults»
                actual fun saveValueLocally(value: String) {
                    NSUserDefaults.standardUserDefaults
                            .setValue(value, forKey = "String")
                }»
                """.trimIndent()) {
                opacity = if (state >= 1) 1 else 0
                width = 100.pct
            }
    }
}
