package ws.utils

import kotlinx.css.*
import kotlinx.css.properties.Angle
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.transform
import react.RBuilder
import styled.css
import styled.styledSpan


@Suppress("DuplicatedCode")
fun RBuilder.arrow(
    color: Color,
    thickness: LinearDimension = 0.2.em,
    width: LinearDimension = 6.em,
    hatWidth: LinearDimension = width / 3,
    hatAngle: Angle = 35.deg,
    css: RuleSet = {}
) {
    styledSpan {
        css {
            display = Display.block
            position = Position.relative
            this.width = width
            height = thickness
            backgroundColor = color
            borderRadius = thickness
            before {
                content = "".quoted
                display = Display.block
                position = Position.absolute
                this.width = hatWidth
                height = thickness
                borderRadius = thickness
                right = 0.pct
                top = (-1).px
                backgroundColor = color
                put("transform-origin", "${100.pct - (thickness / 2)} ${50.pct}")
                transform { rotate(hatAngle) }
            }
            after {
                content = "".quoted
                display = Display.block
                position = Position.absolute
                this.width = hatWidth
                height = thickness
                borderRadius = thickness
                right = 0.pct
                top = 1.px
                backgroundColor = color
                put("transform-origin", "${100.pct - (thickness / 2)} ${50.pct}")
                transform { rotate(Angle("-$hatAngle")) }
//                transform { rotate((-45).deg) }
            }
            put("transform-origin", "${50.pct} ${50.pct}")
            css()
        }
    }
}
