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
) { props ->
    val (state, _) = props
    titledSlide("Platform specifics") {
        styledDiv {
            css {
                opacity = if (state >= 1) 1 else 0
            }

            kotlinSourceCode("""
                «common«// Kotlin common code (src/commonMain/kotlin)
                expect fun deviceIdentification() : String»«android«
                
                // Kotlin for Android (src/«platform-ul«androidMain»/kotlin)
                import «platform«java.util.*»
                actual fun deviceIdentification() = «platform«UUID».randomUUID().toString()»«ios«
                
                // Kotlin for iOS (src/«platform-ul«iosMain»/kotlin)
                import «platform«platform.Foundation.NSUUID»
                actual fun deviceIdentification() = «platform«NSUUID».UUID().UUIDString»
                """.trimIndent()) {
                +"c-common" { blockEffectFrom(state, 1) }
                +"c-android" { blockEffectFrom(state, 2) }
                +"c-ios" { blockEffectFrom(state, 3) }
                +"c-platform" { highlightOn(state, 4, Color.kodein.orange) }
                +"c-platform-ul" { if (state ==4) {
                    color = Color.grey
                    textDecoration = TextDecoration(setOf(TextDecorationLine.underline))
                } }
            }
        }
    }
}
