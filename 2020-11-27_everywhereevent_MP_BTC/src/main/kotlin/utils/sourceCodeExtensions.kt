package ws.utils

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.sourceCode
import react.RBuilder
import ws.charter.kodein

fun RBuilder.kotlinSourceCode(code: String, style: RuleSet = {}) = sourceCode("kotlin", code) {
    "code" {
        overflow = Overflow.hidden
    }
    "span.c-marker" {
        opacity = 1.0
        transition(::opacity, 300.ms)
        transition(::fontSize, 300.ms)
        transition(::lineHeight, 300.ms)
        style()
    }
}

fun CSSBuilder.blockEffect(currentState: Int, range: IntRange) {
    opacity = if (currentState !in range) 0.0 else 1.0
    lineHeight = LineHeight(if (currentState !in range) "0" else "1.2")
}
fun CSSBuilder.blockEffectOn(currentState: Int, state: Int) {
    opacity = if (currentState != state) 0.0 else 1.0
    lineHeight = LineHeight(if (currentState != state) "0" else "1.2")
}

fun CSSBuilder.blockEffectFrom(currentState: Int, from: Int) {
    opacity = if (currentState < from) 0.0 else 1.0
    lineHeight = LineHeight(if (currentState < from) "0" else "1.2")
}

fun CSSBuilder.blockEffectTo(currentState: Int, to: Int) {
    opacity = if (currentState >= to) 0.0 else 1.0
    lineHeight = LineHeight(if (currentState >= to) "0" else "1.2")
}

fun CSSBuilder.lineEffectFrom(currentState: Int, from: Int) {
    opacity = if (currentState < from) 0.0 else 1.0
    fontSize = if (currentState < from) 0.em else 1.em
}

fun CSSBuilder.lineEffectTo(currentState: Int, to: Int) {
    opacity = if (currentState >= to) 0.0 else 1.0
    fontSize = if (currentState >= to) 0.em else 1.em
}

fun CSSBuilder.lineEffect(currentState: Int, range: IntRange) {
    opacity = if (currentState !in range) 0.0 else 1.0
    fontSize = if (currentState !in range) 0.em else 1.em
}

private fun CSSBuilder.highlight(currentState: Int, state: Int, hlColor: Color) {
    fontSize = 1.em
    if (currentState != state) fontSize = 0.em
    color = hlColor
}

fun CSSBuilder.highlightOnRange(currentState: Int, range: IntRange, hlColor: Color) {
    color = if (currentState in range) hlColor else Color.black
}
fun CSSBuilder.highlightOn(currentState: Int, on: Int, hlColor: Color) {
    color = if (currentState == on) hlColor else Color.black
}

fun CSSBuilder.orangeHighlight(currentState: Int, state: Int) =
        highlight(currentState, state, Color.kodein.orange)

private val orangeHighLight: (String, Int, String) -> String = { prefix, i, body -> "«$prefix$i«$body»" }
fun compileError(i: Int) = orangeHighLight("err", i, "!!!")
