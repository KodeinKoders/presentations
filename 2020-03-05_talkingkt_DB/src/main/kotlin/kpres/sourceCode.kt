package ws.kpres

import kotlinx.css.RuleSet
import kotlinx.html.Entities
import kotlinx.html.classes
import kotlinx.html.unsafe
import org.w3c.dom.HTMLElement
import react.*
import react.dom.code
import styled.css
import styled.styledPre
import ws.utils.getValue
import ws.utils.hljs


data class SourceCodeProps(
        val lang: String,
        val code: String,
        val style: RuleSet
): RProps

private val SourceCode by functionalComponent<SourceCodeProps> { props ->

    val code = useRef<HTMLElement?>(null)
    useEffect(listOf(props.lang, props.code)) {
        hljs.highlightBlock(code.current!!)
    }

    styledPre {
        attrs.classes = setOf("code")
        css(props.style)

        code("lang-${props.lang}") {
            ref = code

            val unsafeCode = props.code
                    .replace("<", Entities.lt.text)
                    .replace(">", Entities.gt.text)
                    .replace(Regex("«([a-zA-Z1-9\\-]+)«")) { "<span class=\"c-marker c-${it.groupValues[1]}\">" }
                    .replace("»", "</span>")
            attrs.unsafe { +unsafeCode }
//            +props.code
        }
    }
}

fun RBuilder.sourceCode(lang: String, code: String, style: RuleSet = {}) = child(
        functionalComponent = SourceCode,
        props = SourceCodeProps(lang, code, style)
)