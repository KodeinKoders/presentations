package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.Swipe
import org.kodein.kpres.sourceCode
import org.kodein.kpres.subSlide
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.*
import ws.utils.opacity

fun PresentationBuilder.log() = slide(
    stateCount = 7
) { props ->
    titledSlide("Unified logging system") {
        subSlide(0..5, props.state, Swipe) {
            styledDiv {
                css {
                    width = 100.pct
                }

                slideCode(props.state, "kotlin", """
                    val loggerFactory = LoggerFactory(defaultLogFrontend«s:5-«, LogMemory»)
                """.trimIndent()) {
                    width = 100.pct
                    margin(vertical = 0.5.em)
                    opacity(props.state >= 1)
                }

                slideCode(props.state,"kotlin", """
                    val logger = loggerFactory.newLogger<MyController>()
                """.trimIndent()) {
                    width = 100.pct
                    margin(vertical = 0.5.em)
                    opacity(props.state >= 2)
                }

                slideCode(props.state, "kotlin", """
                    log.warning { "Something weird is going on!" }
                """.trimIndent()) {
                    width = 100.pct
                    margin(vertical = 0.5.em)
                    opacity(props.state >= 3)
                }
            }

            styledH3 {
                css {
                    fontWeight = FontWeight.lighter
                    color = Color.kodein.cute
                    marginTop = 1.em
                    opacity(props.state >= 4)
                }
                +"Logcat, Os_log, SLF4J, Println"
                styledSpan {
                    fontSize(props.state >= 5)
                    +", File"
                }
            }

        }

        subSlide(6..6, props.state, Swipe) {
            logo(
                "Log",
                logoColor = "orange",
                fontColor = Color.kodein.orange
            ) {
                +"Painless multiplatform logging"
            }
        }
    }
}
