package utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.kodein.cic.css
import org.w3c.dom.HTMLDivElement

@Composable
fun Flex(
    direction: FlexDirection,
    flex: Int? = null,
    justifyContent: JustifyContent? = null,
    alignItems: AlignItems? = null,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    css: (CSSBuilder.() -> Unit)? = null,
    content: ContentBuilder<HTMLDivElement>? = null
) {
    Div(attrs = {
        css {
            display(DisplayStyle.Flex)
            flexDirection(direction)
            width(80.percent)
            position(Position.Relative)
            textAlign("center")
            "p" {
                width(90.percent)
                lineHeight("1.4")
                fontSize(1.2.em)
                margin(0.25.em)
                padding(0.25.em)
                borderRadius(0.25.em)
            }

            flex?.let(::flex)
            justifyContent?.let(::justifyContent)
            alignItems?.let(::alignItems)
            css?.invoke(this)
        }
        attrs?.invoke(this)
    }, content = content)
}
