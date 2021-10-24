package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.*


val stage4 = Slide(
    name = "stage4-code",
    stateCount = 2
) { state ->
    H2 {
        Span({
            css {
                fontWeight(200)
            }
        }) {
            Text("Stage 4:")
        }
        Br()
        Text("Publish!")
    }

    H1({ shownIf(state >= 1, grow) }) { Text(Emoji.stars) }

}