package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import ws.utils.d
import ws.utils.opacity


private val slideStyle: CSSBuilder.(Int) -> Unit = { state ->
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

        span {
            flexGrow = 1.0
            flexBasis = FlexBasis.zero
            margin(0.4.em)
        }
    }

}

fun PresentationBuilder.layers() = slide(
        stateCount = 10,
        style = slideStyle
) { props ->
    d("vert root") {
        d("layer a7") {
            d("entry platform") {
                opacity {
                    when (props.state) {
                        in 0..5 -> 0.0
                        6 -> 1.0
                        else -> 0.2
                    }
                }
                +"Android"
            }
            d("entry platform") {
                opacity {
                    when (props.state) {
                        in 0..5 -> 0.0
                        6 -> 1.0
                        else -> 0.2
                    }
                }
                +"JVM"
            }
            d("entry platform") {
                opacity {
                    when (props.state) {
                        in 0..6 -> 0.0
                        7 -> 1.0
                        else -> 0.2
                    }
                }
                +"iOS"
            }
            d("entry platform") {
                opacity {
                    when (props.state) {
                        in 0..6 -> 0.0
                        7 -> 1.0
                        else -> 0.2
                    }
                }
                +"Native"
            }
            d("entry platform") {
                opacity {
                    when (props.state) {
                        in 0..7 -> 0.0
                        8 -> 1.0
                        else -> 0.2
                    }
                }
                +"Web"
            }
            d("entry platform") {
                opacity {
                    when (props.state) {
                        in 0..7 -> 0.0
                        8 -> 1.0
                        else -> 0.2
                    }
                }
                +"Deno"
            }
        }

        d("layer") {
            opacity {
                when (props.state) {
                    in 0..4 -> 0.0
                    in 5..8 -> 1.0
                    else -> 0.2
                }
            }
            d("entry kotlin") { +"Application business" }
        }

        d("layer") {
            opacity { if (props.state >= 4) 1.0 else 0.0 }
            d("entry kotlin") { +"Interface" }
        }

        d("layer") {
            d("vert in") {
                opacity {
                    when (props.state) {
                        0 -> 0.0
                        7, 8 -> 0.2
                        else -> 1.0
                    }
                }
                d("entry kotlin") {
                    +"Kotlin JNI"
                }
                d("entry native") {
                    +"C++ JNI"
                }
            }
            d("entry kotlin") {
                opacity {
                    when (props.state) {
                        in 0..1 -> 0.0
                        6, 8 -> 0.2
                        else -> 1.0
                    }
                }
                +"Kotlin/Native"
                br {}
                +"C-Interop"
            }
            d("entry kotlin") {
                opacity {
                    when (props.state) {
                        in 0..2 -> 0.0
                        6, 7 -> 0.2
                        else -> 1.0
                    }
                }
                +"Kotlin/Js"
                br {}
                +"Facade"
            }
        }

        d("layer") {
            d("entry native") { +"Native library" }
        }

    }

}
