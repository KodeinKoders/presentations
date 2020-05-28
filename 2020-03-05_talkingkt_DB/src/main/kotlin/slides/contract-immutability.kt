package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.sourceCode
import react.dom.h1
import styled.css
import styled.styledH2
import styled.styledSpan


fun PresentationBuilder.contractImmut() = slide(stateCount = 6) { props ->
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

            +"datum immutability"
        }
    }

    styledH2 {
        css {
            fontWeight = FontWeight.w400
            margin(0.em)
            transition(::opacity, 300.ms)
            opacity = if (props.state < 2) 0.0 else 1.0
        }
        +"Do"
        styledSpan {
            css {
                verticalAlign = VerticalAlign.middle
                transition(::fontSize, 500.ms)
                transition(::opacity, 300.ms)
                fontSize = if (props.state < 5) 1.em else 0.em
                opacity = if (props.state < 5) 1.0 else 0.0
            }
            +"n't"
        }
        +":"
    }
    sourceCode(
            "kotlin",
            """
                data class User(val uid: String, «m«var »«i«val »name: String)
                &nbsp;«p«
                                                println(db[key]!!.name) // Jane

                »val user = db[key]
                «r««i«val newUser = »user.«i«copy(»name = "John"«i«)»»
                &nbsp;«p«
                                                println(db[key]!!.name) // «r««m«John»«i«Jane»»
                
                »db.put(«m«user»«i«newUser»)«p«

                                                println(db[key]!!.name) // John»
            """.trimIndent()
    //«m«user.name = "John"»«i«val newUser = user.copy(name = "John")»
    ) {
        opacity = if (props.state < 2) 0.0 else 1.0
        transition(::opacity, 300.ms)
        marginBottom = 2.em
        "code" {
//            height = 14.em
            overflow = Overflow.hidden
        }
        "span" {
            +"c-marker" {
                verticalAlign = VerticalAlign.middle
                transition(::fontSize, 500.ms)
                transition(::opacity, 500.ms)
                if (props.state < 5) {
                    +"c-i" { fontSize = 0.em }
                    +"c-m" { fontSize = 1.em }
                } else {
                    +"c-i" { fontSize = 1.em }
                    +"c-m" { fontSize = 0.em }
                }
                +"c-i" {
                    color = Color.white
                    universal {
                        color = Color.white
                    }
                }
                +"c-r" {
                    position = Position.relative
                    after {
                        content = QuotedString("")
                        backgroundImage = Image("url('images/red-u.png')")
                        backgroundPosition = "bottom"
                        backgroundRepeat = BackgroundRepeat.repeatX
                        backgroundSize = 0.3.em.toString()
                        position = Position.absolute
                        top = 0.px
                        left = 0.px
                        bottom = 0.px
                        right = 0.px
                        transition(::opacity, 500.ms)
                        opacity = if (props.state in 3..4) 1.0 else 0.0
                    }
                }
                +"c-p" {
                    transition(::opacity, 300.ms)
                    transition(::lineHeight, 300.ms)
                    opacity = if (props.state < 4) 0.0 else 0.7
                    lineHeight = if (props.state < 4) LineHeight("0") else LineHeight("1.2")
                }
            }
        }
    }
}
