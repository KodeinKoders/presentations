package ws.comp

import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.html.classes
import react.*
import react.dom.div
import styled.*
import ws.utils.getValue

interface LogoProps : RProps {
    var logoColor: String?
    var fontColor: Color?
    var light: RBuilder.() -> Unit
    var href: String?
    var zoom: Double?
    var style: CSSBuilder.() -> Unit
    var subTitle: (RBuilder.() -> Unit)?
}

private val Logo by functionalComponent<LogoProps> { props ->
    val zoom = props.zoom ?: 1.0
    val logoColor = props.logoColor ?: "white"
    val fontColor = props.fontColor ?: Color.white

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.center

            props.style(this)
        }

        styledImg(alt = "Kodein logo", src = "images/logo-${logoColor}.svg") {
            attrs.classes = setOf("logo_img")
            css {
                display = Display.block
                width = (5.55 * zoom).em
                height = (5.55 * zoom).em
            }
        }

        div {
            styledH1 {
                attrs.classes = setOf("logo_h1")
                css {
                    fontWeight = FontWeight.w700
                    fontSize = (2.98 * zoom).em
                    textAlign = TextAlign.left
                    if (props.subTitle != null)
                        margin((-0.22 * zoom).em, 0.em, 0.em, 0.em)
                    else
                        margin(0.em)
                }

                styledA(href = props.href, target = "_blank") {
                    css {
                        color = fontColor
                        textDecoration = TextDecoration.none
                    }

                    +"KODEIN"

                    styledSpan {
                        css.fontWeight = FontWeight.w300
                        props.light(this)
                    }
                }
            }
            props.subTitle?.let {
                styledH2 {
                    css {
                        fontWeight = FontWeight.w300
                        fontSize = (1.38 * zoom).em
                        color = fontColor
                        marginTop = (-0.45 * zoom).em
                        textAlign = TextAlign.left
                        paddingLeft = 2.px
                        height = 0.em
                    }

                    it(this)
                }
            }
        }
    }

}

fun RBuilder.logo(light: String, logoColor: String? = null, fontColor: Color? = null, href: String? = null, zoom: Double = 1.0, style: CSSBuilder.() -> Unit = {}, subTitle: (RBuilder.() -> Unit)? = null) = child(
        component = Logo,
        props = jsObject {
            this.logoColor = logoColor
            this.fontColor = fontColor
            this.light = { +light }
            this.href = href
            this.zoom = zoom
            this.style = style
            this.subTitle = subTitle
        },
        handler = {}
)

fun RBuilder.logo(light: RBuilder.() -> Unit, logoColor: String? = null, fontColor: Color? = null, href: String? = null, zoom: Double = 1.0, subTitle: (RBuilder.() -> Unit)? = null) = child(
        component = Logo,
        props = jsObject {
            this.logoColor = logoColor
            this.fontColor = fontColor
            this.light = light
            this.href = href
            this.zoom = zoom
            this.subTitle = subTitle
        },
        handler = {}
)
