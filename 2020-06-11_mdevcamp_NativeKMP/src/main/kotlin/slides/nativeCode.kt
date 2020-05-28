package ws.slides

import kotlinx.css.em
import kotlinx.css.fontSize
import kotlinx.css.margin
import kotlinx.css.opacity
import kotlinx.css.properties.ms
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import react.dom.br
import react.dom.h1
import styled.css
import styled.styledH1
import styled.styledH2
import styled.styledSpan


fun PresentationBuilder.nativeCode() = slide(
        stateCount = 4
) { props ->
    styledH1 {
        css {
            margin(0.em)
        }
        styledSpan {
            css {
                transition(::opacity, duration = 0.3.s)
                opacity = if (props.state >= 1) 0.15 else 1.0
            }
            +"Using "
        }
        styledSpan {
            css {
                transition(::opacity, duration = 0.3.s)
                opacity = if (props.state in 1..2) 0.15 else 1.0
            }
            +"C"
        }
        styledSpan {
            css {
                transition(::opacity, duration = 0.3.s)
                opacity = if (props.state >= 1) 0.15 else 1.0
            }
            +" in Kotlin:"
        }
    }
    styledH2 {
        css {
            margin(0.em, bottom = 1.em)
        }
        styledSpan {
            css {
                transition(::opacity, duration = 0.3.s)
                opacity = if (props.state >= 1) 0.15 else 1.0
            }
            +"managing "
        }
        +"native "
        styledSpan {
            css {
                transition(::fontSize, duration = 0.3.s)
                fontSize = if (props.state <= 1) 1.em else 0.em
            }
            +"dependencies"
        }
        styledSpan {
            css {
                transition(::fontSize, duration = 0.3.s)
                fontSize = if (props.state <= 1) 0.em else 1.em
            }
            +"code"
        }
        br {}
        styledSpan {
            css {
                transition(::opacity, duration = 0.3.s)
                opacity = if (props.state >= 1) 0.15 else 1.0
            }
            +"in a multi-platform project"
        }
    }
}
