package ws.kpres

import kotlinx.css.ListStyleType
import kotlinx.css.listStyleType
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

//fun notes(str: String): StyledDOMBuilder<*>.(Int) -> Unit {
//
//    val regex = Regex("\\[([0-9,]+)]")
//
//    val chunks = ArrayList<Pair<Set<Int>, String>>()
//
//    run {
//        var position = 0
//        var states: Set<Int> = setOf(0)
//        while (true) {
//            val match = regex.find(str, position)
//            val matchFirst = match?.range?.first ?: str.length
//            if (position != matchFirst) {
//                val chunk = str.substring(position, matchFirst - 1)
//                chunks.add(Pair(states.toSet(), chunk))
////                console.log(states.toTypedArray())
////                console.log(chunk)
//            }
//
//            if (match == null) break
//
//            states = match.groupValues[1].split(",").map { it.toInt() } .toSet()
//            position = match.range.last + 1
//        }
//    }
//
//    return { state ->
//        for ((i, pair) in chunks.withIndex()) {
////                +pair.second
//            (styled(Markdown)) {
//                css {
//                    if (state !in pair.first) opacity = 0.4
//                }
//                +pair.second
//            }
//        }
//    }
//}


fun notes(str: String): StyledDOMBuilder<*>.(Int) -> Unit {
    return { _ ->
        Markdown {
            +str.trimIndent()
        }
    }
}

class NotesBuilder {
    internal val steps = ArrayList<Pair<IntRange, String>>()

    operator fun Int.invoke(n: String): Added {
        steps.add(this..this to n.trimIndent())
        return Added
    }
    operator fun IntRange.invoke(n: String) = steps.add(this to n.trimIndent())

    object Added

    operator fun Int.rangeTo(@Suppress("UNUSED_PARAMETER") a: Added) {
        val pair = steps.removeAt(steps.lastIndex)
        steps.add(this..pair.first.last to pair.second)
    }
}

fun notes(builder: NotesBuilder.() -> Unit): StyledDOMBuilder<*>.(Int) -> Unit {
    val notes = NotesBuilder().apply(builder).steps
    println(notes)

    return { state ->
        for (note in notes) {
            (styled(Markdown)) {
                css {
                    if (state !in note.first) opacity = 0.4
                }

                +note.second
            }
        }
    }
}
