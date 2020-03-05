package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.dom.p
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes

fun PresentationBuilder.intro() = slide {
    styledH1 {
        css {
            margin(0.5.em)
        }
        +"Advanced multi-platform dependency injection with Kotlin!"
    }
    styledH2 {
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.em
        }
        +"Romain Boisselle"

        styledA(href = "https://twitter.com/romainbsl", target = "_blank") {
            css {
                fontSize = 0.6.em
                fontWeight = FontWeight.w400
                color = Color("#007bfa")
                textDecoration = TextDecoration.none
                display = Display.block
                marginBottom = 0.75.em
            }
            +"@romainbsl"
        }
    }

    styledH3 {
        css {
            fontSize = 0.8.em
            fontWeight = FontWeight.w400
            width = 28.5.em
            marginTop = 2.5.em
        }
        +"Meetup Kotlin/Everywhere Paris"
    }

}
