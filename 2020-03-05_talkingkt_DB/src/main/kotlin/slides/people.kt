package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import react.dom.h1
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes


private val infos = SlideInfos(
        stateCount = 5,
        notes = notes {
            0 ("Jane")
            1 ("John")
            2 ("Jill")
            3 ("Jack")
        }
)

fun PresentationBuilder.people() = slide(infos) { props ->

    fun CSSBuilder.portrait() {
        position = Position.absolute
        width = 100.pct
        height = 100.pct
        top = 0.em
        left = 0.em

        transition(::opacity, 500.ms)
        transition("transform", 500.ms)
    }

    styledDiv {
        css {
            width = 15.em
            height = 15.em
            position = Position.relative
        }

        styledH3 {
            css {
                position = Position.absolute
                fontWeight = FontWeight.w200
                transform { rotate((-90).deg) }
                fontSize = 5.em
                top = (-0.1).em
                left = 1.3.em
                if (props.state < 4) opacity = 0.0
            }
            +"{"
        }

        styledImg {
            key = "jane"
            css {
                portrait()
                if (props.state >= 1) {
                    transform {
                        translate((-5).em, (-5).em)
                        scale(0.5)
                    }
                }
            }
            attrs.src = "images/p-jane.jpg"
        }

        styledImg {
            key = "john"
            css {
                portrait()
                if (props.state < 1) opacity = 0.0
//                opacity = if (props.state < 1) 0.0 else 1.0
                if (props.state >= 2) {
                    transform {
                        translate(5.em, (-5).em)
                        scale(0.5)
                    }
                }
            }
            attrs.src = "images/p-john.jpg"
        }

        styledImg {
            key = "jill"
            css {
                portrait()
                if (props.state < 2) opacity = 0.0
                if (props.state >= 3) {
                    transform {
                        translate((-5).em, 5.em)
                        scale(0.5)
                    }
                }
            }
            attrs.src = "images/p-jill.jpg"
        }

        styledImg {
            key = "jack"
            css {
                portrait()
                if (props.state < 3) opacity = 0.0
                if (props.state >= 4) {
                    transform {
                        translate(5.em, 5.em)
                        scale(0.5)
                    }
                }
            }
            attrs.src = "images/p-jack.jpg"
        }
    }

}
