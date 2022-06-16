package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginRight
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css

val dependencyInjection = Slide(
    "dependency-injection",
    stateCount = 5
) { state ->
    H1 {
        Span({
            shownIf(state >= 1, fade)
        }) {
            Text("« Advanced »")
            Br()
        }
        Text("Dependency Injection")
    }
    Div({
        css {
            position(Position.Absolute)
            fontSize(6.em)
            width(2.em)
            top(40.percent)
            property("left", 50.percent - 1.em)
        }
        shownIf(state == 0, stamp)
    }) { Text(Emoji.thinking) }
    Div({
        css {
            position(Position.Absolute)
            fontSize(6.em)
            width(2.em)
            top(40.percent)
            property("left", 50.percent - 1.em)
        }
        shownIf(state == 1, stamp)
    }) { Text(Emoji.stopwatch) }
    Div({
        css {
            width(100.percent)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
        }
        hiddenIf(state < 2, fade)
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
            P({ hiddenIf(state < 3, fade) }) { Text("How do I access it?") }
            P({ hiddenIf(state < 3, fade) }) { Text("When do I construct it?") }
            P({ hiddenIf(state < 3, fade) }) { Text("How do I share it?") }
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
