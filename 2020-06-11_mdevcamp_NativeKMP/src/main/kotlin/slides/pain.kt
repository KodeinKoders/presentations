package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import react.dom.b
import react.dom.br
import react.dom.h1
import react.dom.i
import styled.css
import styled.styledH1
import styled.styledLi
import styled.styledUl
import ws.utils.opacity
import ws.utils.s

fun PresentationBuilder.pain() = slide(stateCount = 9) { props ->

    styledH1 {
        css {
            margin(0.em, 0.em, 0.6.em, 0.em)
        }
        +"Pain points"
    }

    styledUl {
        css {
            fontSize = 1.1.em
            listStyleType = ListStyleType.disc
            margin(0.em, 0.em, 0.5.em, 0.em)
            "li" {
                margin(0.5.em)
            }
        }
        styledLi {
            opacity {
                when(props.state) {
                    0 -> 0.0
                    in 1..7 -> 1.0
                    else -> 0.2
                }
            }
            +"Manual façade for each platform "
            i { +"family" }
            +"."
            br {}
            s {
                opacity(props.state >= 2)
                +"[JVM & Android]"
            }
            s {
                opacity(props.state >= 3)
                +", [Native & iOS]"
            }
            s {
                opacity(props.state >= 4)
                +", [Web & Deno]"
            }
        }
        styledLi {
            opacity {
                when(props.state) {
                    in 0..4 -> 0.0
                    in 5..7 -> 1.0
                    else -> 0.2
                }
            }
            +"...with lots of configuration quircks!"
        }
        styledLi {
            opacity {
                when(props.state) {
                    in 0..5 -> 0.0
                    in 6..7 -> 1.0
                    else -> 0.2
                }
            }
            +"Need an understanding of C, low-level"
            br {}
            +" memory and garbage collection."
        }
        styledLi {
            opacity(props.state >= 7)
            +"Non-compatible managed & native memory"
            br {}
            +"means non-trivial optimizations"
        }
    }

}
