package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import styled.*
import ws.comp.logo
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes


private val infos = SlideInfos(
        stateCount = 2,
        containerStyle = {
            ".inner-container" {
                backgroundColor = if (it < 1) Color("#46AF6D") else Color("#1B93D2")
            }
        }
)

fun PresentationBuilder.kodeinDB() = slide(infos) { props ->

    logo(
            division = {
                styledSpan {
                    css {
                        transition(::fontSize, 500.ms)
                        fontSize = if (props.state >= 1) 1.em else 0.em
                        verticalAlign = VerticalAlign.middle
                    }
                    +"DB"
                }
                styledSpan {
                    css {
                        transition(::fontSize, 500.ms)
                        fontSize = if (props.state <= 0) 1.em else 0.em
                        verticalAlign = VerticalAlign.middle
                    }
                    +"Framework"
                }
            },
            zoom = 0.8
    ) {
        styledP {
            css {
                padding(0.em)
                margin(0.em)
            }
            +"painless "
            styledImg(src = "images/kotlin-white.svg") {
                css { height = 0.65.em }
            }
            +" "
            styledSpan {
                css {
                    transition(::fontSize, 500.ms)
                    fontSize = if (props.state >= 1) 1.em else 0.em
                    verticalAlign = VerticalAlign.middle
                }
                +"embedded NoSQL"
            }
            styledSpan {
                css {
                    transition(::fontSize, 500.ms)
                    fontSize = if (props.state <= 0) 1.em else 0.em
                    verticalAlign = VerticalAlign.middle
                }
                +"multiplatform kotlin"
            }
        }
    }

}
