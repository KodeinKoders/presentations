package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.*
import ws.charter.kodein
import ws.utils.flexColumn
import ws.utils.flexRow

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

        flexRow(JustifyContent.center, Align.center) {

            styledA(href = "https://twitter.com/romainbsl", target = "_blank") {
                css {
                    +kodein.body
                    color = Color.kodein.kamethiste
                    textDecoration = TextDecoration.none
                }
                +"@romainbsl"
            }

            styledDiv {
                css {
                    width = 1.rem
                    backgroundColor = Color.kodein.kamethiste
                    height = 0.05.rem
                    margin(horizontal = .5.rem)
                }
            }

            styledA(href = "https://twitter.com/KodeinKoders", target = "_blank") {
                css {
                    +kodein.body
                    color = Color.kodein.kamethiste
                    textDecoration = TextDecoration.none
                }
                +"@KodeinKoders"
            }
        }

        flexRow(JustifyContent.flexEnd) {
            css {
                +kodein.body
                marginTop = 3.em
            }
            styledA(href = "https://cutt.ly/ee-kmp") {
                css {
                    color = Color.kodein.orange
                    textDecoration = TextDecoration.none
                    alignSelf = Align.flexEnd
                }
                +"cutt.ly/ee-kmp"
            }
        }
    }
}
