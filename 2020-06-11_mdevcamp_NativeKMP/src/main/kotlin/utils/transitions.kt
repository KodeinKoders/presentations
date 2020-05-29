package ws.utils

import kotlinx.css.LinearDimension
import kotlinx.css.fontSize
import kotlinx.css.opacity
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.SPAN
import react.RBuilder
import styled.StyledBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledSpan


internal inline fun StyledBuilder<*>.fontSize(transition: Boolean = true, value: () -> LinearDimension) {
    css {
        if (transition) transition(::fontSize, 0.3.s)
        fontSize = value()
    }
}

internal inline fun StyledBuilder<*>.opacity(transition: Boolean = true, value: () -> Double) {
    css {
        if (transition) transition(::opacity, 0.3.s)
        opacity = value()
    }
}
