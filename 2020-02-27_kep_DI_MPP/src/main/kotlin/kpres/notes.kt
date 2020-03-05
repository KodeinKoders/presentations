package ws.kpres

import kotlinx.css.opacity
import react.RClass
import react.RProps
import react.dom.WithClassName
import styled.StyledDOMBuilder
import styled.css
import styled.styled

external interface MarkdownProps : RProps, WithClassName

@JsModule("react-markdown")
external val Markdown: RClass<MarkdownProps>

fun notes(str: String): StyledDOMBuilder<*>.(Int) -> Unit {

    val regex = Regex("\\[([0-9,]+)]")

    val chunks = ArrayList<Pair<Set<Int>, String>>()

    run {
        var position = 0
        var states: Set<Int> = setOf(0)
        while (true) {
            val match = regex.find(str, position)
            val matchFirst = match?.range?.first ?: str.length
            if (position != matchFirst) {
                val chunk = str.substring(position, matchFirst - 1)
                chunks.add(Pair(states.toSet(), chunk))
//                console.log(states.toTypedArray())
//                console.log(chunk)
            }

            if (match == null) break

            states = match.groupValues[1].split(",").map { it.toInt() } .toSet()
            position = match.range.last + 1
        }
    }

    return { state ->
        for ((i, pair) in chunks.withIndex()) {
//                +pair.second
            (styled(Markdown)) {
                css {
                    if (state !in pair.first) opacity = 0.4
                }
                +pair.second
            }
        }
    }
}
