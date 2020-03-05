package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.borderLeft
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.dom.br
import styled.css
import styled.styledBlockQuote
import styled.styledDiv
import styled.styledSmall
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes

private val infos = SlideInfos(
        stateCount = 2,
        notes = notes {
            0 ("Lorsque l'on développe une application mobile ou plus généralement cliente, la plus part du temps, SQL n'est pas nécéssaire à la gestion des données")
            1 ("**Opinion !!**")
        }
)

fun PresentationBuilder.opinion() = slide(infos) { props ->

    styledDiv {
        css {
            textAlign = TextAlign.left
            borderLeft(0.1.em, BorderStyle.solid, Color.white)
            paddingLeft = 0.6.em
            width = 19.em
            fontSize = 1.25.em
        }

        styledBlockQuote {
            css {
                fontWeight = FontWeight.w500
                margin(0.em)
            }
            +"« Most of the time, in mobile or end-user applications,"
            br {}
            +"SQL is not needed for embedded data management »"
        }

        styledSmall {
            css {
                display = Display.inlineBlock
                paddingTop = 1.em
                fontWeight = FontWeight.w200
                fontSize = 0.75.em
                transition(::opacity, 300.ms)
                opacity = if (props.state < 1) 0.0 else 0.8
            }
            +"-- Salomon BRYS (me)"
        }
    }

}
