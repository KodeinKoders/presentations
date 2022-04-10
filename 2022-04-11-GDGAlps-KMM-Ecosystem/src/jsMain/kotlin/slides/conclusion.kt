package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.shownIf
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

val conclusion = Slide(
    name = "conclusion",
    stateCount = 4
) { state ->
        H4 { Text("Is Kotlin Multiplatform mature enough?") }

        H2({
            shownIf(state >= 1, Transitions.fade)
            style {
                marginBottom(0.em)
            }
        }) {
            Text("Keep calm and start using KMM!")
        }


    P({
        shownIf(state >= 2, Transitions.fade)
        css {
            marginTop(0.em)
        }
    }) {
        Text("Do not take my word for it!")
    }

    Div({
        shownIf(state >= 3, Transitions.fontGrow)
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