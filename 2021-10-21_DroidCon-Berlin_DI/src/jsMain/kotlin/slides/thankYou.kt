package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
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
                Text("DroidCon Berlin - 21/10/2021")
            }

            H3 {
                A(href = "https://github.com/Kodein-Framework/Kodein-DI", {
                    target(ATarget.Blank)
                }) {
                    Span({ style { fontWeight(300) } }) { Text("https://github.com/") }
                    Br()
                    Span({ style { fontWeight(600) } }) { Text("Kodein-Framework/Kodein-DI") }

//                    Text("https://github.com/")
//                    Br()
//                    Text("Kodein-Framework/Kodein-DI")
                }
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

            A(href = "https://kodeinkoders.github.io/presentations/2021-10-21_DroidCon-Berlin_DI", {
                css {
                    paddingTop(2.em)
                    fontWeight(200)
                }
            }) {
                Text("https://kodeinkoders.github.io/presentations/2021-10-21_DroidCon-Berlin_DI")
            }
        }

        //
        Div({
        }) {
            Img(src = "img/dcb.gif") {
                css {
                    marginTop((-2).em)
                    marginRight((-2).em)
                    property("height", 100.percent + 4.em)
                }
            }
        }
    }
}