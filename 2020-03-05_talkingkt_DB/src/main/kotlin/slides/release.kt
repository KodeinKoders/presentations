package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.dom.*
import styled.css
import styled.styledA
import styled.styledLi
import styled.styledUl
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos


fun PresentationBuilder.release() = slide {
    h1 {
        styledA(href = "https://github.com/Kodein-Framework/Kodein-DB") {
            css {
                color = Color.white
                textDecoration = TextDecoration.none
            }
            +"github.com/Kodein-Framework/Kodein-DB"
        }
    }

    h2 { +"Comming early 2020" }

    styledUl {
        css {
            listStyleType = ListStyleType.disc

            "li" {
                margin(0.5.em)
                transition(::opacity, 300.ms)
            }
        }

        li { +"API freeze" }
        li { +"Documentation" }
        li { +"Demo" }
    }
}
