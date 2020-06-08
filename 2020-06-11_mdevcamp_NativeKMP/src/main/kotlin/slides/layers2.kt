package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.unsafe
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.css
import ws.utils.d
import ws.utils.fontSize
import ws.utils.opacity
import ws.utils.s


private val slideStyle: CSSBuilder.(Int) -> Unit = {
    div {
        +"root" {
            fontSize = 0.85.em
            +"vert" {
                flexGrow = 0.0
            }
        }

        +"vert" {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignSelf = Align.stretch
            flexGrow = 1.0
            flexBasis = FlexBasis.zero

            +"in" {
                padding(0.em, 0.2.em)

                div {
                    +"entry" {
                        margin(0.2.em, 0.em)
                    }
                }
            }
        }

        +"layer" {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignSelf = Align.stretch
        }

        +"entry" {
            padding(0.2.em, 0.em)
            margin(0.2.em)
            borderRadius = 0.2.em
            flexGrow = 1.0
            flexBasis = FlexBasis.zero
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            boxShadow(Color.black, blurRadius = 0.5.em)
        }

        +"native" {
            backgroundColor = Color.dimGray
        }

        +"kotlin" {
            backgroundColor = Color("#2281fb")
        }

        +"platform" {
            backgroundColor = Color("#89AB0D")
        }
    }

}

fun PresentationBuilder.layers2() = slide(
        stateCount = 2,
        style = slideStyle
) { props ->
    d("vert root") {
        d("layer a7") {
            opacity { 0.5 }
            d("entry platform") {
                +"Android"
            }
            d("entry platform") {
                +"JVM"
            }
            d("entry platform") {
                +"iOS"
            }
            d("entry platform") {
                +"Native"
            }
            d("entry platform") {
                +"Web"
            }
            d("entry platform") {
                +"Deno"
            }
        }

        d("layer") {
            opacity { 0.5 }
            d("entry kotlin") { +"Application business" }
        }

        d("layer") {
            css {
                transition("height", 0.3.s)
                height = if (props.state >= 1) 1.98.em else 0.em
                opacity(props.state >= 1)
            }
            d("entry kotlin") { +"Common façade" }
        }

        d("layer") {
            d("entry kotlin") {
                s {
                    +"Common "
                    s {
                        fontSize(props.state >= 1)
                        +"low-level "
                    }
                    +"interface"
                }
            }
        }

        d("layer") {
            d("vert in") {
                d("entry kotlin") {
                    +"Kotlin JNI"
                }
                d("entry native") {
                    +"C++ JNI"
                }
            }
            d("entry kotlin") {
                +"Kotlin/Native"
                br {}
                +"C-Interop"
            }
            d("entry kotlin") {
                +"Kotlin/Js"
                br {}
                +"Façade"
            }
        }

        d("layer") {
            d("entry native") { +"Native library" }
        }

    }

}
