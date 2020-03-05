package ws.utils

import kotlinx.css.*
import react.RBuilder
import styled.*
import styled.StyledComponents.css

fun RBuilder.slideTitle(title: String) {
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" $title"
    }
}

fun RBuilder.titledContent(title: String, body: RBuilder.() -> Unit) {
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
                fontSize = 2.em
            }
            +title
        }
        styledDiv {
            css {
                height = 90.pct
                width = 100.pct
                paddingTop = 1.em
            }
            body()
        }
    }
}

fun RBuilder.showCode() {
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(0.em, 0.em, 0.3.em, 0.em)
            padding(1.em)
        }
        +" Show us some code!"
    }
    styledImg(src = "images/technologist.png") {
        css {
            height = 5.em
        }
    }
}