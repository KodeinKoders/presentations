package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.*
import ws.utils.opacity
import ws.utils.s
import ws.utils.transform

fun PresentationBuilder.done() = slide(stateCount = 2) { props ->

    styledDiv {
        css {
            position = Position.relative
        }

        styledH1 {
            css {
                margin(0.em)
            }
            +"Using C in Kotlin:"
        }
        styledH2 {
            css {
                margin(0.em, bottom = 1.em)
            }
            +"managing native dependencies"
            br {}
            +"in a multi-platform project"
        }

        styledImg(src = "images/jobDone.png") {
            css {
                position = Position.absolute
                transform { rotate((-15).deg) }
                height = 10.em;
                top = (-0.3).em;
                left = 2.em;
                filter = "drop-shadow(0px 0 0.2em black)"

                transition("opacity", 0.3.s, Timing.easeIn)
                transition("transform", 0.3.s, Timing.easeIn)
                if (props.state < 1) {
                    transform {
                        scale(1.5)
                    }
                    opacity = 0.0
                }
            }
        }
    }
}
