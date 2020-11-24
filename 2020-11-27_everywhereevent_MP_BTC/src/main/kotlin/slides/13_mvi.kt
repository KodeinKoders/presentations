package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import styled.css
import styled.styledH1
import ws.charter.kodein
import ws.utils.light
import ws.utils.titledSlide

fun PresentationBuilder.mvi() = slide(
    inTransitions = Flip,
    inTransitionDuration = 1500,
    containerStyle = {
        ".inner-container" {
            backgroundColor = Color.transparent
            transition(::background, 1500.ms)
        }
    },
) { props ->
    titledSlide("Model View Intent") {

    }
}
