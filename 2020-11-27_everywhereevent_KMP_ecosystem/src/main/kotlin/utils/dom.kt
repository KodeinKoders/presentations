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


class PICTURE(initialAttributes : Map<String, String>, consumer: TagConsumer<*>) :
        HTMLTag("picture", consumer, initialAttributes, inlineTag = true, emptyTag = false),
        HtmlInlineTag {
}

inline fun RBuilder.picture(classes: String? = null, block: RDOMBuilder<PICTURE>.() -> Unit): ReactElement =
        tag(block) { PICTURE(attributesMapOf("class", classes), it) }


//fun RDOMBuilder<PICTURE>.source(srcset: String, type: String? = null, media: String? = null) {
//    source {
//        attrs["srcset"] = srcset
//        if (type != null) attrs["type"] = type
//        if (media != null) attrs["media"] = media
//    }
//}

fun RDOMBuilder<PICTURE>.source(type: String, vararg srcset: Pair<String, String?>) {
    source {
        attrs["type"] = type
        attrs["srcSet"] = srcset.joinToString { (src, cst) -> if (cst != null) "$src $cst" else src }
        attrs["sizes"] = "250px"
    }
}

//fun RDOMBuilder<PICTURE>.img(src: String, alt: String? = null, classes: String? = null, vararg srcset: Pair<String, String?>) {
//    img(alt = alt, src = src, classes = classes) {
//        attrs["srcset"] = srcset.joinToString { (src, cst) -> if (cst != null) "$src $cst" else src }
//    }
//}
