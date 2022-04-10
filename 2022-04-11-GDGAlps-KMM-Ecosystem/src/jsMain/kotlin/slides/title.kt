package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val title = Slide(
    name = "title"
) {
    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }) {
        Div {
            H1({
                style {
                    fontSize(2.em)
                }
            }) {
                Text("State of native mobile development with Kotlin Multiplatform")
            }

            P({
                css {
                    fontWeight(200)
                }
            }) {
                Text("GDG Alps #18 - 11/04/2022")
            }
        }
    }

    H3({
        css {
            color(KodeinColor.kaumon.css)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }) {
        Text("Romain BOISSELLE")
    }

    A(href = "https://kodein.net", {
        target(ATarget.Blank)
        css {
            margin(0.5.em)
            fontSize(2.em)
        }
    }) {
        Logo { Text("Koders") }
    }
}
