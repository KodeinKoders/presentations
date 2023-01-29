package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css

val ksp = listOf(
    Slide(
        name = "ksp:whois",
        stateCount = 5
    ) { state ->
        Div {
            Cap("K")
            Low("otlin", state)
            Cap("S")
            Low("ymbol", state)
            Cap("P")
            Low("rocessor", state)
        }
        Span({
            css { fontSize(1.3.em) }
            shownIf(state > 1, transition = Transitions.fontGrow)
        }) { Text("(or a simpler compiler plugin)") }
        Div({
            css { margin(2.em, 15.em, 2.em, 0.em) }
            shownIf(state > 2, transition = Transitions.fontGrow)
        }) {
            SourceCode(lang = "kotlin", code = """
            @ToBeProcessed 
            interface Foo {
                fun check()
            }«gen:
            
            // build/generated/ksp
            class GeneratedFoo : Foo {
                override fun check() {
                    TODO("Assert something.")
                }
            }»
        """.trimIndent()) {
                "gen" { lineHeight(state > 3)}
            }
        }
    }
)

@Composable
private fun Cap(letter: String) = Span({ css { fontSize(3.em) } }) { Text(letter) }

@Composable
private fun Low(text: String, state: Int) = Span({
    css { fontSize(2.em) }
    shownIf(state > 0, transition = Transitions.fontGrow)
}) { Text("$text ") }
