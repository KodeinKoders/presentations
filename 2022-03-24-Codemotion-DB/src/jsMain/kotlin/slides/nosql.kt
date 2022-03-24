package slides

import net.kodein.pres.Slide
import net.kodein.pres.util.transition
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text


val nosql = Slide(
    name = "nosql",
    stateCount = 3
) { state ->
    H2 {
        Span({
            style {
                if (state >= 1) opacity(0.1)
                transition { "opacity"(300.ms) }
            }
        }) {
            Text("Embedded typed ")
        }
        Span({
            style {
                if (state >= 2) opacity(0.1)
                transition { "opacity"(300.ms) }
            }
        }) {
            Text("No")
        }
        Text("SQL")
        Span({
            style {
                if (state >= 1) opacity(0.1)
                transition { "opacity"(300.ms) }
            }
        }) {
            Text(" data persistence, everywhere!")
        }
    }
}