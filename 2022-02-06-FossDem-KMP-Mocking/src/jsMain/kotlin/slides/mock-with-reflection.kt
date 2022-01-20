package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.dom.*
import utils.strikeThrough

val mockWithReflection = Slide(
    name = "mock-with-reflection",
    stateCount = 4
) { state ->

    SourceCode(
        lang = "kotlin",
        code = """
            val repo = mockk<Repository>(relaxed = true)
        """.trimIndent()
    )

    Div({
        css {
            fontSize(1.6.em)
        }
    }) {
        Ul {
            Li({ shownIf(state >= 1, fade) }) {
                strikeThrough(state >= 3) {
                    Text("Run time type reflection")
                }
            }
            Li({ shownIf(state >= 2, fade) }) {
                strikeThrough(state >= 3) {
                    Text("Runtime manipulation")
                }
            }
        }

        Span({ shownIf(state >= 3, fade) }) {
            Text("Not available in Kotlin/Multiplatform!")
        }
    }
}
