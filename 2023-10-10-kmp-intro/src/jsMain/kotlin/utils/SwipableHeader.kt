package utils

import androidx.compose.runtime.Composable
import net.kodein.theme.KodeinFont
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transitions
import org.jetbrains.compose.web.dom.ContentBuilder
import org.jetbrains.compose.web.dom.Div
import org.kodein.compose.html.css.css
import org.w3c.dom.HTMLDivElement

@Composable
fun SweepingHeader(state: Int, content: ContentBuilder<HTMLDivElement>? = null) {
    Div({
        css {
            transitions { "all" { duration = 300.ms } }
            position(Position.Absolute)
            top(if (state == 0) 5.5.em else 0.5.em)
            fontSize(if (state == 0) 2.2.em else 1.8.em)
            fontWeight(500)
            fontFamily(KodeinFont.extended.name)
            property("margin-left", "auto")
            property("margin-right", "auto")
        }
    }, content = content)
}