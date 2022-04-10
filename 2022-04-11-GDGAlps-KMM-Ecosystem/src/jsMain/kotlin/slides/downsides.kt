package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.widget.StrikeThrough
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import utils.SweepingHeader

val downsides = Slide(
    name = "downsides",
    stateCount = 4
) { state ->
    SweepingHeader(state, "So, is there a catch?")

    Div({
        shownIf(state >= 1, fade)
        css {
            fontSize(1.25.em)
            margin(1.em)
        }
    }) {
        StrikeThrough(KodeinColor.cute.css, state >= 2) {
            Text("Coroutines + Kotlin/Native + Multi-thread.")
        }
    }

    Div({
        shownIf(state >= 2, fade)
        css {
            fontSize(1.25.em)
            margin(1.em)
        }
    }) {
        Text("Kotlin - Swift interop through Objective-C")
    }

    Div({
        shownIf(state >= 3, fade)
        css {
            fontSize(1.25.em)
            margin(1.em)
        }
    }) { Text("Build System - configuration and UX") }
}