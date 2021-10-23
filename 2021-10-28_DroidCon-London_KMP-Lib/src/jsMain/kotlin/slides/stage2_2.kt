package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.util.d
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


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

                d("li") {
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
                B { Text("Gradle") }
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

        H1({ shownIf(state >= 1, Transitions.grow) }) { Text(Emoji.stars) }

    }
)