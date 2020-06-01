package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import react.dom.h1
import styled.css
import styled.styledLi
import styled.styledUl
import ws.utils.opacity
import ws.utils.s

fun PresentationBuilder.jni0() = slide(stateCount = 6) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JVM & Android: "
        }
        s {
            opacity { if (props.state >= 1) 1.0 else 0.0}
            +"JNI"
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
            +"1: Kotlin interface"
        }
        styledLi {
            opacity { if (props.state >= 3) 1.0 else 0.0}
            +"2: C/C++ bridge"
        }
        styledLi {
            opacity { if (props.state >= 4) 1.0 else 0.0}
            +"3: Kotlin façade"
        }
        styledLi {
            opacity { if (props.state >= 5) 1.0 else 0.0}
            +"4: Test!"
        }
    }

}
