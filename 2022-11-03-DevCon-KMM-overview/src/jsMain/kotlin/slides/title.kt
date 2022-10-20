package slides

import net.kodein.pres.OverlayAttrs
import net.kodein.pres.Slide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css


val title = Slide(
    name = "title",
    config = {
        OverlayAttrs {
            css {
                backgroundColor(KodeinColor.cute.css)
                color(KodeinColor.dark.css)
            }
        }
    }
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
                    fontWeight(300)
                }
            }) {
                Text("DevCon #15 Kotlin - 03/11/2022")
            }
        }
    }

    H4({
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

    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
        }
    }) {
        Img("img/logos/programmez.png") {
            css {
                height(3.em)
                margin(0.5.em)
            }
        }

        Img("img/logos/esgi.png") {
            css {
                height(6.em)
                margin(0.5.em)
            }
        }

        Img("img/logos/jb_square.png") {
            css {
                height(6.em)
                margin(0.5.em)
            }
        }
    }
}
