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

fun PresentationBuilder.joy() = slide(stateCount = 13) { props ->

    styledH1 {
        css {
            margin(0.em, 0.em, 0.6.em, 0.em)
        }
        +"Joy points"
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
            opacity(props.state >= 1)
            +"It frackin' works!"
        }
        styledLi {
            opacity(props.state >= 2)
            +"...with a lot of flexibility!"
        }
        styledLi {
            opacity(props.state >= 3)
            +"It's challenging!"
            br {}
            s {
                opacity(props.state >= 4)
                +"Lots of moving parts to learn:"
            }
            br {}
            s {
                opacity(props.state >= 5)
                +"C"
            }
            s {
                opacity(props.state >= 6)
                +", CMake"
            }
            s {
                opacity(props.state >= 7)
                +", JNI"
            }
            s {
                opacity(props.state >= 8)
                +", Gradle"
            }
            s {
                opacity(props.state >= 9)
                +", K/N C-Interop"
            }
            s {
                opacity(props.state >= 10)
                +","
            }
            br {}
            s {
                opacity(props.state >= 10)
                +"Emscripten"
            }
            s {
                opacity(props.state >= 11)
                +", Webassembly"
            }
            s {
                opacity(props.state >= 12)
                +", ..."
            }

        }
    }

}
