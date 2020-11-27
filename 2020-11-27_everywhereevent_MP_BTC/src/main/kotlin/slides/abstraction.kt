package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.classes
import org.kodein.kpres.PresentationBuilder
import react.dom.*
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledP
import ws.charter.kodein
import ws.utils.*

fun PresentationBuilder.abstraction() = slide(
    stateCount = 6
) { props ->
    titledSlide("Shared abstraction") {
        flexRow {
            css {
                width = 100.pct - 2.em
                padding(1.em)
                "p" {
                    +kodein.body
                    padding(1.em)
                    border(.15.em, BorderStyle.solid, Color.transparent)
                    borderRadius = .6.em
                    margin(.4.em)
                }
            }
            flexColumn {
                css {
                    flexGrow = 1.0

                    "p" {
                        +"o" {
                            borderColor = Color.kodein.orange
                            color = Color.kodein.kaumon
                            backgroundColor = Color.kodein.kuivre
                        }
                        +"p" {
                            borderColor = Color.kodein.purple
                            color = Color.kodein.klycine
                            backgroundColor = Color.kodein.kinzolin
                        }
                    }
                }
                flexRow {
                    opacity(props.state >= 4)
                    css {
                        "p" {
                            flexGrow = 1.0
                            flexBasis = FlexBasis.zero
                        }
                    }
                    styledP {
                        attrs.classes += "o"
                        +"SwiftUI"
                    }
                    styledP {
                        attrs.classes += "o"
                        +"Jetpack Compose"
                    }
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 3)
                    +"Behavior"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 2)
                    +"Business logic"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 1)
                    +"Business primitives"
                }
            }
            flexColumn {
                css {
                    "p" {
                        textAlign = TextAlign.left
                        +"o" {
                            color = Color.kodein.korail
                        }
                        +"p" {
                            color = Color.kodein.kamethiste
                        }
                        marginRight = 0.em
                        paddingRight = 0.em
                    }
                }
                styledP {
                    attrs.classes += "o"
                    opacity(props.state >= 4)
                    +"Declarative UI"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 3)
                    +"Model View Intent"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 2)
                    +"Actor based"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 1)
                    +"Open sourced APIs"
                }
            }
            styledDiv {
                css {
                    fontSize = 7.em
                    fontWeight = FontWeight.lighter
                    paddingTop = 5.2.rem
                    color = Color.kodein.klycine
                    opacity(props.state >= 5)
                }
                +"}"
            }
            styledDiv {
                css {
                    fontSize = 2.5.em
                    fontWeight = FontWeight.regular
                    color = Color.kodein.klycine
                    paddingTop = 10.5.rem
                    opacity(props.state >= 5)
                }
                +"95%"
            }
        }
    }
}
