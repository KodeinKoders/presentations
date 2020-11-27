package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
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
        flexRow {
            flexColumn(JustifyContent.center, Align.center) {
                css.margin(horizontal = 1.em)
                styledH2 {
                    css {
                        +kodein.display1
                        color = Color.kodein.kaumon
                    }
                    +"Fabrice Drouin"
                }
                flexRow(alignItems = Align.center) {
                    styledA(href = "https://twitter.com/acinq_co", target = "_blank") {
                        css {
                            +kodein.body
                            color = Color.kodein.kamethiste
                            textDecoration = TextDecoration.none
                        }
                        +"@acinq_co"
                    }
                }

                styledA(href = "mailto://fabrice@acinq.co", target = "_blank") {
                    css {
                        +kodein.body
                        color = Color.kodein.kamethiste
                        textDecoration = TextDecoration.none
                        marginTop = .5.em
                    }
                    +"fabrice.drouin@acinq.fr"
                }

                styledA(href = "https://acinq.co", target = "_blank") {
                    css {
                        +kodein.body
                        color = Color.kodein.kamethiste
                        textDecoration = TextDecoration.none
                        marginTop = .5.em
                    }
                    +"acinq.co"
                }
            }

            flexColumn(JustifyContent.center, Align.center) {
                css.margin(horizontal = 1.em)
                styledH2 {
                    css {
                        +kodein.display1
                        color = Color.kodein.kaumon
                    }
                    +"Salomon Brys"
                }
                flexRow(alignItems = Align.center) {
                    styledA(href = "https://twitter.com/salomonbrys", target = "_blank") {
                        css {
                            +kodein.body
                            color = Color.kodein.kamethiste
                            textDecoration = TextDecoration.none
                        }
                        +"@salomonbrys"
                    }

                    styledDiv {
                        css {
                            width = 1.rem
                            backgroundColor = Color.kodein.kinzolin
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
                styledA(href = "mailto://salomon@kodein.net", target = "_blank") {
                    css {
                        +kodein.body
                        color = Color.kodein.kamethiste
                        textDecoration = TextDecoration.none
                        marginTop = .5.em
                    }
                    +"salomon@kodein.net"
                }
                styledA(href = "https://kodein.net", target = "_blank") {
                    css {
                        +kodein.body
                        color = Color.kodein.kamethiste
                        textDecoration = TextDecoration.none
                        marginTop = .5.em
                    }
                    +"kodein.net"
                }
            }
        }

        styledA(href = "https://cutt.ly/ee-mp-btc") {
            css {
                +kodein.body
                color = Color.kodein.kuivre
                textDecoration = TextDecoration.none
                marginTop = 6.em
                alignSelf = Align.flexEnd
            }
            +"cutt.ly/ee-mp-btc"
        }
    }
}
