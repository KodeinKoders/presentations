package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.flexColumn
import ws.utils.flexRow
import ws.utils.medium
import ws.utils.opacity


fun PresentationBuilder.phoenix() = slide(
    style = {
        backgroundColor = Color.kodein.dark
    },
    stateCount = 2
) { props ->
    flexColumn {

        flexRow(JustifyContent.center, Align.center) {
            styledImg(src = "images/acinq-logo.svg") {
                css {
                    height = 3.3.em
                    marginLeft = 1.5.em
                }
            }
            styledImg(src = "images/acinq-text.svg") {
                css {
                    height = 1.5.em
                    marginLeft = 0.25.em
                }
            }
        }

        flexRow(JustifyContent.center, Align.center) {
            css {
                marginTop = 2.5.em
                if (props.state < 1) transform { translateX(6.em) }
                transition(::transform, 1.s)
            }

            styledImg(src = "images/phoenix.png") {
                css {
                    height = 6.em
                    objectFit = ObjectFit.contain
                }
            }

            styledSpan {
                css {
                    fontSize = 8.rem
                    fontWeight = FontWeight.medium
                    padding(0.25.em, 0.4.em, 0.em, 0.1.em)
                    color = Color.kodein.purple
                }
                +":"
            }

            styledImg(src = "images/logo-android.png") {
                css {
                    height = 6.em
                    objectFit = ObjectFit.contain
                }
            }

            styledSpan {
                css {
                    fontSize = 8.rem
                    fontWeight = FontWeight.medium
                    padding(0.25.em, 0.4.em, 0.em, 0.4.em)
                    color = Color.kodein.orange
                    opacity(props.state >= 1)
                    transition(::opacity, 1.s, Timing.easeIn)
                }
                +"+"
            }

            styledImg(src = "images/logo-apple.png") {
                css {
                    height = 6.em
                    objectFit = ObjectFit.contain
                    opacity(props.state >= 1)
                    transition(::opacity, 1.s, Timing.easeIn)
                }
            }
        }
    }
}

