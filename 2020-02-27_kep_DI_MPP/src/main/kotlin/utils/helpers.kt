package ws.utils

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledSpan

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
                marginTop = 1.em
            }
            body()
        }
    }
}