package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import org.jetbrains.compose.web.css.CSSBuilder
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css
import kotlin.time.Duration.Companion.seconds

val ecosystem = Slide(
    name = "ecosystem",
    stateCount = 12,
    inAnimation = Animations.Move(1.seconds)
) { state ->
    H2({ css { opacity(if (state <= 1 || state == 11) 1.0 else 0.6) } }) { Text("The ecosystem") }

    wordCloud(state, 1, "JetBrains") {
        top(5.percent); left(5.percent)
        opacity(if (state <= 1 || state == 11) 1.0 else 0.5)
    }
    wordCloud(state, 1, "${Emoji.heart} Kotlin Community") {
        bottom(5.percent); left(75.percent)
        opacity(if (state <= 1 || state == 11) 1.0 else 0.5)
    }
    wordCloud(state, 11, "Google") {
        top(5.percent); right(5.percent)
    }
    wordCloud(state, 2, "kotlin-stdlib") { top(20.percent); left(5.percent) }
    wordCloud(state, 3, "Networking") { top(17.percent); right(8.percent)  }
    wordCloud(state, 3, "REST / GraphQL") { bottom(27.percent); left(25.percent) }
    wordCloud(state, 4, "Serialization") { top(35.percent); left(0.percent) }
    wordCloud(state, 5, "SQL") { bottom(40.percent); left(10.percent) }
    wordCloud(state, 5, "No-SQL") { bottom(10.percent); right(40.percent) }
    wordCloud(state, 6, "Async tasks") { top(22.percent); right(35.percent) }
    wordCloud(state, 7, "Dependency Injection") { bottom(38.percent); right(15.percent) }
    wordCloud(state, 8, "MVI / Redux") { bottom(5.percent); left(5.percent) }
    wordCloud(state, 9, "Testing") { top(10.percent); right(45.percent) }
    wordCloud(state, 9, "Mocking") { bottom(25.percent); right(20.percent) }
    wordCloud(state, 10, "Logging") { bottom(20.percent); left(3.percent) }
    wordCloud(state, 10, "Date & Time") { top(35.percent); right(10.percent)}
}

@Composable
private fun wordCloud(
    state: Int,
    fadeAt: Int,
    text: String,
    css: CSSBuilder.() -> Unit,
) {
    Div({
        shownIf(state >= fadeAt, fade)
        css {
            position(Position.Absolute)
            opacity(if (state == fadeAt) 1.0 else 0.5)
            fontSize(1.8.em)
            css()
        }
    }) { Text(text) }
}