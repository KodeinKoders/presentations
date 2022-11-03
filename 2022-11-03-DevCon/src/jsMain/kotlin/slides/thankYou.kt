package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val thankYou = Slide(
    name = "thank-you"
) {
    Div({
        css {
            flex(1)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }) {
        H1 {
            Text("Merci!")
        }

        P({
            css {
                margin(0.1.em, 0.em, 1.em, 0.em)
                fontWeight(200)
            }
        }) {
            Text("Dev Con 100% Kotlin - 03/11/2022")
        }

        P({
            css {
                fontSize(1.5.em)
                color(KodeinColor.kaumon.css)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                alignItems(AlignItems.Center)
            }
        }) {
            Span({
                css {
                    paddingTop(0.3.em)
                    marginRight(2.em)
                    fontWeight(300)
                }
            }) {
                Text("Salomon BRYS")
            }
            A(href = "https://kodein.net") {
                Logo(KodeinColor.kaumon) { Text("Koders") }
            }
        }

        A(href = "https://kodeinkoders.github.io/presentations/2022-11-03-DevCon", {
            css {
                paddingTop(1.em)
                fontWeight(200)
            }
        }) {
            Text("https://kodeinkoders.github.io/presentations/")
            Br()
            Text("2022-11-03-DevCon")
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                width(100.percent)
                justifyContent(JustifyContent.SpaceAround)
                paddingTop(1.em)

                "img" {
                    property("object-fit", "contain")
                }
            }
        }) {
            Img(src = "img/programmez.png") {
                css { width(30.percent) }
            }
            Img(src = "img/esgi.jpeg") {
                css { width(20.percent) }
            }
        }
    }

}
