package ws.slides

import kotlinx.css.Color
import kotlinx.css.FontWeight
import kotlinx.css.color
import kotlinx.css.fontWeight
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.css
import styled.styledB
import styled.styledH2
import ws.charter.kodein
import ws.utils.medium


fun PresentationBuilder.msg_mvi() = slide(
) { props ->
    styledH2 {
        css {
            color = Color.kodein.klycine
            fontWeight = FontWeight.lighter
        }

        +"MVI provides a simple and repeatable"
        br {}
        styledB {
            css {
                color = Color.kodein.korail
                fontWeight = FontWeight.medium
            }
            +"behaviour"
        }
        +" shared abstraction."
    }
}
