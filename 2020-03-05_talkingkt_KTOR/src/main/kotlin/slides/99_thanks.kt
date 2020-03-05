package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.child
import react.functionalComponent
import styled.*
import ws.kpres.Flip
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.blockEffectFrom
import ws.utils.getValue
import ws.utils.kotlinSourceCode
import ws.utils.slideTitle

private val ThankYou by functionalComponent<SlideContentProps> { props ->
    styledH1 {
        css {
            margin(0.5.em)
        }
        +"Thank You!"
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
        styledA(href = "https://twitter.com/KodeinKoders", target = "_blank") {
            css {
                fontSize = 0.6.em
                fontWeight = FontWeight.w400
                color = Color("#007bfa")
                textDecoration = TextDecoration.none
                display = Display.block
                marginBottom = 0.75.em
            }
            +"@KodeinKoders"
        }
    }
}

fun PresentationBuilder.thanks() {
    slide(SlideInfos(inTransitions = Flip)) { child(ThankYou, it) }
}