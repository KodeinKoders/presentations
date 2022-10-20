package utils

import androidx.compose.runtime.Composable
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css

@Composable
internal fun wordCloud(
        state: Int,
        fadeAt: Int,
        text: String,
        css: CSSBuilder.() -> Unit,
        ) {
    Div({
        shownIf(state >= fadeAt, fade)
        css {
            position(Position.Absolute)
            opacity(if (state == fadeAt) 0.8 else 0.5)
            fontSize(1.7.em)
            css()
        }
    }) { Text(text) }
}
