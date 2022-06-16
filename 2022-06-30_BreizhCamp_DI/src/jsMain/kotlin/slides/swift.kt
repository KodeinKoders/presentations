package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.theme.compose.pres.KodeinAttrs
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.cic.css
import utils.strikeThrough

val swift = listOf(
    Slide(
        name = "swift-usage",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 3
    ) { state ->
        H3 { Text("How about Swift & SwiftUI?") }

        SourceCode(
            lang = "swift",
            code = """
                «r:// Retrieval
                struct MyView : View {
                    @ViewBuilder var view: some View {
                        withInstance(Ctrl.self) { (c: Ctrl) -> Void in
                        }
                    }
                }
                »
                «b:// Binding
                let module = DIModule { (b: binder) -> Void in
                    b.bind(Ctrl.self, singleton { Ctrl() })
                }
                »
            """.trimIndent()
        ) {
            "r" { lineHeight(state >= 1) }
            "b" { lineHeight(state >= 2) }
        }
    },

    Slide(
        name = "swift-needs",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 5
    ) { state ->
        H3 { Text("To support Swift, we need...") }
        Ul({
            css {
                "li" {
                    padding(0.3.em, 0.em)
                }
            }
        }) {
            Li {
                Span({
                    css {
                        display(DisplayStyle.InlineBlock)
                        position(Position.Relative)
                    }
                }) {
                    Text("...a way to distribute a compiled framework with a KLib.")
                    strikeThrough(state >= 1)
                }
            }
            Li({
                shownIf(state >= 2, fade)
            }) {
                Text("...a way to ")
                B { Text("distribute") }
                Text(" Swift ")
                B { Text("sources") }
                Text(" with a KLib.")
            }
            Li({
                shownIf(state >= 3, fade)
            }) {
                Text("...a way to ")
                B { Text("modify") }
                Text(" these sources according to the app ")
                B { Text("module") }
                Text(".")
            }
            Li({
                shownIf(state >= 4, fade)
            }) {
                Text("...a way to ")
                B { Text("compile") }
                Text(" these sources alongside the framework")
                B { Text(" compilation") }
                Text(".")
            }
        }
    }
)