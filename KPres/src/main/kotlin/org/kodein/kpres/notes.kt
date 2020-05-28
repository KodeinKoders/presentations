package org.kodein.kpres

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
