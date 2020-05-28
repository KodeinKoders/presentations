package ws.slides

import kotlinx.css.opacity
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import react.dom.h1
import styled.css
import styled.styledSpan


fun PresentationBuilder.noSQL() = slide(
        stateCount = 3,
        notes = notes {
            0 ("Préambule : titre, expliquer...")
            1..2 ("...No**SQL**")
        }
) { props ->
    h1 {
        styledSpan {
            key = "1"
            css {
                if (props.state >= 1) opacity = 0.1
                transition(::opacity, 300.ms)
            }
            +"Embedded "
        }
        styledSpan {
            key = "2"
            css {
                if (props.state >= 2) opacity = 0.1
                transition(::opacity, 300.ms)
            }
            +"No"
        }
        +"SQL"
        styledSpan {
            key = "3"
            css {
                if (props.state >= 1) opacity = 0.1
                transition(::opacity, 300.ms)
            }
            +" data persistence, everywhere!"
        }
    }
}
