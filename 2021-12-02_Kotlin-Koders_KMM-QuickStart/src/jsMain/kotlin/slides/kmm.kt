package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.*
import net.kodein.pres.Transitions.fade
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img


val kmm = Slide(
    name = "kmm",
    stateCount = 2
) { state ->
    Div({
        css {
            position(Position.Absolute)
            width(100.percent)
            height(100.percent)
            top(0.percent)
            left(0.percent)
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.Center)
        }
    }) {
        Img(src = "img/kmm2.svg") {
            css {
                height(20.em)
            }
        }
    }
    Div({
        css {
            position(Position.Absolute)
            width(100.percent)
            height(100.percent)
            top(0.percent)
            left(0.percent)
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.Center)
        }
    }) {
        Img(src = "img/kmm.svg") {
            css {
                height(20.em)
            }
            hiddenIf(state >= 1, fade)
        }
    }
}
