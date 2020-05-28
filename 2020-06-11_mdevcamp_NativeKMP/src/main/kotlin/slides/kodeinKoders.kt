package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import styled.css
import styled.styledDiv
import styled.styledImg
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
    logo(division = "Koders", href = "https://kodein.net", zoom = 1.0 ) {
        +"painless technology"
    }
    styledDiv {
        css {
            paddingBottom = 4.em
        }
    }
    styledImg(src = "images/certified-training.svg") {
        css {
            height = 4.5.em
            position = Position.absolute
            right = 3.em
            bottom = 2.em
            filter = "drop-shadow(0px 0px 2px white)"

            transition(duration = 0.5.s)
            if (props.state < 1) {
                transform { scale(2) }
                opacity = 0.0
            }
        }
    }
}

