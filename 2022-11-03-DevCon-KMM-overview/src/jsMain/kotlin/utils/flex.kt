package utils

import androidx.compose.runtime.Composable
import org.kodein.cic.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
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
            flex?.let(::flex)
            justifyContent?.let(::justifyContent)
            alignItems?.let(::alignItems)
            css?.invoke(this)
        }
        attrs?.invoke(this)
    }, content = content)
}
