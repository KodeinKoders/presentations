package utils

import androidx.compose.runtime.Composable
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.P
import org.kodein.cic.css


@Composable
fun Stamp(shown: Boolean, content: @Composable () -> Unit) {
    P({
        shownIf(shown, Transitions.stamp)

        css {
            position(Position.Absolute)
            top(0.px)
            left(0.px)
            width(100.percent)
            height(100.percent)
            padding(0.px)
            margin(0.px)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            fontSize(2.em)
            fontFamily(KodeinFont.extended.name)
            fontWeight(800)
            transform {
                rotate(30.deg)
            }
            color(KodeinColor.korail.css)
        }
    }) {
        content()
    }
}