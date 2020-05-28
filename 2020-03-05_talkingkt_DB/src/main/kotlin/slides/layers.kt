package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.div
import react.dom.span


private val slideStyle: CSSBuilder.(Int) -> Unit = { state ->
    div {
        +"root" {
            fontSize = 0.88.em
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
        }

        +"layer" {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignSelf = Align.stretch
        }

        +"entry" {
            padding(0.2.em)
            margin(0.2.em)
            borderRadius = 0.2.em
            flexGrow = 2.0
            flexBasis = FlexBasis.zero
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            boxShadow(Color.black, blurRadius = 0.5.em)

            +"small" {
                flexGrow = 1.0
            }

            +"large" {
                flexGrow = 4.0
            }
        }

        +"native" {
            backgroundColor = Color.dimGray
        }

        +"business" {
            backgroundColor = Color("#2281fb")
        }

        +"platform" {
            backgroundColor = Color("#89AB0D")
        }

        +"middleware" {
            backgroundImage = Image("linear-gradient(to bottom right, #E8441F, #921F81)")
        }

        span {
            flexGrow = 1.0
            flexBasis = FlexBasis.zero
            margin(0.4.em)
        }

        for (s in 0..7) {
            ".a$s" {
                transition(::opacity, 300.ms)
                opacity = if (state >= s) 1.0 else 0.0
            }
        }

        ".a5" {
            transition("transform", 500.ms)

            if (state >= 11)
                transform {
                    scale(1.4)
                }

            ".entry" {
                padding(0.em)
                margin(0.2.em, 0.em)

                ".business" {
                    padding(0.2.em)
                    flexGrow = 1.0
                    borderRadius = 0.2.em
                    transition(::backgroundColor, 500.ms)
                    if (state >= 10) {
                        backgroundColor = Color("#2281fb00")
                    }
                }
            }
        }

        ".m1" {
            transition(::height, 300.ms)
            transition(::opacity, 300.ms)
            height = if (state < 8) 0.em else 2.em
            opacity = if (state < 8) 0.0 else 1.0
        }

        ".m2" {
            transition(::height, 300.ms)
            transition(::opacity, 300.ms)
            height = if (state < 9) 0.em else 2.em
            opacity = if (state < 9) 0.0 else 1.0
        }
    }
}

fun PresentationBuilder.layers() = slide(
        stateCount = 12,
        style = slideStyle
) {
    div("vert root") {
        div("layer a7") {
            div("entry platform") { +"iOS & Native" }
            div("entry platform") { +"Android & Desktop" }
        }
        div("entry large business a6") { +"API" }

        div("layer m2") {
            span {}
            div("entry middleware") { +"Model Middlewares" }
            span {}
        }

        div("layer a5") {
            span {}
            div("entry middleware") {
                div("business") {
                    +"Cache"
                }
            }
            span {}
        }

        div("layer") {
            div("entry small business a4") { +"KX.Serialization" }
            div("entry business a3") { +"Model" }
            div("entry small business a4") { +"Kryo" }
        }

        div("layer m1") {
            span {}
            div("entry middleware") { +"Data Middlewares" }
            span {}
        }

        div("layer a2") {
            span {}
            div("entry business") { +"Data" }
            span {}
        }

        div("layer a1") {
            span {}
            div("vert i") {
                div("entry native") {
                    +"Kotlin JNI"
                }
                div("entry native") {
                    +"C++ JNI"
                }
            }
            div("entry small native") {
                +"Kotlin/Native"
                br {}
                +"C-Interop"
            }
            span {}
        }

        div("layer") {
            span {}
            div("entry native") { +"Google LevelDB" }
            span {}
        }

    }

}
