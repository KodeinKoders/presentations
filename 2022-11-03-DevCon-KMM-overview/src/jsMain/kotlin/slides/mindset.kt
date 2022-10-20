package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinFont
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css
import utils.SweepingHeader

val mindset = Slide(
    name = "mindset",
    stateCount = 4
) { state ->
    SweepingHeader(state, "The right mindset")

    P {
        Span({
            shownIf(state > 0, fontGrow)
            css { fontSize(1.25.em) }
        }) {
            Text("You are not just doing Android...")
        }
        Br{}
        Span({
            shownIf(state > 1, fontGrow)
            css { fontSize(1.25.em) }
        }) { Text("...nor iOS") }
    }
    P({
        shownIf(state > 2, fontGrow)
        css {
            fontSize(1.5.em)
            fontWeight(500)
            fontFamily(KodeinFont.extended.name)
        }
    }) { Text("You are a Multiplatform developer!!!") }

}