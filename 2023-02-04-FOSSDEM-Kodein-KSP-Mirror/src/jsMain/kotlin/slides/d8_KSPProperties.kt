package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions.fade
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.pres.errorUnder
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val d8_KSPProperties = listOf(
    Slide(
        name = "KSP-Bad",
        stateCount = 3
    ) { state ->
        SourceCode("kotlin", """
            class MyTest : TestsWithMocks() {
              override fun setUpMocks() = «b:injectMocks»(mocker)

              /*...*/
            }
        """.trimIndent()) {
            "b" {
                attrs {
                    css {
                        transitions { "color" { duration = 0.5.s } }
                    }
                    style {
                        if (state == 1) {
                            color(Color.red)
                        }
                    }
                }
            }
        }
        Pre({
            shownIf(state >= 2, fade)
            css {
                fontSize(1.5.em)
            }
        }) { Text("./gradlew kspCommonMainKotlinMetadata") }
    },
    Slide(
        name = "KSP-Good",
        stateCount = 4
    ) { state ->
        H3 {
            Text("Why KSP?")
            Br()
            Small({
                css {
                    fontSize(1.cssRem)
                }
            }) { Text(" (As opposed to a Kotlin Compiler plugin.)") }
        }

        Ul({
            css {
                "li" {
                    padding(0.5.em, 0.em)
                    "li" {
                        padding(0.em)
                    }
                }
            }
        }) {
            Li({ shownIf(state >= 1, fade) }) {
                Text("\"Stable\" API")
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("Kotlin compiler plugin:")
                Ul {
                    Li { Text("Compiler integration") }
                    Li { Text("Gradle integration") }
                }
            }
            Li({ shownIf(state >= 3, fade) }) {
                Text("Simplicity for generator use-cases")
            }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
