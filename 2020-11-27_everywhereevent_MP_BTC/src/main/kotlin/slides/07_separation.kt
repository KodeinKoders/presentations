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

fun PresentationBuilder.separation() = slide(
    stateCount = 9
) { props ->
    titledSlide("Separation of concern") {
        flexRow {
            css {
                width = 100.pct
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
                    flexBasis = FlexBasis.zero

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
                    css {
                        "p" {
                            flexGrow = 1.0
                            flexBasis = FlexBasis.zero
                        }
                    }
                    styledP {
                        attrs.classes += "o"
                        opacity(props.state >= 7)
                        +"Phoenix-iOS"
                    }
                    styledP {
                        attrs.classes += "o"
                        opacity(props.state >= 7)
                        +"Phoenix-Android"
                    }
                }
                styledP {
                    attrs.classes += "o"
                    opacity(props.state >= 7)
                    +"Phoenix-KMM"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 5)
                    +"Eclair-KMP"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 3)
                    +"Bitcoin-KMP"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 1)
                    +"Secp256k1-KMP"
                }
            }
            flexColumn {
                css {
                    flexGrow = 1.0
                    flexBasis = FlexBasis.zero
                    "p" {
                        textAlign = TextAlign.left
                        +"o" {
                            color = Color.kodein.korail
                        }
                        +"p" {
                            color = Color.kodein.kamethiste
                        }
                    }
                }
                styledP {
                    css.color = Color.transparent
                    +"."
                }
                styledP {
                    attrs.classes += "o"
                    opacity(props.state >= 8)
                    +"https://github.com/ACINQ/phoenix-kmm *"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 6)
                    +"https://github.com/ACINQ/eclair-kmp *"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 4)
                    +"https://github.com/ACINQ/bitcoin-kmp *"
                }
                styledP {
                    attrs.classes += "p"
                    opacity(props.state >= 2)
                    +"https://github.com/ACINQ/secp256k1-kmp"
                }
            }
        }
    }
}
