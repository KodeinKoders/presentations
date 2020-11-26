package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.RBuilder
import react.functionalComponent
import styled.*
import ws.charter.kodein
import ws.utils.*
import ws.utils.transform


fun PresentationBuilder.next() = slide(
    stateCount = 2,
    inTransitions = Flip
) { props ->
    titledSlide("Where to go next?") {
        styledA(href = "https://kotlinlang.org/lp/mobile/", target = "_blank") {
            css {
                textDecoration = TextDecoration.none
            }

            flexColumn(JustifyContent.center, Align.center) {
                css {
                    color = Color.kodein.korail
                    transition(duration = .5.s)
                    if (props.state < 1) {
                        opacity = 0.0
                    }
                }

                +"KMM Portal"
                styledDiv {
                    css {
                        width = 5.em
                        backgroundColor = Color.kodein.kamethiste
                        height = 0.05.em
                    }
                }
                styledImg(src = "images/kmmportal.png") {
                    css {
                        transition(duration = .5.s)
                        if (props.state < 1) {
                            transform { scale(1.5) }
                        }
                        height = 12.em
                        borderRadius = 1.rem
                        margin(1.em)
                        boxShadow(Color.kodein.krouille, blurRadius = 0.5.em)
                    }
                }
            }
        }
    }
}
