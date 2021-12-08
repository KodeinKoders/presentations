package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val conclusion = Slide(
    name = "conclusion",
    stateCount = 4
) { state ->
    H3 { Text("Is Kotlin/Multiplatform mature enough?") }

    H2({
        shownIf(state >= 1, fade)
        style {
            marginBottom(0.em)
        }
    }) {
        Text("Hell Yeah!")
    }

    P({
        shownIf(state >= 2, fade)
        css {
            marginTop(0.em)
        }
    }) {
        Text("OK, I'm biased!")
    }

    Div({
        shownIf(state >= 3, fontGrow)
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

            "img.vert" { height(4.em) }
        }
    }) {
        Div {
            Img(src = "img/logos/phillips.svg")
            Img(src = "img/logos/netflix.svg")
            Img(src = "img/logos/deezer.svg")
        }

        Div {
            Img(src = "img/logos/autodesk.svg")
            Img(src = "img/logos/leroymerlin.svg") { classes("vert") }
            Img(src = "img/logos/vmware.svg")
        }
    }
}
