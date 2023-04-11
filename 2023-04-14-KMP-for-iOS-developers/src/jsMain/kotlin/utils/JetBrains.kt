package utils

import androidx.compose.runtime.Composable
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import org.w3c.dom.HTMLDivElement


@Composable
fun JetBrains(
        attrs: AttrBuilderContext<HTMLDivElement>? = null,
        content: ContentBuilder<HTMLDivElement>
) {
    Div({
        css {
            width(80.percent)
            height(70.percent)
            position(Position.Absolute)
            top(15.percent)
            left(10.percent)
            backgroundColor(KodeinColor.cute.css)
            borderRadius(0.5.em)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
        }
        attrs?.invoke(this)
    }) {
        Img(src = "img/Jetbrains.svg") {
            css {
                width(6.em)
                height(6.em)
                padding(2.em)
                alignSelf(AlignSelf.Center)
            }
        }
        Div({
            css {
                flexGrow(1)
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
                color(Color.black)
                fontSize(1.2.em)
                paddingRight(2.em)
            }
        }) {
            Div {
                content(this)
            }
        }
    }
}