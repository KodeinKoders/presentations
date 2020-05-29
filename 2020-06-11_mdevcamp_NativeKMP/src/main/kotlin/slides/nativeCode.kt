package ws.slides

import kotlinx.css.em
import kotlinx.css.margin
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.css
import styled.styledH1
import styled.styledH2
import ws.utils.fontSize
import ws.utils.opacity
import ws.utils.s


fun PresentationBuilder.nativeCode() = slide(
        stateCount = 4
) { props ->
    styledH1 {
        css {
            margin(0.em)
        }
        s {
            opacity { if (props.state >= 1) 0.15 else 1.0 }
            +"Using "
        }
        s {
            opacity { if (props.state in 1..2) 0.15 else 1.0 }
            +"C"
        }
        s {
            opacity { if (props.state >= 1) 0.15 else 1.0 }
            +" in Kotlin:"
        }
    }
    styledH2 {
        css {
            margin(0.em, bottom = 1.em)
        }
        s {
            opacity { if (props.state >= 1) 0.15 else 1.0 }
            +"managing "
        }
        +"native "
        s {
            fontSize { if (props.state <= 1) 1.em else 0.em }
            +"dependencies"
        }
        s {
            fontSize { if (props.state <= 1) 0.em else 1.em }
            +"code"
        }
        br {}
        s {
            opacity { if (props.state >= 1) 0.15 else 1.0 }
            +"in a multi-platform project"
        }
    }
}
