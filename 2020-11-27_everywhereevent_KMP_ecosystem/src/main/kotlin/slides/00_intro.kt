package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.flexColumn
import ws.utils.flexRow

fun PresentationBuilder.intro() = slide {
    flexColumn(justifyContent = JustifyContent.flexStart) {
        flexRow {
            logo(
                light = "Koders",
                logoColor = "kinzolin",
                fontColor = Color.kodein.kinzolin,
                href = "https://kodein.net",
                zoom = .4
            )
        }

        flexColumn {
            css {
                padding(top = 2.rem, left = 5.em)
            }

            styledP {
                css {
                    +kodein.display3
                    specific {
                        fontWeight = FontWeight.lighter
                        textAlign = TextAlign.start
                    }
                    color = Color.kodein.orange
                    margin(0.5.em)
                }
                +"State of Kotlin/Multiplatform ecosystem"
            }

            flexRow(justifyContent = JustifyContent.center, alignItems = Align.center) {
                css {
                    +kodein.body
                    color = Color.kodein.kaumon
                    padding(top = 4.rem)
                }
                +"Romain Boisselle"

                styledDiv {
                    css {
                        width = 1.rem
                        backgroundColor = Color.kodein.kamethiste
                        height = 0.05.rem
                        margin(horizontal = .5.rem)
                    }
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
    }
}
