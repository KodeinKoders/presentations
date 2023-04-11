package slides

import net.kodein.pres.Slide
import net.kodein.pres.util.zIndex
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
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
            paddingRight(12.em)
            zIndex(1)
        }
    }) {
        H1 {
            Text("Thank you,")
            Br()
            Span({
                css {
                    fontFamily(KodeinFont.main.name)
                    fontSize(0.5.em)
                    marginTop(-(0.2).em)
                    display(DisplayStyle.Block)
                }
            }) { Text("and don't forget to vote!") }
        }

        P({
            css {
                margin(0.1.em, 0.em, 1.em, 0.em)
                fontWeight(200)
            }
        }) {
            Text("KotlinConf'23 - 14/04/2023")
        }

        P({
            css {
                fontSize(1.5.em)
                color(KodeinColor.kaumon.css)
            }
        }) {
            Text("Salomon BRYS")
        }

        A(href = "https://kodein.net", {
            css {
                fontSize(1.5.em)
                paddingBottom(1.em)
            }
        }) {
            Logo(KodeinColor.kaumon) { Text("Koders") }
        }

        A(href = "https://p.kodein.net/kc23", {
            css {
                paddingTop(1.em)
                fontWeight(200)
            }
        }) {
            Text("https://p.kodein.net/kc23")
        }
    }

    Img(src = "img/end.png") {
        css {
            height(80.percent)
            property("object-fit", "contain")
            position(Position.Absolute)
            top(0.em)
            right(2.em)
            zIndex(0)
        }
    }

}
