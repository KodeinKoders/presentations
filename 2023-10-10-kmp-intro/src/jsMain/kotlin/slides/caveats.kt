package slides

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.compose.html.css.css
import org.kodein.compose.html.pres.Animations
import org.kodein.compose.html.pres.Slide
import org.kodein.compose.html.pres.Slides
import org.kodein.compose.html.pres.Transitions
import org.kodein.compose.html.pres.shownIf

val caveats = Slides(Animations.Move(towards = Animations.Move.Towards.Bottom)) {

    +Slide(name = "no-fool", stateCount = 2) { state ->
        H2 { Text("I am not a fool!") }
        H3({ shownIf(state > 0, Transitions.Fade()) }) { Text("So, where is the catch?") }
    }

    +Slide(name = "caveat", stateCount = 4) { state ->
        H2 { Text("Some caveats") }
        Div({
            css {
                fontSize(1.3.em)
                paddingTop(1.em)
                paddingLeft(1.em)
            }
        }) {
            Span({ shownIf(state > 0, Transitions.Fade()) }) {
                Text("Overwhelming toolchain")
            }
            Br()
            Span({ shownIf(state > 1, Transitions.Fade()) }) {
                Text("Concurrency interoperability")
            }
            Br()
            Span({ shownIf(state > 2, Transitions.Fade()) }) {
                Text("Heavy continuous delivery")
            }
        }
    }

    +Slide(name = "showcase") {
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                width(100.percent)

                "div" {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    justifyContent(JustifyContent.SpaceAround)
                    alignItems(AlignItems.Center)
                    width(100.percent)
                    margin(1.5.em, 0.em)
                }

                "img" { height(2.em) }
            }
        }) {
            Div {
                Img(src = "img/logos/phillips.svg")
                Img(src = "img/logos/netflix.svg")
                Img(src = "img/logos/deezer.svg")
            }

            Div {
                Img(src = "img/logos/autodesk.svg")
                Img(src = "img/logos/sumup.svg")
                Img(src = "img/logos/vmware.svg")
            }
        }
    }
}
