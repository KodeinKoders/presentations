package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.html.js.onLoadFunction
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.child
import react.functionalComponent
import react.useState
import styled.css
import styled.styledIframe
import styled.styledImg
import ws.utils.getValue
import ws.utils.provideDelegate


private val Slide by functionalComponent<SlideContentProps> {
    styledImg {
        css {
            maxWidth = 100.pct
            maxHeight = 100.pct
            transition(::opacity, 500.ms)
        }
        attrs.src = "images/medium.png"
    }
}


fun PresentationBuilder.immutability() = slide {
    child(component = Slide, props = it)
}
