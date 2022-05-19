package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val stage0 = Slide(
    name = "stage0-code",
    stateCount = 2
) { state ->
    H2 {
        Span({
            css {
                fontWeight(200)
            }
        }) {
            Text("Stage 0:")
        }
        Br()
        Text("Create a KMM Library project")
    }

    H1({ shownIf(state >= 1, grow) }) { Text(Emoji.stars) }

}