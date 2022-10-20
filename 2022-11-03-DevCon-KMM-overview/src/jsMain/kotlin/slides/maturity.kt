package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css

val maturity = Slide(
    name = "is_kmm_mature",
    stateCount = 2
) { state ->
    H3 {
        Span({ shownIf(state >= 1, Transitions.fontGrow) }) { Text("Is") }
        Br {  }
        Span({
            css {
                opacity(if (state == 0) 1 else 0.8)
            }
        }) {
            Text("Kotlin Multiplatform Mobile")
            Span({ hiddenIf(state >= 1, Transitions.fontGrow) }) { Text(" is in ") }
            Text(" Beta")
        }
        Br {  }
        Span({ hiddenIf(state >= 1, Transitions.fontGrow) }) { Text("It isn't ") }
        Text("mature enough")
        Span({ hiddenIf(state >= 1, Transitions.fontGrow) }) { Text("!") }
        Span({ shownIf(state >= 1, Transitions.fontGrow) }) { Text("?") }
    }
}