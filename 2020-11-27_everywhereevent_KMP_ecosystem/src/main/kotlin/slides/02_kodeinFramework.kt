package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import react.dom.li
import react.dom.span
import styled.*
import ws.comp.logo
import ws.utils.li


private fun CSSBuilder.targetsStyle() {
    textAlign = TextAlign.center
    listStyleType = ListStyleType.none
    display = Display.flex
    flexDirection = FlexDirection.row
    padding(0.em, 1.em)
    marginTop = 2.em
    alignSelf = Align.stretch
    position = Position.relative

    li {
        backgroundColor = Color("#EDEDED")
        color = Color("#444444")
        fontSize = 0.75.em
        fontWeight = FontWeight.w500
        padding(0.4.em)
        margin(0.2.em)
        borderRadius = 0.5.em
        flexGrow = 1.0
        flexBasis = FlexBasis.zero
        transition(::opacity, duration = 0.3.s)
        transition(::backgroundColor, duration = 0.3.s)
    }
}

private fun CSSBuilder.libsStyle() {
    textAlign = TextAlign.center
    listStyleType = ListStyleType.none
    display = Display.flex
    flexDirection = FlexDirection.row
    padding(0.em, 4.em)
    marginTop = 1.em
    alignSelf = Align.stretch
    position = Position.relative

    li {
        color = Color("#444444")
        fontSize = 0.75.em
        fontWeight = FontWeight.w700
        padding(0.4.em)
        margin(0.2.em)
        borderRadius = 0.15.em
        flexGrow = 1.0
        flexBasis = FlexBasis.zero
        color = Color.white
        boxShadow(Color("#444"), blurRadius = 0.2.em)

        span {
            fontWeight = FontWeight.w300
        }
    }
}

fun PresentationBuilder.kodeinFramework() = slide(
        stateCount = 4,
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("#46AF6D")
                transition(::background, 2000.ms)
            }
        },
        inTransitions = Flip,
        inTransitionDuration = 2000
) { props ->
    logo(
            light = "Framework",
            href = "https://kodein.org",
            zoom = 0.8,
            style = {
                ".logo_img" {
                    transition(::opacity, duration = 1.s)
                    opacity = if (props.state >= 3) 0 else 1.0
                }
                ".logo_h1" {
                    transition(::opacity, duration = 1.s)
                    opacity = if (props.state >= 3) 0 else 1.0
                }
            }
    ) {
        styledSpan {
            css {
                transition(::opacity, duration = 1.s)
                opacity = if (props.state >= 3) 0 else 1.0
            }
            +"painless "
        }
        styledSpan {
            css {
                display = Display.inlineBlock
                transition("transform", duration = 1.s)
                transition(::marginLeft, duration = 1.s)
                if (props.state >= 3) {
                    marginLeft = (-1.1).em
                    transform { scale(2) }
                }
            }

            styledImg(src = "images/kotlin-white.svg") {
                css { height = 0.65.em }
            }
            +" multiplatform kotlin"
        }
    }

    styledUl {
        css {
            transition(::opacity, duration = 0.3.s)
            opacity = if (props.state == 0) 0.0 else 1.0
            targetsStyle()
        }

        li { +"Android" }
        li { +"iOS" }
        li("web") { +"Web" }
        li("server") { +"Server" }
        li { +"Desktop" }
    }

    styledUl {
        css {
            transition(::opacity, duration = 0.3.s)
            opacity = if (props.state == 2) 1.0 else 0.0
            libsStyle()
        }

        styledLi {
            css { backgroundColor = Color("#EF822B") }
            +"KODEIN"
            span { +"DI" }
        }
        styledLi {
            css { backgroundColor = Color("#1B93D2") }
            +"KODEIN"
            span { +"DB" }
        }
        styledLi {
            css { backgroundColor = Color("#8E4C97") }
            +"KODEIN"
            span { +"MVI" }
        }
        styledLi {
            css { backgroundColor = Color("#EB5A44") }
            +"KODEIN"
            span { +"..." }
        }

    }
}
