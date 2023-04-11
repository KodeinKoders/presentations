package utils

import net.kodein.pres.Transition
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.Transitions
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.opacity
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds


class Dimmed(private val duration: Duration) : Transition {
    override val cssTransitions: Transitions.() -> Unit = {
        "opacity" { duration = this@Dimmed.duration.inWholeMilliseconds.ms }
    }

    override val hiddenStyle: StyleScope.() -> Unit = {
        opacity(0.2)
    }
}

val dimmed = Dimmed(300.milliseconds)