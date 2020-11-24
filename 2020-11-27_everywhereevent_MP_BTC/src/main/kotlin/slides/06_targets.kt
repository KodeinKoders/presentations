package ws.slides

import kotlinx.css.*
import kotlinx.html.classes
import org.kodein.kpres.PresentationBuilder
import react.dom.div
import react.dom.h3
import react.dom.li
import react.dom.ul
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledP
import ws.charter.kodein
import ws.utils.*

fun PresentationBuilder.targets() = slide(
    stateCount = 4
) { props ->
    titledSlide("Targets") {
        flexRow {
            css {
                "div.half" {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    flexGrow = 1.0
//                    flexBasis = FlexBasis.zero
                    padding(horizontal = 2.em)
                }
                "h3" {
                    color = Color.kodein.purple
                    fontWeight = FontWeight.regular
                    textAlign = TextAlign.left
                }
                "ul" {
                    listStyleType = ListStyleType.disc
                }
                color = Color.kodein.kaumon
            }
            styledDiv {
                attrs.classes += "half"
                opacity(props.state >= 1)
                h3 { +"Mobile" }
                ul {
                    li { +"Android" }
                    li { +"iOS" }
                }
            }
            styledDiv {
                attrs.classes += "half"
                opacity(props.state >= 2)
                h3 { +"Desktop" }
                ul {
                    li { +"JVM (Linux, MacOS, Windows)" }
                    li { +"Native Linux X64" }
                }

                styledP {
                    css {
                        opacity(props.state >= 3)
                        color = Color.kodein.orange
                        fontWeight = FontWeight.light
                    }
                    +"Test & development flexibility!"
                }
            }
        }
    }
}
