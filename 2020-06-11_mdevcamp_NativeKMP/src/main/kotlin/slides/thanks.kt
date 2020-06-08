package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import org.kodein.kpres.PresentationBuilder
import react.dom.a
import react.dom.br
import react.dom.h1
import styled.*


fun PresentationBuilder.thanks() = slide {

    h1 { +"Thank you!" }

    styledDiv {
        css {
            "a" {
                color = Color("#A0C7ff")
                textDecoration = TextDecoration.none
            }
        }
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
                        a(href = "https://twitter.com/salomonbrys") {
                            +" @salomonbrys"
                        }
                        br {}
                        a(href = "mailto:salomon@kodein.net") {
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

        styledP {
            css {
                margin(top = 2.5.em, bottom = 4.em)
                fontSize = 0.8.em
            }
            +"kodeinkoders.github.io/pres/2020-06-11_mdevcamp_NativeKMP/"

            br {}
            br {}

            a(href = "https://github.com/SalomonBrys/demo-native-kmp") {
                +"github.com/SalomonBrys/demo-native-kmp"
            }
        }

    }
}
