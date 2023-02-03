package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Dimmed
import utils.Stamp
import utils.dimmed


val d3_solution = listOf(
    Slide(
        name = "solution",
        stateCount = 3
    ) { state ->
        H4 {
            Span({ shownIf(state < 1, dimmed) }) {
                Text("Reflection is a feature [that] allows an ")
            }
            Span({ shownIf(state < 2, dimmed) }) {
                Text("executing")
            }
            Span({ shownIf(state < 1, dimmed) }) {
                Text(" Java program to examine or \"introspect\" upon itself, ")
                Text("and manipulate internal properties of the program.")
            }
        }

        Stamp(state == 2) {
            Text("What you cannot do")
            Br()
            Text("at Run-Time,")
            Br()
            Text("do at Compile-Time!")
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
