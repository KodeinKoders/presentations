package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import react.dom.*
import styled.css
import styled.styledLi
import styled.styledUl
import ws.charter.kodein
import ws.utils.light
import ws.utils.medium
import ws.utils.opacity
import ws.utils.titledSlide

fun PresentationBuilder.serialization() = slide(
    stateCount = 6
) { props ->
    titledSlide("Serialization") {
        styledUl {
            css {
                color = Color.kodein.cute
                fontWeight = FontWeight.light
                "b" {
                    fontWeight = FontWeight.medium
                    color = Color.kodein.orange
                }
                "li span" {
                    paddingBottom = .5.em
                    display = Display.inlineBlock
                }
            }
            styledLi {
                opacity(props.state >= 1)
                span {
                    +"Lightning channels are "
                    b { +"very large" }
                    +" structures!"
                }
            }
            styledLi {
                opacity(props.state >= 2)
                css.paddingTop = 1.em
                span { +"KotlinX Serialization..." }
                ul {
                    styledLi {
                        opacity(props.state >= 3)
                        span {
                            +"...generates serializers at "
                            b { +"compile time" }
                            +"."
                        }
                    }
                    styledLi {
                        opacity(props.state >= 4)
                        span { +"...automates a lot via the @Serializable annotation" }
                    }
                    styledLi {
                        opacity(props.state >= 5)
                        span {
                            +"...still needs some "
                            b { +"manual polymorphism defintions" }
                            +"."
                        }
                    }
                }
            }
        }
    }
}
