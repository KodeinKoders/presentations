package slides

import net.kodein.pres.Slide
import net.kodein.pres.util.transformOrigin
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.KodeinStyle
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val thankYou = Slide(
    name = "thank-you"
) {
    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            width(100.percent)
            height(100.percent)
        }
    }) {
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
                Text("Thank You!")
            }

            P({
                css {
                    margin(0.1.em, 0.em, 1.em, 0.em)
                    fontWeight(200)
                }
            }) {
                Text("Advanced Technology Days - 08/12/2021")
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
                    Br()
                    A(href = "https://twitter.com/salomonbrys", {
                        css { fontSize(0.8.em) }
                    }) {
                        Text("@salomonbrys")
                    }
                }
                A(href = "https://kodein.net") {
                    Logo(KodeinColor.kaumon) { Text("Koders") }
                }
            }

            A(href = "https://kodeinkoders.github.io/presentations/2021-12-08_ATD16_KMM-QuickStart", {
                css {
                    paddingTop(2.em)
                    fontWeight(200)
                }
            }) {
                Text("https://kodeinkoders.github.io/presentations/")
                Br {}
                Text("2021-12-08_ATD16_KMM-QuickStart")
            }
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
            }
        }) {
            Img(src = "img/atd/atd16.png") {
                css {
                    width(10.em)
                }
            }
        }
    }
}