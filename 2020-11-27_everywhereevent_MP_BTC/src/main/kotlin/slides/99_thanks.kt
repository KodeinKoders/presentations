package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import styled.css
import styled.styledA
import styled.styledH1
import styled.styledH2
import ws.charter.kodein
import ws.utils.flexColumn

fun PresentationBuilder.thanks() = slide(stateCount = 1) {

    flexColumn(justifyContent = JustifyContent.center, alignItems = Align.center) {
        styledH1 {
            css {
                +kodein.display3
                specific {
                    fontWeight = FontWeight.lighter
                }
                color = Color.kodein.orange
            }
            +"Thank You!"
        }
        styledH2 {
            css {
                +kodein.display1
                color = Color.kodein.kaumon
            }
            +"Romain Boisselle"
        }
        styledA(href = "https://twitter.com/romainbsl", target = "_blank") {
            css {
                +kodein.body
                color = Color.kodein.kamethiste
                textDecoration = TextDecoration.none
            }
            +"@romainbsl"
        }

    }
}
