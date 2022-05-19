package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val stage2_1 = listOf(
    Slide(
        name = "stage2_1-jni",
        stateCount = 6
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 2.1:")
            }
            Text(" JNI")
        }

        Ul({
            css {
                fontSize(1.5.em)

                "li" {
                    paddingBottom(0.6.em)
                }
            }
        }) {
            Li({ shownIf(state >= 1, fade) }) {
                Text("Write ")
                B { Text("Kotlin facade") }
                Text(" ")
                Text(Emoji.cry)
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("Generate ")
                B { Text("headers") }
                Text(" ")
                Text(Emoji.shrug)
            }
            Li({ shownIf(state >= 3, fade) }) {
                Text("Write ")
                B { Text("C++ implementation") }
                Text(" ")
                Text(Emoji.sob)
            }
            Li({ shownIf(state >= 4, fade) }) {
                Text("Write ")
                B { Text("CMakefile") }
                Text(" ")
                Text(Emoji.fearful)
            }
            Li({ shownIf(state >= 5, fade) }) {
                Text("Configure ")
                B { Text("Gradle") }
                Text(" ")
                Text(Emoji.persevere)
            }
        }
    },

    Slide(
        name = "stage2_1-code",
        stateCount = 2
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 2.1:")
            }
            Br()
            Text("JNI")
        }

        H1({ shownIf(state >= 1, grow) }) { Text(Emoji.stars) }

    }
)