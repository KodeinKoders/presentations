package ws.slides

import kotlinx.css.Color
import kotlinx.css.FontWeight
import kotlinx.css.color
import kotlinx.css.fontWeight
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.css
import styled.styledB
import styled.styledH1
import styled.styledH2
import ws.charter.kodein
import ws.utils.light
import ws.utils.medium

fun PresentationBuilder.msg_ecosystem() = slide(
    outTransitions = Flip
) { props ->
    styledH2 {
        css {
            color = Color.kodein.klycine
            fontWeight = FontWeight.lighter
        }

        +"The Kotlin/Multiplatform"
        br {}
        +"ecosystem "
        styledB {
            css {
                color = Color.kodein.korail
                fontWeight = FontWeight.medium
            }
            +"is growing"
        }
        +"!"
    }
}
