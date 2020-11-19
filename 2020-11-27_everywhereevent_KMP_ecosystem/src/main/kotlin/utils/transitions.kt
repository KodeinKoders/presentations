package ws.utils

import kotlinx.css.*
import kotlinx.css.properties.Transforms
import kotlinx.css.properties.s
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import styled.StyledBuilder
import styled.css
import ws.utils.fontSize
import ws.utils.opacity


internal inline fun CSSBuilder.fontSize(transition: Boolean = true, value: () -> LinearDimension) {
    if (transition) transition("font-size", 0.3.s)
    fontSize = value()
}

internal inline fun StyledBuilder<*>.fontSize(transition: Boolean = true, value: () -> LinearDimension) {
    css {
        fontSize(transition, value)
    }
}

internal fun CSSBuilder.fontSize(visible: Boolean, off: LinearDimension = 0.em, transition: Boolean = true) {
    fontSize(transition) { if(visible) 1.em else off }
}

internal fun StyledBuilder<*>.fontSize(visible: Boolean, off: LinearDimension = 0.em, transition: Boolean = true) {
    fontSize(transition) { if(visible) 1.em else off }
}


internal inline fun CSSBuilder.opacity(transition: Boolean = true, value: () -> Double) {
    if (transition) transition("opacity", 0.3.s)
    opacity = value()
}

internal inline fun StyledBuilder<*>.opacity(transition: Boolean = true, value: () -> Double) {
    css {
        opacity(transition, value)
    }
}

internal fun CSSBuilder.opacity(visible: Boolean, off: Double = 0.0, transition: Boolean = true) {
    opacity(transition) { if (visible) 1.0 else off }
}

internal fun StyledBuilder<*>.opacity(visible: Boolean, off: Double = 0.0, transition: Boolean = true) {
    opacity(transition) { if (visible) 1.0 else off }
}


internal inline fun CSSBuilder.transform(exec: Boolean, transition: Boolean = true, crossinline builder: Transforms.() -> Unit) {
    if (transition) transition("transform", 0.3.s)
    if (exec) transform { builder() }
}

internal inline fun StyledBuilder<*>.transform(exec: Boolean, transition: Boolean = true, crossinline builder: Transforms.() -> Unit) {
    css {
        transform(exec, transition, builder)
    }
}
