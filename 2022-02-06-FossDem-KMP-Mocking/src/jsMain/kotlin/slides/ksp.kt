package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.dom.*
import utils.strikeThrough

val ksp = Slide(
    name = "ksp",
    stateCount = 5
) { state ->
    H3 { Text("Google Kotlin Symbol Processor API") }

    A(href = "https://github.com/google/ksp") { Text("https://github.com/google/ksp") }

    Div({
        css {
            fontSize(1.6.em)
        }
    }) {
        Ul {
            Li({ shownIf(state >= 1, fade) }) {
                Text("allows to read source code at compile time")
                Span({ hiddenIf(state >= 3, fontGrow) }) { Text(".") }
                Span({ shownIf(state >= 3, fontGrow) }) { Text(",") }
                Br {}
                Span({ shownIf(state >= 3, fontGrow) }) {
                    B { Text("with a stable API") }
                    Text(".")
                }
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("allows to generate ")
                Span({ shownIf(state >= 3, fontGrow) }) {
                    B { Text("Kotlin") }
                    Text(" ")
                }
                Text("code accordingly")
            }
            Li({ shownIf(state >= 4, fade) }) {
                Text("Now compatible with ")
                B { Text("Kotlin/Multiplatform") }
            }
        }
    }

}
