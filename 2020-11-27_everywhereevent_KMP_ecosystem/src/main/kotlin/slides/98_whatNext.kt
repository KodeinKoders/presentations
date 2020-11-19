package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.RBuilder
import react.functionalComponent
import styled.css
import styled.styledLi
import styled.styledSpan
import ws.utils.*

fun PresentationBuilder.whatNext() = slide(stateCount = 1) { props ->
    titledSlide("Where to go next?") {

    }
}
