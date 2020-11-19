package ws.utils

import kotlinx.html.DIV
import kotlinx.html.SPAN
import kotlinx.html.classes
import react.RBuilder
import styled.StyledDOMBuilder
import styled.styledDiv
import styled.styledSpan

internal inline fun RBuilder.s(vararg classNames: String, block: StyledDOMBuilder<SPAN>.() -> Unit) = styledSpan {
    if (classNames.isNotEmpty()) attrs.classes = setOf(*classNames)
    block()
}

internal inline fun RBuilder.d(vararg classNames: String, block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
    if (classNames.isNotEmpty()) attrs.classes = setOf(*classNames)
    block()
}
