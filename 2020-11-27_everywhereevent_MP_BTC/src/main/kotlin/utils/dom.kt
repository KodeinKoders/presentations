package ws.utils

import kotlinx.css.*
import kotlinx.html.*
import org.w3c.dom.HTMLElement
import react.RBuilder
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.img
import react.dom.source
import react.dom.tag
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledTag


fun HTMLElement.recursiveOffset(upTo: HTMLElement? = null): Pair<Int, Int> {
    if (this == upTo) return 0 to 0
    val parent = offsetParent as? HTMLElement ?: return offsetLeft to offsetTop
    return parent.recursiveOffset(upTo).let { (it.first + offsetLeft) to (it.second + offsetTop) }
}

inline fun RBuilder.flexColumn(justifyContent: JustifyContent? = null, alignItems: Align? = null, block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
    css {
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent?.let { this.justifyContent = it }
        alignItems?.let { this.alignItems = it }
    }

    block()
}

inline fun RBuilder.flexRow(justifyContent: JustifyContent? = null, alignItems: Align? = null, block: StyledDOMBuilder<DIV>.() -> Unit) = styledDiv {
    css {
        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent?.let { this.justifyContent = it }
        alignItems?.let { this.alignItems = it }
    }

    block()
}
