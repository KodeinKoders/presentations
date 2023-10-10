package slides

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.compose.html.css.css
import org.kodein.compose.html.pres.Animations
import org.kodein.compose.html.pres.Slide
import org.kodein.compose.html.pres.Slides
import org.kodein.compose.html.pres.Transitions
import org.kodein.compose.html.pres.shownIf
import utils.Stamp

val problem = Slides(Animations.Move(towards = Animations.Move.Towards.Bottom)) {
        +Slide(name = "polyglot-dev") {
            H2 { Text("Disclaimer:")   }
            H4 { Text("I am not an Android developer!") }
        }

        +Slide(name = "dry", stateCount = 2) { state ->
            Div {
                Cap("D")
                Low("on't ", state)
                Cap("R")
                Low("epeat ", state)
                Cap("Y")
                Low("ourslef", state)
            }
        }

        +Slide(name = "full-fledged-solutions", stateCount = 4) { state ->
            H3 { Text("There are plenty solutions for that!") }
            H4 {
                Ul {
                    Li({ shownIf(state > 0, Transitions.Fade()) }) { Text("Ionic") }
                    Li({ shownIf(state > 1, Transitions.Fade()) }) { Text("React Native") }
                    Li({ shownIf(state > 2, Transitions.Fade()) }) { Text("Flutter") }
                }
            }
        }

        +Slide(name = "kmp-ftw", stateCount = 2) { state ->
            H3 { Text("Kotlin Multiplatform for the win!") }
            Br()
            Img(src = "img/kotlin.svg") {
                css {
                    maxWidth(40.percent)
                    maxHeight(40.percent)
                }
            }
            Br()
            H4({ shownIf(state > 0, Transitions.FontGrow()) }) {
                Text("Shared non-UI business logic.")
            }
        }
}

@Composable private fun Cap(letter: String) = Span({ css { fontSize(3.em) } }) { Text(letter) }

@Composable
private fun Low(text: String, state: Int) =
    Span({
      css { fontSize(2.em) }
      shownIf(state > 0, transition = Transitions.FontGrow())
    }) {
      Text("$text ")
    }
