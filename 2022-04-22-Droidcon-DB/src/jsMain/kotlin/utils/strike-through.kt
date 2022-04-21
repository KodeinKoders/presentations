package utils

import androidx.compose.runtime.Composable
import net.kodein.pres.util.transition
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span


@Composable
fun StrikeThrough(shown: Boolean, content: @Composable () -> Unit) {
    Span({
        css {
            display(DisplayStyle.InlineBlock)
            position(Position.Relative)
        }
    }) {
        Div({
            css {
                position(Position.Absolute)
                height(0.1.em)
                backgroundColor(KodeinColor.cute.css)
                left(0.em)
                property("top", 50.percent - 0.05.em)
                transition {
                    "opacity"(1.s)
                    "width"(1.s)
                }
            }
            style {
                if (shown) {
                    width(100.percent)
                    opacity(1)
                } else {
                    width(0.percent)
                    opacity(0)
                }
            }
        })
        Span({
            css {
                transition { "opacity"(1.s) }
            }
            style {
                if (shown) { opacity(0.6) }
            }
        }) {
            content()
        }
    }
}