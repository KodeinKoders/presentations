package slides

import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css


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
                Text("DevCon Kotlin - 03/11/2022")
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
