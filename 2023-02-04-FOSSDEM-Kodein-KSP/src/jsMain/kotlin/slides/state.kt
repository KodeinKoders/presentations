package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.theme.compose.pres.KodeinAttrs
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

val state = Slide(
    name = "is-it-live",
    config = { KodeinAttrs(workInProgress = true) },
    stateCount = 6
) { state ->
    H2 {
        Span({ shownIf(state == 0, Transitions.fontGrow) }) { Text("Is this live?") }
        Span({ shownIf(state > 0, Transitions.fontGrow) }) { Text("Soon!") }
    }
    H3({ shownIf(state > 1, Transitions.fontGrow) }) {
        Ul {
            Li({ shownIf(state > 1, Transitions.fade) }) { Text("using Tags") }
            Li({ shownIf(state > 2, Transitions.fade) }) { Text("managing modules") }
            Li({ shownIf(state > 3, Transitions.fade) }) { Text("handling/declaring scopes") }
            Li({ shownIf(state > 4, Transitions.fade) }) { Text("...") }
        }
    }
}