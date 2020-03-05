package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.dom.*
import styled.css
import styled.styledLi
import styled.styledUl
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos


private val infos = SlideInfos(
        stateCount = 4
)

fun PresentationBuilder.migration() = slide(infos) { props ->
    h1 {
        +"Migration"
    }
    styledUl {
        css {
            listStyleType = ListStyleType.disc

            "pre" {
                display = Display.inline
                fontFamily = "fira code"
            }

            "ul" {
                listStyleType = ListStyleType.circle
            }

            "li" {
                margin(0.5.em)
                transition(::opacity, 300.ms)
            }
        }

        styledLi {
            css {
                opacity = if (props.state < 1) 0.0 else 1.0
            }
            +"Depends on Serializer"
            ul {
                li {
                    b { +"Kryo" }
                    +" configured with "
                    pre { +"CompatibleFieldSerializer" }
                }
                li {
                    b { +"KotlinX Serialization" }
                    +" uses "
                    pre { +"CBOR" }
                }
            }
        }
        styledLi {
            css {
                opacity = if (props.state < 2) 0.0 else 1.0
            }
            +"Depends on type table configuration"
        }
        styledLi {
            css {
                opacity = if (props.state < 3) 0.0 else 1.0
            }
            +"Manual endeavour"
        }
    }
}
