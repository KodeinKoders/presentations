package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import react.dom.br
import styled.*


fun PresentationBuilder.intro() = slide {
    styledH1 {
        css {
            margin(0.em)
        }
        +"Using C in Kotlin:"
    }
    styledH2 {
        css {
            margin(0.em, bottom = 1.em)
        }
        +"managing native dependencies"
        br {}
        +"in a multi-platform project"
    }
    styledH3 {
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.em
        }
        +"Salomon BRYS"
    }

    styledH3 {
        css {
            fontSize = 0.8.em
            fontWeight = FontWeight.w400
            width = 28.5.em
            textAlign = TextAlign.left
            marginTop = 0.5.em
        }
        styledA(href = "https://twitter.com/salomonbrys") {
            css {
                color = Color("#007bfa")
                textDecoration = TextDecoration.none
                display = Display.block
                marginBottom = 0.75.em
            }
            +" @salomonbrys"
        }
        styledImg(src = "images/mdevcamp.svg") {
            css {
                height = 2.em
            }
        }
    }

}
