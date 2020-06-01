package org.kodein.kpres

import kotlinx.css.RuleSet
import kotlinx.html.Entities
import kotlinx.html.classes
import kotlinx.html.unsafe
import org.kodein.kpres.utils.getValue
import org.kodein.kpres.utils.hljs
import org.w3c.dom.*
import react.*
import react.dom.code
import react.dom.span
import styled.css
import styled.styledPre
import kotlin.browser.document


data class SourceCodeProps(
        val lang: String,
        val code: String,
        val onHighlight: () -> Unit,
        val style: RuleSet
): RProps


private fun wrap(n: Int, e: Node) {
    if (e is Text) {
        val span = document.createElement("span")
        span.className = "txt"
        span.textContent = e.textContent
        e.parentNode!!.replaceChild(span, e)
    }

    e.childNodes.asList().forEach {
        wrap(n + 1, it)
    }
}

private val SourceCode by functionalComponent<SourceCodeProps> { props ->

    val code = useRef<HTMLElement?>(null)
    useEffect(listOf(props.lang, props.code)) {
        hljs.highlightBlock(code.current!!)
        props.onHighlight()
        wrap(0, code.current!!)
    }

    styledPre {
        attrs.classes = setOf("code")
        css(props.style)

        code("lang-${props.lang}") {
            ref = code
            val unsafeCode = props.code
                    .replace("<", Entities.lt.text)
                    .replace(">", Entities.gt.text)
                    .replace(Regex("«([^«]+)«")) { "<span class=\"c-marker c-${it.groupValues[1]}\">" }
                    .replace("»", "</span>")
            attrs.unsafe { +unsafeCode }
        }
    }
}

fun RBuilder.sourceCode(lang: String, code: String, onHighlight: () -> Unit = {}, style: RuleSet = {}) = child(
        component = SourceCode,
        props = SourceCodeProps(lang, code, onHighlight, style)
)