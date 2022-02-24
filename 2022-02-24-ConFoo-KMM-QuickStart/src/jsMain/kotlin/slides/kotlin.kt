package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.pres.widget.SubSlide
import org.jetbrains.compose.web.dom.*


val kotlin = Slide(
    name = "kotlin",
    stateCount = 4
) { state ->
    SubSlide(state in 0..1, fade) {
        H2 {
            Text("Kotlin is ")
            Span({ shownIf(state >= 1, fontGrow) }) {
                Text("also ")
            }
            Text("for Android!")
        }
        P({
            shownIf(state >= 1, fade)
        }) {
            Text("It is also for JVM servers, for iOS, for the Web, for embedded targets, etc.")
        }
    }

    SubSlide(state in 2..3, fade) {
        H2 {
            Span({ shownIf(state >= 3, fontGrow) }) { Text("Is ") }
            Text("Kotlin/Multiplatform ")
            Span({ hiddenIf(state >= 3, fontGrow) }) { Text("isn't ") }
            Text("mature enough")
            Span({ hiddenIf(state >= 3, fontGrow) }) { Text("!") }
            Span({ shownIf(state >= 3, fontGrow) }) { Text("?") }
        }
    }
}