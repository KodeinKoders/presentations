package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import react.dom.br
import react.dom.h1
import react.dom.li
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes


private val infos = SlideInfos(
        stateCount = 11
)

fun PresentationBuilder.path() = slide(infos) { props ->

    styledDiv {
        css {
            width = 100.pct
            height = 100.pct
            position = Position.relative
            width = 18.6.em
            height = 17.5.em
            universal {
                margin(0.em)
                transition(::opacity, 300.ms)
            }

            "h2" {
                fontSize = 1.2.em
            }

            "h3" {
                fontSize = 1.05.em
                fontWeight = FontWeight.w400
                put("text-shadow", "black 0 0 0.15em")
            }

            "h4" {
                fontSize = 0.9.em
                fontWeight = FontWeight.w200
            }

            "img" {
                maxWidth = 8.em
            }
        }

        fun CSSBuilder.absolute(top: LinearDimension, left: LinearDimension) {
            position = Position.absolute
            this.top = top
            this.left = left
        }
        fun CSSBuilder.minState(min: Int, stays: Boolean = false) {
            opacity = if (props.state < min) 0.0 else if (stays || props.state < 10) 1.0 else 0.2
        }

        styledImg { // Object -> Binary Data
            css {
                transform { rotate(120.deg) }
                absolute(2.8.em, 1.em)
                minState(1)
            }
            attrs.src = "images/arrow.webp"
        }
        styledImg { // Object -> Metadata
            css {
                transform { rotate(60.deg) }
                absolute(2.8.em, 6.4.em)
                minState(3)
            }
            attrs.src = "images/arrow.webp"
        }
        styledImg { // Binary Data -> Database
            css {
                transform { rotate(60.deg) }
                absolute(10.8.em, 1.em)
                minState(7)
            }
            attrs.src = "images/arrow.webp"
        }
        styledImg { // Metadata -> Database
            css {
                transform { rotate(120.deg) }
                absolute(10.8.em, 6.4.em)
                minState(7)
            }
            attrs.src = "images/arrow.webp"
        }
        styledImg { // Database -> Binary Data
            css {
                transform { rotate((-120).deg) }
                absolute(10.8.em, 1.em)
                minState(9)
            }
            attrs.src = "images/arrow.webp"
        }
        styledImg { // Binary Data -> Object
            css {
                transform { rotate((-60).deg) }
                absolute(2.8.em, 1.em)
                minState(9)
            }
            attrs.src = "images/arrow.webp"
        }

        styledH2 {
            css {
                absolute(0.em, 5.em)
                minState(0)
            }
            +"Object"
        }

        styledH2 {
            css {
                absolute(6.8.em, 0.em)
                minState(1)
            }
            +"Binary Data"
        }

        styledH2 {
            css {
                absolute(6.8.em, 8.em)
                minState(3)
            }
            +"Metadata"
        }

        styledH2 {
            css {
                absolute(13.6.em, 4.4.em)
                minState(7)
            }
            +"Database"
        }

        styledH4 {
            css {
                absolute(7.5.em, 6.em)
                minState(6)
            }
            +"Model Layer"
        }

        styledH4 {
            css {
                absolute(11.em, 6.4.em)
                minState(8)
            }
            +"Data Layer"
        }

        styledH3 {
            css {
                absolute(4.em, 2.5.em)
                minState(2, true)
            }
            +"Serializer"
        }

        styledH3 {
            css {
                absolute(3.4.em, 8.5.em)
                minState(5, true)
            }
            +"Metadata"
            br {}
            +"extractor"
        }

        styledUl {
            css {
                minState(4)
                absolute(9.3.em, 20.em)
                padding(0.em)
                fontSize = 0.8.em
                fontWeight = FontWeight.w200
                listStyleType = ListStyleType.circle
            }

            li { +"Type" }
            li { +"ID" }
            li { +"[Index]*" }
        }

    }

}
