package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.TextDecorationLine
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import react.dom.li
import react.dom.ul
import styled.*
import ws.charter.kodein
import ws.utils.*

fun PresentationBuilder.kotlin() = slide(
    stateCount = 5,
    inTransitions = Flip,
    inTransitionDuration = 1000
) { props ->
titledSlide("What is Kotlin ?") {
        flexRow {
            css {
                "p" {
                    textAlign = TextAlign.left
                    color = Color.kodein.korail
                    margin(horizontal = .5.rem, vertical = 0.em)
                }

                "ul" {
                    listStyleType = ListStyleType.disc
                    color = Color.kodein.kaumon
                }
            }

            flexColumn(JustifyContent.left) {
                css { width = 50.pct }

                styledP {
                    opacity(props.state >= 1)
                    +"JVM"
                    styledDiv {
                        css {
                            width = 1.85.em
                            backgroundColor = Color.kodein.kamethiste
                            height = 0.05.em
                        }
                    }
                }
                ul {
                    styledLi {
                        opacity(props.state >= 2)
                        +"Desktop"
                    }
                    styledLi {
                        opacity(props.state >= 3)
                        +"Android"
                    }
                    styledLi {
                        opacity(props.state >= 3)
                        +"Server"
                    }
                }
                styledP {
                    opacity(props.state >= 4)
                    +"JS"
                    styledDiv {
                        css {
                            width = 1.em
                            backgroundColor = Color.kodein.kamethiste
                            height = 0.025.em
                        }
                    }
                }
                styledUl {
                    opacity(props.state >= 4)
                    li { +"Browser" }
                    li { +"Node" }
                }
            }
            flexColumn(JustifyContent.left) {
                css { width = 50.pct }

                styledP {
                    opacity(props.state >= 4)
                    +"Native"
                    styledDiv {
                        css {
                            width = 2.85.em
                            backgroundColor = Color.kodein.kamethiste
                            height = 0.05.em
                        }
                    }
                }
                styledUl {
                    opacity(props.state >= 4)
                    li { +"Linux" }
                    li { +"Windows" }
                    li { +"MacOS / iOS / watchOS" }
                }
            }
        }
    }
    styledImg(src = "images/kotlin-logo.svg") {
        css {
            height = 4.em
            position = Position.absolute
            right = 2.em
            bottom = 2.em
        }
    }
}
