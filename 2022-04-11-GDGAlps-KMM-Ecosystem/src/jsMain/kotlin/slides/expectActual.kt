package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

val expectActual = Slide(
    name = "expect_actual",
    stateCount = 7
) { state ->
    SubSlide(state == 0, Transitions.grow) {
        H2 {
            Text("How to handle platform specific behavior?")
        }
    }
    SubSlide(state in 1..4, Transitions.fade) {
        SourceCode(
            "kotlin",
            """
        «expect:// Kotlin common code (src/commonMain/kotlin)
        expect fun saveValueLocally(value: String)               
    
        »«android:// Kotlin for Android (src/androidMain/kotlin)
        «platform:import android.content.SharedPreferences»
        actual fun saveValueLocally(value: String) {
            val sharedPreferences = …
            sharedPreferences.edit { putString("MyString", value) }
        }
        
        »«ios:// Kotlin for iOS (src/iosMain/kotlin)
        «platform:import platform.Foundation.NSUserDefaults»
        actual fun saveValueLocally(value: String) {
            NSUserDefaults.standardUserDefaults
                    .setValue(value, forKey = "MyString")
        }»
        """.trimIndent()
        ) {
            "expect" { lineHeight(state >= 1) }
            "android" { lineHeight(state >= 2) }
            "ios" { lineHeight(state >= 3) }
            "platform" { zoomed(state == 4) }
        }

    }

    SubSlide(state in 5..6, Transitions.fade) {
        H2 {
            Span({ shownIf(state >= 6, Transitions.fontGrow) }) { Text("Is ") }
            Text("Kotlin Multiplatform ")
            Span({ hiddenIf(state >= 6, Transitions.fontGrow) }) { Text("isn't ") }
            Text("mature enough")
            Span({ hiddenIf(state >= 6, Transitions.fontGrow) }) { Text("!") }
            Span({ shownIf(state >= 6, Transitions.fontGrow) }) { Text("?") }
        }
    }
}