package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.html.js.onLoadFunction
import react.child
import react.functionalComponent
import react.useState
import styled.css
import styled.styledIframe
import styled.styledImg
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.utils.getValue
import ws.utils.provideDelegate


private val infos = SlideInfos(
)

private val Slide by functionalComponent<SlideContentProps> {
    var loaded by useState(false)

    styledImg {
        css {
            maxWidth = 100.pct
            maxHeight = 100.pct
            transition(::opacity, 500.ms)
            opacity = if (loaded) 0.0 else 1.0
        }
        attrs.src = "images/medium.png"
    }

    styledIframe {
        css {
            borderWidth = 0.em
            width = 100.pct - 4.em
            height = 100.pct - 4.em
            margin(2.em)
            opacity = 0.0
            position = Position.absolute
            top = 0.px
            left = 0.px
            transition(::opacity, 500.ms)
            opacity = if (loaded) 1.0 else 0.0
        }
        attrs.src = "https://itnext.io/designing-a-kotlin-memory-safe-mode-c76c06317c3e"
        attrs.onLoadFunction = { loaded = true }
    }
}


fun PresentationBuilder.immutability() = slide(infos) {
    child(functionalComponent = Slide, props = it)
}
