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


fun PresentationBuilder.next() = slide {
    h1 { +"Next..." }

    styledUl {
        css {
            listStyleType = ListStyleType.disc

            "li" {
                margin(0.5.em)
                transition(::opacity, 300.ms)
            }
        }

        li { +"Migration API" }
        li { +"Coroutines support" }
        li { +"Compiler plugin" }
        li { +"IndexedDB JS support" }
        li { +"Cloud sync" }
        li { +"Data export / visualisation" }
        li { +"Full text search" }
    }
}
