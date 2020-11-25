package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import react.dom.a
import react.dom.br
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.flexColumn
import ws.utils.flexRow

fun PresentationBuilder.intro() = slide {
    flexColumn(justifyContent = JustifyContent.flexStart) {
        flexRow(alignItems = Align.center) {
            logo(
                light = "Koders",
                logoColor = "kinzolin",
                fontColor = Color.kodein.kinzolin,
                href = "https://kodein.net",
                zoom = .4
            )

            styledA(href = "https://acinq.co", target = "_blank") {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    alignItems = Align.center
                    textDecoration = TextDecoration.none
                }
                styledImg(src = "images/acinq-logo.svg") {
                    css {
                        height = 2.2.em
                        marginLeft = 1.5.em
                        opacity = .4
                    }
                }
                styledImg(src = "images/acinq-text.svg") {
                    css {
                        height = 1.em
                        marginLeft = 0.25.em
                        opacity = .4
                    }
                }
            }
        }

        flexColumn {
            css {
                padding(top = 2.rem, left = 4.2.em)
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
                +"A Multi-platform Bitcoin Lightning node"
            }

            flexColumn(alignItems = Align.center) {
                css {
                    padding(top = 3.em)
                    +kodein.body
                    color = Color.kodein.kaumon
                }

                flexRow(alignItems = Align.center) {
                    css {
                        width = 25.em
                    }

                    +"Fabrice Drouin"

                    styledDiv {
                        css {
                            width = 1.rem
                            backgroundColor = Color.kodein.kinzolin
                            height = 0.05.rem
                            margin(horizontal = .5.rem)
                        }
                    }

                    styledA(href = "https://twitter.com/acinq_co", target = "_blank") {
                        css {
                            +kodein.body
                            color = Color.kodein.kamethiste
                            textDecoration = TextDecoration.none
                        }
                        +"@acinq_co"
                    }
                }

                flexRow(alignItems = Align.center) {
                    css {
                        width = 25.em
                    }

                    +"Salomon Brys"

                    styledDiv {
                        css {
                            width = 1.rem
                            backgroundColor = Color.kodein.kinzolin
                            height = 0.05.rem
                            margin(horizontal = .5.rem)
                        }
                    }

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
            }
        }

        flexRow(JustifyContent.spaceBetween) {
            css {
                +kodein.body
                marginTop = 3.em
            }
            styledA(href = "https://everywhereevent.com") {
                css {
                    color = Color.kodein.kinzolin
                    textDecoration = TextDecoration.none
                    alignSelf = Align.flexEnd
                    textAlign = TextAlign.left
                }
                +"Everywhere Event"
                br {}
                +"nov. 27, 2020"
            }
            styledA(href = "https://cutt.ly/ee-mp-btc") {
                css {
                    color = Color.kodein.orange
                    textDecoration = TextDecoration.none
                    alignSelf = Align.flexEnd
                }
                +"cutt.ly/ee-mp-btc"
            }
        }
    }
}
