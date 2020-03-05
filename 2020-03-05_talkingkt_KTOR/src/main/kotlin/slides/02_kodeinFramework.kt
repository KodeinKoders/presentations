package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import react.dom.li
import react.dom.p
import styled.*
import ws.comp.logo
import ws.kpres.Flip
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.utils.li


private fun CSSBuilder.targetsStyle(state: Int) {
    listStyleType = ListStyleType.none
//    display = if (state in 1..3) Display.flex else Display.none
    display = Display.flex
    transition(::display, 500.ms)
    flexDirection = FlexDirection.row
    padding(0.em, 1.em)
    marginTop = 2.em
    alignSelf = Align.stretch
    position = Position.relative

    li {
        backgroundColor = Color("#EDEDED")
        color = Color("#444444")
        fontSize = 0.65.em
        fontWeight = FontWeight.w500
        padding(0.4.em)
        margin(0.2.em)
        borderRadius = 0.5.em
        flexGrow = 1.0
        flexBasis = FlexBasis.zero
        opacity = 0.0
        lineHeight = LineHeight("0")
        transition(::lineHeight, duration = 500.ms)
        transition(::opacity, duration = 500.ms)
        transition(::backgroundColor, duration = 500.ms)

        +"di" {
            if (state in 1..3) {
                opacity = 1
                lineHeight = LineHeight("1.2")
            }
        }
        +"db" {
            if (state in 2..3) {
                opacity = 1
                lineHeight = LineHeight("1.2")
            }
        }
        +"more" {
            if (state == 3) {
                opacity = 1
                lineHeight = LineHeight("1.2")
            }
        }
    }
}

private val infos = SlideInfos(
        stateCount = 4,
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("#46AF6D")
                transition(::background, 1000.ms)
            }
        },
        inTransitions = Flip,
        inTransitionDuration = 1000
)

fun PresentationBuilder.kodeinFramework() = slide(infos) { props ->
    logo(division = {
        styledSpan {
            css {
                transition(::fontSize, 500.ms)
                fontSize = if (props.state >= 4) 1.em else 0.em
                verticalAlign = VerticalAlign.middle
            }
            +"DI"
        }
        styledSpan {
            css {
                verticalAlign = VerticalAlign.middle
            }
            +"Framework"
        }
    }, zoom = 0.8) {
        styledP {
            css {
                padding(0.em)
                margin(0.em)
            }
            +"painless "
            styledImg(src = "images/kotlin-white.svg") {
                css { height = 0.65.em }
            }
            +" "
            styledSpan {
                css {
                    verticalAlign = VerticalAlign.middle
                }
                +"multiplatform kotlin"
            }
        }
    }

    styledUl {
        css {
            textAlign = TextAlign.center
            targetsStyle(props.state)
        }

        li("di") { +"Dependency Injection" }
        li("db") { +"NoSQL embedded database" }
        li("more") { +"More to come..." }
    }
}
