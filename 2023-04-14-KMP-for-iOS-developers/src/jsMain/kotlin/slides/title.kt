package slides

import net.kodein.pres.Slide
import net.kodein.pres.util.zIndex
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val title = Slide(
    name = "title"
) {
    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            flexGrow(1)
            zIndex(1)
            paddingRight(10.em)
        }
    }) {
        A(href = "https://kodein.net", {
            target(ATarget.Blank)
            css {
                margin(0.em, 0.em, 0.5.em, 0.em)
                fontSize(2.em)
            }
        }) {
            Logo { Text("Koders") }
        }

        H1({
            style {
                fontSize(2.1.em)
            }
        }) {
            Text("Kotlin/Multiplatform")
            Br()
            Text("for iOS developers")
            Br()
            Small {
                Text("State & Future")
            }
        }

        P({
            css {
                margin(0.em, 0.em, 0.5.em, 0.em)
                fontWeight(200)
            }
        }) {
            Text("KotlinConf'23 - 14/04/2023")
        }

        H3({
            css {
                color(KodeinColor.kaumon.css)
            }
        }) {
            Text("Salomon BRYS")
        }

        Img("img/gde.png") {
            css {
                height(1.4.em)
            }
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
    Img(src = "img/kc23.png") {
        css {
            width(15.em)
            property("object-fit", "contain")
            position(Position.Absolute)
            top(3.em)
            right(1.6.em)
            zIndex(0)
        }
    }
}
