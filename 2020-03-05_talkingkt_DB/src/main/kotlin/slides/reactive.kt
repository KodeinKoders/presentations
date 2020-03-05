package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.w3c.dom.Range
import react.dom.br
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos


private val infos = SlideInfos(
        stateCount = 9
)

fun PresentationBuilder.reactive() = slide(infos) { props ->


    styledDiv {
        css {
            transition(::height, 500.ms)
            position = Position.relative
            width = 26.5.em
            height = if (props.state < 8) 16.5.em else 10.5.em
            universal {
                margin(0.em)
            }

            "h2" {
                transition(::opacity, 300.ms)
                transition("transform", 500.ms)
                fontSize = 1.2.em
            }

            "img" {
                transition(::opacity, 300.ms)
                transition("transform", 500.ms)
                maxWidth = 6.em
                +"large" {
                    maxWidth = 7.em
                }
                +"small" {
                    maxWidth = 5.em
                }
            }
        }

        fun CSSBuilder.absolute(top: LinearDimension, left: LinearDimension) {
            position = Position.absolute
            this.top = top
            this.left = left
        }
        fun CSSBuilder.states(states: IntRange, add: CSSBuilder.(Boolean) -> Unit = {}) {
            if (props.state in states) {
                opacity = 1.0
                add(true)
            } else {
                opacity = 0.0
                add(false)
            }
        }

        styledImg { // B -> NR
            css {
                transform { rotate(155.deg) }
                absolute(0.8.em, 4.5.em)
                states(1..99)
            }
            attrs.src = "images/arrow.webp"
        }

        styledImg { // NR -> B
            css {
                absolute(1.em, 5.em)
                states(3..6)
                if (props.state > 6) {
                    transform {
                        rotate((-90).deg)
                        translate((-2).em, 4.8.em)
                    }
                } else {
                    transform { rotate((-25).deg) }
                }
            }
            attrs.src = "images/arrow.webp"
        }

        styledImg { // NR -> DB/EB
            css {
                transform { rotate(25.deg) }
                absolute(6.6.em, 4.5.em)
                states(2..99)
            }
            attrs.src = "images/arrow.webp"
        }

        styledImg { // NS -> DB/EB
            css {
                transform { rotate(155.deg) }
                absolute(6.6.em, 15.8.em)
                states(5..99)
            }
            attrs.src = "images/arrow.webp"
        }

        styledImg { // DB/EB -> B
            css {
                +"large"
                transform { rotate((-90).deg) }
                absolute(3.3.em, 9.8.em)
                states(7..99) {
                    if (!it) transform { scale(0.5) }
                }
            }
            attrs.src = "images/arrow.webp"
        }

        styledImg { // EB -> DB
            css {
                +"small"
                absolute(11.5.em, 10.77.em)
                states(6..7) {
                    if (!it) transform { translateY(-2.7.em) }
                }
                transform { rotate(90.deg) }
            }
            attrs.src = "images/arrow.webp"
        }

        styledImg { // NS -> B
            css {
                absolute(1.em, 15.8.em)
                states(5..6)

                if (props.state > 6) {
                    transform {
                        rotate((-90).deg)
                        translate((-2).em, (-5).em)
                    }
                } else {
                    transform { rotate((-155).deg) }
                }
            }
            attrs.src = "images/arrow.webp"
        }

        styledH2 {
            css {
                absolute(0.em, 9.em)
            }
            +"Business"
        }

        styledH2 {
            css {
                absolute(3.2.em, 0.em)
                states(1..99)
            }
            +"Network"
            br {}
            +"Request"
        }

        styledH2 {
            css {
                absolute(7.4.em, 8.8.em)
                states(2..99)
                if (props.state in 6..7) {
                    transform { translateY(5.4.em) }
                }
            }
            +"Database"
        }

        styledH2 {
            css {
                absolute(3.2.em, 18.em)
                states(4..99)
            }
            +"Network"
            br {}
            +"Service"
        }

        styledH2 {
            css {
                absolute(7.4.em, 8.7.em)
                states(6..7) {
                    if (!it) transform { scale(1.5) }
                }
            }
            +"Event Bus"
        }

    }

}
