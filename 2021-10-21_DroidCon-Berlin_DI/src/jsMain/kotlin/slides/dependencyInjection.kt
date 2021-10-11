package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.pres.util.d
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.selectors.descendant
import org.jetbrains.compose.web.css.selectors.universal
import org.jetbrains.compose.web.dom.*


val dependencyInjection = Slide(
    "dependency-injection",
    stateCount = 5
) { state ->
    H1 { Text("Dependency Injection") }
    Div({
        css {
            position(Position.Absolute)
            fontSize(6.em)
            width(2.em)
            top(40.percent)
            property("left", 50.percent - 1.em)
        }
        shownIf(state < 1, grow)
    }) { Text(Emoji.thinking) }
    Div({
        css {
            width(100.percent)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
        }
        hiddenIf(state < 1, fade)
    }) {
        Div({
            css {
                flex(1)
                padding(0.em, 1.5.em, 0.em, 1.5.em)
                textAlign("end")
            }
        }) {
            H4({
                style {
                    textAlign("end")
                    marginLeft(0.em)
                    marginRight(0.em)
                }
            }) { Text("What do I have?") }
            P({ hiddenIf(state < 2, fade) }) { Text("How do I access it?") }
            P({ hiddenIf(state < 2, fade) }) { Text("When do I construct it?") }
            P({ hiddenIf(state < 2, fade) }) { Text("How do I share it?") }
            P({
                css {
                    marginTop(2.em)
                    fontWeight(700)
                }
                hiddenIf(state < 4, fade)
            }) { Text("How do I debug this process?") }
        }
        Div({
            css {
                width(0.2.em)
                backgroundColor(KodeinColor.cute.css)
            }
        })
        Div({
            css {
                flex(1)
                padding(0.em, 1.5.em, 0.em, 1.5.em)
                textAlign("start")
            }
        }) {
            H4({
                style {
                    textAlign("start")
                    marginLeft(0.em)
                    marginRight(0.em)
                }
            }) { Text("What do I need?") }
            P({ hiddenIf(state < 3, fade) }) { Text("How can I express it?") }
            P({ hiddenIf(state < 3, fade) }) { Text("When do I need it?") }
            P({ hiddenIf(state < 3, fade) }) { Text("How can I get it?") }
            P({
                css {
                    marginTop(2.em)
                    fontWeight(700)
                }
                hiddenIf(state < 4, fade)
            }) { Text("How do I debug this process?") }
        }
    }
}
