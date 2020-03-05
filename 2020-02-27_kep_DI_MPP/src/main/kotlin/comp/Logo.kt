package ws.comp

import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.*
import react.dom.div
import styled.*
import ws.utils.getValue

interface LogoProps : RProps {
    var division: RBuilder.() -> Unit
    var href: String?
    var zoom: Double?
}

private val Logo by functionalComponent<LogoProps> { props ->
    val zoom = props.zoom ?: 1.0

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.center
        }

        styledImg(alt = "Kodein logo", src = "images/logo-white.svg") {
            css {
                display = Display.block
                width = (5.55 * zoom).em
                height = (5.55 * zoom).em
            }
        }

        div {
            styledH1 {
                css {
                    fontWeight = FontWeight.w700
                    fontSize = (2.98 * zoom).em
                    margin((-0.22 * zoom).em, 0.em, 0.em, 0.em)
                    textAlign = TextAlign.left
                }

                styledA(href = props.href, target = "_blank") {
                    css {
                        color = Color.white
                        textDecoration = TextDecoration.none
                    }

                    +"KODEIN"

                    styledSpan {
                        css.fontWeight = FontWeight.w300
                        props.division(this)
                    }
                }
            }
            styledH2 {
                css {
                    fontWeight = FontWeight.w300
                    color = Color.white
                    fontSize = (1.38 * zoom).em
                    marginTop = (-0.45 * zoom).em
                    textAlign = TextAlign.left
                    paddingLeft = 2.px
                    height = 0.em
                }
                props.children()
            }
        }
    }

}

fun RBuilder.logo(division: String, href: String? = null, zoom: Double = 1.0, handler: RHandler<LogoProps>) = child(
        functionalComponent = Logo,
        props = jsObject {
            this.division = { +division }
            this.href = href
            this.zoom = zoom
        },
        handler = handler
)

fun RBuilder.logo(division: RBuilder.() -> Unit, href: String? = null, zoom: Double = 1.0, handler: RHandler<LogoProps>) = child(
        functionalComponent = Logo,
        props = jsObject {
            this.division = division
            this.href = href
            this.zoom = zoom
        },
        handler = handler
)
