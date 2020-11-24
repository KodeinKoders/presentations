package ws.utils

import kotlinx.css.*
import react.RBuilder
import styled.*
import ws.charter.kodein

fun RBuilder.slideTitle(title: String) {
    flexRow(justifyContent = JustifyContent.left, alignItems = Align.center) {
        css {
            width = 100.pct
            margin(bottom = .5.em)
        }
        styledImg(alt = "Kodein logo", src = "images/logo-kinzolin.svg") {
            css {
                display = Display.block
                width = 2.em
                height = 2.em
                marginRight = 1.rem
            }
        }
        styledDiv {
            css {
                fontSize = 1.5.em
                fontWeight = FontWeight.lighter
                textAlign = TextAlign.start
                color = Color.kodein.orange
                margin(left = 1.rem)
            }
            +" $title"
        }
    }
}

fun RBuilder.titledSlide(title: String, body: RBuilder.() -> Unit) {
    flexColumn(justifyContent = JustifyContent.flexStart, alignItems = Align.center) {
        css {
            height = 100.pct
            width = 100.pct
        }

        slideTitle(title)

        flexColumn(JustifyContent.center, Align.center) {
            css {
                flexGrow = 1.0
                width = 100.pct
                position = Position.relative
            }
            body()
        }
    }
}
