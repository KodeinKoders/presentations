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
import ws.utils.slideTitle
import ws.utils.titledSlide


fun PresentationBuilder.kmp() = slide(
        stateCount = 1,
        containerStyle = {
            justifyContent = JustifyContent.flexStart
            ".inner-container" {
                backgroundColor = Color("rgba(0, 0, 0, 0)")
            }
        },
        outTransitions = Flip
) { props ->
    titledSlide("My first slide") {

    }
}
