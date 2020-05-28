package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import styled.css
import styled.styledDiv
import styled.styledH3
import styled.styledImg


fun PresentationBuilder.people() = slide(
        stateCount = 5,
        notes = notes {
            0 ("Jane")
            1 ("John")
            2 ("Jill")
            3 ("Jack")
        }
) { props ->

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
