package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.borderLeft
import org.kodein.kpres.PresentationBuilder
import react.dom.b
import react.dom.br
import styled.*
import ws.charter.kodein
import ws.utils.light
import ws.utils.medium
import ws.utils.opacity


fun PresentationBuilder.conclusion() = slide(
    stateCount = 3
) { props ->
    styledH4 {
        css {
            color = Color.kodein.klycine
            fontWeight = FontWeight.lighter
            textAlign = TextAlign.left
            width = 19.em
            borderLeft(0.2.em, BorderStyle.solid, Color.kodein.orange)
            padding(0.2.em, 0.em, 0.2.em, 0.5.em)

            "b" {
                color = Color.kodein.korail
                fontWeight = FontWeight.medium
            }
        }

        styledSpan {
            +"We estimate that, using "
            b { +"Kotlin/Multiplatform" }
            +", the amount of "
            b { +"sharable code" }
            +" in a multiplatform mobile application represents "
            b { +"50 to 95 percent" }
            +" of the total code base."
        }
        br {}
        br {}
        styledSpan {
            opacity(props.state >= 1)
            +"The non-shareable code represents a "
            b { +"necessary investment" }
            +" into the quality of the app in order to make it "
            b { +"natively integrated" }
            +" into the host platform User Experience."
        }
        br {}
        br {}
        styledSpan {
            css {
                color = Color.kodein.orange
                fontWeight = FontWeight.light
                opacity(props.state >= 2)
            }
            +"-- "
            styledB {
                css {
                    specific {
                        color = Color.kodein.orange
                        fontWeight = FontWeight.bold
                    }
                }
                +"KODEIN"
            }
            +"Koders"
        }
    }
}
