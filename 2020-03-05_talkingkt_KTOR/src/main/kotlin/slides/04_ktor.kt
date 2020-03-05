package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import react.RBuilder
import react.child
import react.dom.br
import react.dom.div
import react.dom.span
import react.functionalComponent
import styled.*
import ws.kpres.*
import ws.utils.*

private val WhatIsKtor by functionalComponent<SlideContentProps> { props ->
    titledContent("What is Ktor?") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletPoint(currentState, 1, "Server Engine")
            bulletPoint(currentState, 2, "REST Systems", level = 2)
            bulletPoint(currentState, 3, "HTTP APIs", level = 2)
            bulletPoint(currentState, 4, "(Web)sockets", level = 2)
            bulletPoint(currentState, 5, "Asynchronous", level = 2)
            bulletPoint(currentState, 6, "HTTP client")
            bulletPoint(currentState, 7, "Asynchronous", level = 2)
            bulletPoint(currentState, 8, "Multi-platform", level = 2)
            bulletPoint(currentState, 9, "DSL based")
        }
    }
}
private val WhatIsKtorFor by functionalComponent<SlideContentProps> { props ->
    titledContent("What is Ktor for?") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletPoint(currentState, 1, "Building client connections")
            bulletPoint(currentState, 2, "Building server applications")
            bulletPoint(currentState, 3, "HTTP APIs", 2)
            bulletPoint(currentState, 4, "REST systems", 2)
            bulletPoint(currentState, 5, "(Web)Sockets", 2)
        }
    }
}
private val KtorExample by functionalComponent<SlideContentProps> { props ->
    ktorBackend {
        kotlinSourceCode("""
            «main«fun main() {«server«
                val server = embeddedServer(Netty, port = 8080) {«routing«
                    routing {«get«
                        get("/") {
                            call.respondText("Hello World!", 
                                            ContentType.Text.Plain) 
                        }
                    »} 
                »}»«start«
                server.start(wait = true) 
            »}»
            """.trimIndent()) {
            val currentState = props.state
            +"c-main" { blockEffectFrom(currentState, 1) }
            +"c-server" { blockEffectFrom(currentState, 2) }
            +"c-routing" { blockEffectFrom(currentState, 3) }
            +"c-get" { blockEffectFrom(currentState, 4) }
            +"c-start" { blockEffectFrom(currentState, 5) }
        }
    }
}

private val slideStyle: CSSBuilder.(Int) -> Unit = { state ->
    div {
        +"root" {
            fontSize = 0.88.em
            boxShadow(Color.black, blurRadius = 0.5.em)
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
            boxShadow(Color.black, blurRadius = 0.1.em)

            +"small" {
                flexGrow = 1.0
            }

            +"large" {
                flexGrow = 4.0
            }
        }

        +"feature" {
            backgroundColor = Color("#2281fb")
        }

        +"module" {
            backgroundColor = Color("#89AB0D")
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

            if (state >= 10)
                transform {
                    scale(1.4)
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

private fun PresentationBuilder.slideKtorSchema() {
    slide(SlideInfos(10)) { props ->
        attrs {
            style = slideStyle
        }


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
                div("entry business") { +"Cache" }
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

}

private fun RBuilder.ktorBackend(body: RBuilder.() -> Unit) {
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
                fontSize = 2.0.em
            }
            +"Ktor"
            styledSpan {
                css {
                    fontSize = 0.8.em
                }
                +" on the backend"
            }
        }
        styledDiv {
            css {
                height = 90.pct
                width = 100.pct
                paddingTop = 1.em
                display = Display.flex
                flexDirection = FlexDirection.column
                alignContent = Align.center
                alignItems = Align.center
            }
            body()
        }
    }
}

private fun PresentationBuilder.ktorBackendArch(step: Int) {
    slide(SlideInfos(
            inTransitions = Fade,
            outTransitions = Fade,
            inTransitionDuration = 0
    )) {
        ktorBackend {
            styledImg(src = "images/ktor-$step.png") {
                css {
                    height = 14.em
                    transition(::opacity, 300.ms)
                }
            }
        }
    }
}

fun PresentationBuilder.ktor() {
    slide(SlideInfos(10)) { child(WhatIsKtor, it) }
//    slide(SlideInfos(6)) { child(WhatIsKtorFor, it) }
    slide(SlideInfos(6)) { child(KtorExample, it) }
    for (i in 1..14) {
        ktorBackendArch(i)
    }

//    slideKtorSchema()

}

