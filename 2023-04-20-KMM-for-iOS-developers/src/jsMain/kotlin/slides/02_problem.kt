package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.animatedWith
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.cic.css
import utils.Stamp

val problem =
    listOf(
            Slide(name = "polyglot-dev") {
              H3 { Text("I am not an Android developer!") }
            },
            Slide(name = "dry", stateCount = 2) { state ->
              Div {
                Cap("D")
                Low("on't ", state)
                Cap("R")
                Low("epeat ", state)
                Cap("Y")
                Low("ourslef", state)
              }
            },
            Slide(name = "full-fledged-solutions", stateCount = 5) { state ->
              H3 { Text("There are plenty solutions for that!") }
              H4 {
                Ul {
                  Li({ shownIf(state > 0, Transitions.fade) }) { Text("Ionic") }
                  Li({ shownIf(state > 1, Transitions.fade) }) { Text("React Native") }
                  Li({ shownIf(state > 2, Transitions.fade) }) { Text("Flutter") }
                }
              }
              Stamp(state == 4) { Img(src = "img/troll.png") }
            },
            Slide(name = "kmp-ftw", stateCount = 2) { state ->
              H3 { Text("Kotlin Multiplatform for the win!") }
              Br()
              Img(src = "img/kotlin.svg") {
                css {
                  maxWidth(40.percent)
                  maxHeight(40.percent)
                }
              }
              Br()
              H4({ shownIf(state > 0, Transitions.fontGrow) }) {
                Text("Shared non-UI business logic.")
              }
            },
        )
        .animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))

@Composable private fun Cap(letter: String) = Span({ css { fontSize(3.em) } }) { Text(letter) }

@Composable
private fun Low(text: String, state: Int) =
    Span({
      css { fontSize(2.em) }
      shownIf(state > 0, transition = Transitions.fontGrow)
    }) {
      Text("$text ")
    }
