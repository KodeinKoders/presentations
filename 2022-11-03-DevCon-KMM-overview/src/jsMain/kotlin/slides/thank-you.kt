package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css

val thankYou = Slide(
    name = "thank-you",
    stateCount = 1
) { state ->
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
                Text("DevCon Kotlin - 03/11/2022")
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
                    Text("Romain BOISSELLE")
                    Br()
                    A(href = "https://twitter.com/romainbsl", {
                        css { fontSize(0.8.em) }
                    }) {
                        Text("@romainbsl")
                    }
                }
                A(href = "https://kodein.net") {
                    Logo(KodeinColor.kaumon) { Text("Koders") }
                }
            }

            A(href = "https://kodeinkoders.github.io/presentations/2022-11-03-DevCon-KMM-overview", {
                css {
                    paddingTop(2.em)
                    fontWeight(200)
                }
            }) {
                Text("https://kodeinkoders.github.io/presentations/")
                Br {}
                Text("2022-11-03-DevCon-KMM-overview")
            }

        }
    }

}