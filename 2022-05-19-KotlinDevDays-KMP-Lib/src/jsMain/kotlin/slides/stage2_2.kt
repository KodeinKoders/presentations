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


val stage2_2 = listOf(
    Slide(
        name = "stage2_2-c-interop",
        stateCount = 4
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 2.1:")
            }
            Text(" C-Interop")
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
                B { Text("C-Interop definition file") }
                Text(" ")
                Text(Emoji.sweat_smile)
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("Configure ")
                B { Text("Gradle KMP C-Interop") }
                Text(" ")
                Text(Emoji.persevere)
            }
            Li({ shownIf(state >= 3, fade) }) {
                Text("Write ")
                B { Text("Kotlin facade") }
                Text(" ")
                Text(Emoji.star_struck)
            }
        }
    },

    Slide(
        name = "stage2_2-code",
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
            Text("C-Interop")
        }

        H1({ shownIf(state >= 1, grow) }) { Text(Emoji.stars) }

    }
)