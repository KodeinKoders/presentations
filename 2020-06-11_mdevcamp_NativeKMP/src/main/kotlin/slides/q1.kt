package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.translate
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import react.dom.img
import styled.*
import ws.utils.opacity
import ws.utils.s
import ws.utils.transform

fun PresentationBuilder.q1() = slide(stateCount = 2) { props ->

    styledImg(src = "images/wondering.png") {
        css {
            height = 5.em
            zIndex = 2
        }
    }

    styledH2 {
        css {
            opacity(props.state >= 1)
            transform(props.state < 1) {
                translate(0.em, (-2).em)
            }
            paddingBottom = 2.em
        }

        +"Have you worked or played"
        br {}
        +"with Kotlin/Multiplatform?"
    }

}
