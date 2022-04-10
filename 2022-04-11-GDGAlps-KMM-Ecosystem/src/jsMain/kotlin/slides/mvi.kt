package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text


val mvi = listOf(
    Slide(
        name = "mvi",
        stateCount = 5
    ) { state ->
        H2 { Text("Model View Intent") }

        Img(src = "img/mvi.png") {
            css {
                height(10.em)
            }
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceAround)
                width(100.percent)
            }
        }) {
            Div({
                shownIf(state >= 1, fade)
            }) {
                SourceCode(
                    lang = "kotlin",
                    """
                        @Composable
                        fun ViewContent(
                            «z:model: Model»,
                            «z:post: (Intent) -> Unit»
                        )
                    """.trimIndent()
                ) { "z" { zoomed(state == 3) } }
            }

            Div({
                shownIf(state >= 2, fade)
            }) {
                SourceCode(
                    lang = "swift",
                    """
                        
                        struct ViewContent: View {
                            var «z:model: Model»
                            var «z:post: (Intent) -> Void»
                        }
                    """.trimIndent()
                ) { "z" { zoomed(state == 3) } }
            }
        }
    }
)