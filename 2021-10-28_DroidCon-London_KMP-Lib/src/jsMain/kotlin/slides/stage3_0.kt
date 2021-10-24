package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.util.d
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val stage3_0 = Slide(
    name = "stage3_0-android",
    stateCount = 3
) { state ->
    H2 {
        Span({
            css {
                fontWeight(200)
            }
        }) {
            Text("Stage 3.0:")
        }
        Br()
        Text("Android Kotlin API")
    }

    Div({
        css {
            position(Position.Relative)
            width(100.percent)
            height(5.em)
        }
    }) {
        H1({
            style {
                position(Position.Absolute)
                fontSize(2.6.em)
                top(0.em)
                property("left", 50.percent - 1.3.em)
            }
            shownIf(state >= 1, grow)
        }) { Text(Emoji.stars) }
        H1({
            style {
                position(Position.Absolute)
                fontSize(4.0.em)
                top((-0.37).em)
                property("left", 50.percent - 1.16.em)
            }
            shownIf(state >= 2, stamp)
        }) { Text(Emoji.no_entry_sign) }
    }

}
