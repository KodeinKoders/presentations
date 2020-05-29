package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import react.dom.*
import styled.css
import styled.styledLi
import styled.styledUl
import ws.utils.opacity

fun PresentationBuilder.whatFor() = slide(stateCount = 3) { props ->
    h1 { +"What for?" }

    styledUl {
        css {
            listStyleType = ListStyleType.disc
            "> li" {
                marginBottom = 1.em
            }
            paddingBottom = 2.em
        }

        styledLi {
            opacity { if (props.state >= 1) 1.0 else 0.0 }
            +"Use a native library"
            ul {
                li { +"OpenCV" }
                li { +"LevelDB" }
                li { +"FFmpeg" }
                li { +"..." }
            }
        }
        styledLi {
            opacity { if (props.state >= 2) 1.0 else 0.0 }
            +"Use your "
            b { +"heavy computing" }
            +" native code"
        }
    }
}
