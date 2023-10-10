package utils

import androidx.compose.runtime.Composable
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.P
import org.kodein.compose.html.css.css
import org.kodein.compose.html.pres.Transitions
import org.kodein.compose.html.pres.shownIf


@Composable
fun Stamp(shown: Boolean, content: @Composable () -> Unit) {
    P({
        shownIf(shown, Transitions.Stamp())

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
            fontSize(5.em)
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