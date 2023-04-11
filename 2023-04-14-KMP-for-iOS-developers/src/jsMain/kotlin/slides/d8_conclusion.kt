package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css
import utils.JetBrains

val d8_conclusion = Slide(
    name = "conclusion",
    stateCount = 3
) { state ->
    Img(
        src = "img/illus/training_1920.webp"
    ) {
        css {
            maxWidth(125.percent)
            maxHeight(125.percent)
            transform { scale(1.2) }
        }
    }
    JetBrains({ shownIf(state >= 1, Transitions.stamp) }) {
        Text("Please tell iOS developers")
        Br()
        Span({
            css {
                fontSize(1.2.em)
                fontWeight(700)
            }
        }) {
            Text("we care about them")
        }
        Br()
        Br()
        Span({
            shownIf(state >= 2, fade)
            css { fontSize(0.75.em) }
        }) {
            Text("-- Anastasiia Kapanina (Product manager for Kotlin)")
        }
    }
}
