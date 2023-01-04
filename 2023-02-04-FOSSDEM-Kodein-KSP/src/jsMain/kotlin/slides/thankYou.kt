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
            Text("Thank You!")
        }

        P({
            css {
                margin(0.1.em, 0.em, 1.em, 0.em)
                fontWeight(200)
            }
        }) {
            Text("FOSDEM - Kotlin DevRoom - 04/02/2023")
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
            }
            A(href = "https://kodein.net") {
                Logo(KodeinColor.kaumon) { Text("Koders") }
            }
        }

        A(href = "https://kodeinkoders.github.io/presentations/2023-02-04-FOSSDEM-Kodein-KSP", {
            css {
                paddingTop(1.em)
                fontWeight(200)
            }
        }) {
            Text("https://kodeinkoders.github.io/presentations/")
            Br()
            Text("2023-02-04-FOSSDEM-Kodein-KSP")
        }
    }
}
