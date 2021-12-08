package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text


val conclusion = Slide(
    name = "conclusion",
    stateCount = 3
) { state ->
    H2 { Text("Make sure to...") }

    H4({ shownIf(state >= 1, fade) }) { Text("...use amazing existing tools :)") }
    H4({ shownIf(state >= 2, fade) }) { Text("...contribute back!!!") }
}