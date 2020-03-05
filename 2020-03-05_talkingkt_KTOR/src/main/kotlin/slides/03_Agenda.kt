package ws.slides

import kotlinx.css.*
import styled.css
import styled.styledH1
import ws.kpres.PresentationBuilder
import ws.utils.slideTitle

fun PresentationBuilder.agenda() =
        slide {
            styledH1 {
                css {
                    fontWeight = FontWeight.normal
                    margin(0.em, 0.em, 0.3.em, 0.em)
                    padding(1.em)
                    borderRight = "solid"
                }
                +" Agenda"
            }
        }

