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
                Text("DroidCon London - 28/10/2021")
            }

            H4 {
                A(href = "https://github.com/KodeinKoders/Playground-Demo-Crypto", {
                    target(ATarget.Blank)
                }) {
                    Span({ style { fontWeight(300) } }) { Text("https://github.com/") }
                    Span({ style { fontWeight(600) } }) { Text("KodeinKoders/") }
                    Br()
                    Span({ style { fontWeight(600) } }) { Text("Playground-Demo-Crypto") }
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

            A(href = "https://kodeinkoders.github.io/presentations/2021-10-28_DroidCon-London_KMP-Lib", {
                css {
                    paddingTop(2.em)
                    fontWeight(200)
                }
            }) {
                Text("https://kodeinkoders.github.io/presentations/")
                Br()
                Text("2021-10-28_DroidCon-London_KMP-Lib")
            }
        }

        Div {
            Img(src = "img/dcl.gif") {
                css {
                    marginTop(20.percent + 2.em)
                    marginRight((-10).em)
                    property("height", 80.percent + 4.em)
                }
            }
        }
    }
}
