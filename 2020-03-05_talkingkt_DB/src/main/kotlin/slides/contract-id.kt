package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import org.kodein.kpres.sourceCode
import react.dom.b
import react.dom.div
import react.dom.h1
import react.dom.span
import styled.css
import styled.styledDiv
import styled.styledSpan


fun PresentationBuilder.contractId() = slide(
        stateCount = 10,
        notes = notes {
            1 ("L'ID définit le document")
        }
) { props ->
    h1 {
        styledSpan {
            css {
                fontWeight = FontWeight.w400
            }
            +"Contract: "
        }
        styledSpan {
            css {
                transition(::opacity, 300.ms)
                opacity = if (props.state < 1) 0.0 else 1.0
            }

            +"ID defines document"
        }
    }

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignSelf = Align.stretch
            alignItems = Align.center
            height = 10.em
        }

        div {
            sourceCode(
                    "kotlin",
                    """
                    val key = db.put(U(1, "John"))«c1«
                    
                    val model = db[key]!!
                    db.put(model.copy(name = "Alice"))»«c2«

                    val model = db[key]!!
                    db.put(model.copy(
                        id = 2, name = "Bob"
                    ))»«c3«
                    
                    println(db[key])»«c4« // U(1, "Alice")»
                """.trimIndent()
            ) {
                transition(::opacity, 300.ms)
                opacity = if (props.state < 2) 0.0 else 1.0
                flexGrow = 2.0
                flexBasis = FlexBasis.zero

                "code" {
                    overflow = Overflow.hidden
                }

                "span" {
                    +"c-marker" {
                        opacity = 1.0
                        verticalAlign = VerticalAlign.middle
                        transition(::opacity, 300.ms)
                        transition(::lineHeight, 300.ms)
                        transition(::fontSize, 300.ms)
                    }

                    +"c-c1" {
                        lineHeight = LineHeight(if (props.state < 4) "0" else "1.2")
                        opacity = if (props.state < 4) 0.0 else 1.0
                    }
                    +"c-c2" {
                        lineHeight = LineHeight(if (props.state < 6) "0" else "1.2")
                        opacity = if (props.state < 6) 0.0 else 1.0
                    }
                    +"c-c3" {
                        lineHeight = LineHeight(if (props.state < 8) "0" else "1.2")
                        opacity = if (props.state < 8) 0.0 else 1.0
                    }
                    +"c-c4" {
                        fontSize = if (props.state < 9) 0.em else 1.em
                    }
                }
            }
        }

        fun CSSBuilder.document() {
            backgroundImage = Image("url('images/doc.png')")
            backgroundSize = "contain"
            backgroundRepeat = BackgroundRepeat.noRepeat
            backgroundPosition = "center"
            flexGrow = 1.0
            flexBasis = FlexBasis.zero
            height = 6.em
            padding(0.5.em)
            display = Display.flex
            flexDirection = FlexDirection.column
            justifyContent = JustifyContent.center
            alignItems = Align.center
        }

        styledDiv {
            css {
                document()
                transition("transform", 500.ms)
                transition(::opacity, 500.ms)
                if (props.state < 3) {
                    opacity =  0.0
                    transform { scale(1.5) }
                }
                if (props.state == 9) {
                    transform { scale(1.25) }
                }
            }
            b { +"1" }
            styledSpan {
                css {
                    position = Position.relative
                    alignSelf = Align.stretch
                    height = 1.2.em
                }
                styledSpan {
                    css {
                        position = Position.absolute
                        left = 0.em
                        width = 100.pct
                        transition("transform", 500.ms)
                        transition(::opacity, 500.ms)
                        if (props.state >= 5) {
                            opacity = 0.0
                            transform { scale(0.5) }
                        }
                    }
                    +"John"
                }
                styledSpan {
                    css {
                        position = Position.absolute
                        left = 0.em
                        width = 100.pct
                        transition("transform", 500.ms)
                        transition(::opacity, 500.ms)
                        if (props.state < 5) {
                            opacity = 0.0
                            transform { scale(1.5) }
                        }
                    }
                    +"Alice"
                }
            }
        }

        styledDiv {
            css {
                document()
                transition(::opacity, 300.ms)
                transition("transform", 500.ms)
                transition(::opacity, 500.ms)
                if (props.state < 7) {
                    opacity =  0.0
                    transform { scale(1.5) }
                }
                if (props.state == 9) {
                    opacity = 0.4
                }
            }
            b { +"2" }
            span { +"Bob" }
        }
    }

}
