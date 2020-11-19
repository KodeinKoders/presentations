package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import styled.css
import styled.styledDiv
import styled.styledImg
import ws.charter.kodein
import ws.comp.logo


fun PresentationBuilder.kodeinKoders() = slide(
        stateCount = 2,
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("rgba(0, 0, 0, 0)")
            }
        },
        outTransitions = Flip
) { props ->
    logo(
        light = "Koders",
        logoColor = "orange",
        fontColor = Color.kodein.orange,
        href = "https://kodein.net",
        zoom = 1.0
    ) {
        +"painless technology"
    }
    styledDiv {
        css {
            paddingBottom = 4.em
        }
    }
    styledImg(src = "images/certified-training.svg") {
        css {
            backgroundColor = Color.kodein.cute.withAlpha(0.5)
            padding(1.5.rem)
            borderRadius = 1.rem
            height = 4.5.em
            position = Position.absolute
            right = 2.em
            bottom = 2.em

            transition(duration = 0.5.s)
            if (props.state < 1) {
                transform { scale(2) }
                opacity = 0.0
            }
        }
    }
}

