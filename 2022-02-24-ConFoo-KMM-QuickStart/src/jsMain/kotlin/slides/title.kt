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
        Img("img/avatar.webp") {
            css {
                width(8.em)
                height(8.em)
                borderRadius(0.2.em)
            }
        }

        Div {
            H1({
                style {
                    fontSize(2.em)
                }
            }) {
                Text("Quick start native mobile Kotlin/Multiplatform setup.")
            }

            P({
                css {
                    fontWeight(200)
                }
            }) {
                Text("ConFoo - 24/02/2022")
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
        Text("Salomon BRYS")
        Img("img/gde.png") {
            css {
                height(1.em)
                margin(0.em, 0.em, 0.25.em, 1.em)
                marginLeft(1.em)
            }
        }
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
