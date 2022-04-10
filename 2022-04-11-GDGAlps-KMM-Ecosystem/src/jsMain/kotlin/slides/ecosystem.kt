package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.theme.compose.web.css
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
    wordCloud(state, 2, "kotlin-stdlib") { top(20.percent); left(5.percent) }
    wordCloud(state, 3, "ktor-client") { top(5.percent); right(5.percent)  }
    wordCloud(state, 3, "Apollo-GraphQL") { bottom(30.percent); left(25.percent) }
    wordCloud(state, 4, "KotlinX Serialization") { top(35.percent); left(0.percent) }
    wordCloud(state, 5, "SQLDelight") { bottom(40.percent); left(0.percent) }
    wordCloud(state, 5, "Kodein-DB") { bottom(10.percent); right(40.percent) }
    wordCloud(state, 6, "KotlinX Coroutines") { top(22.percent); right(30.percent) }
    wordCloud(state, 7, "Kodein-DI") { bottom(38.percent); right(15.percent) }
    wordCloud(state, 8, "MVIKotlin") { bottom(5.percent); left(5.percent) }
    wordCloud(state, 9, "kotlin-test") { top(10.percent); right(45.percent) }
    wordCloud(state, 9, "MockKMP") { bottom(25.percent); right(20.percent) }
    wordCloud(state, 10, "Kodein-Log") { bottom(20.percent); left(3.percent) }
    wordCloud(state, 10, "KotlinX DateTime") { top(35.percent); right(10.percent)}
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