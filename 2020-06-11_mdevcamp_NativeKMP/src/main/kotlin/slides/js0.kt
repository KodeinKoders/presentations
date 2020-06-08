package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import react.dom.h1
import styled.css
import styled.styledLi
import styled.styledUl
import ws.utils.opacity
import ws.utils.s

fun PresentationBuilder.js0() = slide(stateCount = 5) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"Web & Deno: "
        }
        s {
            opacity { if (props.state >= 1) 1.0 else 0.0}
            +"Web Assembly"
        }
    }

    styledUl {
        css {
            fontSize = 1.25.em
            "li" {
                margin(0.6.em)
            }
        }
        styledLi {
            opacity { if (props.state >= 2) 1.0 else 0.0}
            +"1: Emscripten"
        }
        styledLi {
            opacity { if (props.state >= 3) 1.0 else 0.0}
            +"2: Kotlin façade"
        }
        styledLi {
            opacity { if (props.state >= 4) 1.0 else 0.0}
            +"3: Test!"
        }
    }

}
