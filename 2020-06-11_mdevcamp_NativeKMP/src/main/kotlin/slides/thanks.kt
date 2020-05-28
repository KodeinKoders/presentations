package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.*


fun PresentationBuilder.thanks() = slide {

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
                    br {}
                    styledA(href = "mailto:salomon@kodein.net") {
                        css {
                            color = Color("#007bfa")
                            textDecoration = TextDecoration.none
                        }
                        +" salomon@kodein.net"
                    }
                }
            }
        }

        styledImg(src = "images/mdevcamp.svg") {
            css {
                height = 2.em
            }
        }
    }

    styledP() {
        css {
            margin(top = 2.5.em, bottom = 4.em)
            fontSize = 0.7.em
        }
        +"kodeinkoders.github.io/pres/2020-06-11_mdevcamp_NativeKMP"
    }

}
