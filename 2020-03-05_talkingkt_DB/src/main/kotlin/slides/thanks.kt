package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.dom.br
import react.dom.h1
import react.dom.h2
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos


private val infos = SlideInfos(
)

fun PresentationBuilder.thanks() = slide(infos) {

    h1 { +"Thank you!" }

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            width = 20.em
            justifyContent = JustifyContent.spaceBetween
            alignItems = Align.center
        }

        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.column
            }

            styledH3 {
                css {
                    fontSize = 0.8.em
                    textAlign = TextAlign.left
                }
                +"Salomon BRYS"
                styledSpan {
                    css {
                        fontWeight = FontWeight.w400
                    }
                    br {}
                    styledA(href = "https://twitter.com/salomonbrys") {
                        css {
                            color = Color("#007bfa")
                            textDecoration = TextDecoration.none
                        }
                        +" @salomonbrys"
                    }
                }
            }
        }

        styledImg(src = "images/talking_kt.png") {
            css {
                height = 4.5.em
            }
        }
    }

    styledA(href = "https://github.com/Kodein-Framework/Kodein-DB") {
        css {
            margin(top = 1.5.em, bottom = 2.em)
            color = Color("#007bfa")
            textDecoration = TextDecoration.none
        }
        +"github.com/Kodein-Framework/Kodein-DB"
    }

}
