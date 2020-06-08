package ws.utils

import kotlinext.js.jsObject
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.classes
import kotlinx.html.unsafe
import org.kodein.kpres.sourceCode
import org.w3c.dom.*
import react.*
import styled.css
import styled.styledDiv
import styled.styledSpan
import kotlin.browser.document

private interface SlideCodeProps : RProps {
    var state: Int
    var lang: String
    var code: String
    var style: RuleSet
}

private fun offsetUntil(element: HTMLElement?, until: HTMLElement, get: HTMLElement.() -> Int): Int {
    if (element == null || element == until || element == document.body) return 0
    return element.get() + offsetUntil(element.offsetParent as? HTMLElement, until, get)
}

/*
 CMD:STATE[-STATE]

 o: opacity
 s: font size
 f: focus
*/

private data class Snippet(val top: Int, val left: Int, val html: String, val classNames: Set<String>, val focus: (Int) -> Boolean)
private data class Command(val command: String, val arg: String?, val states: IntRange, val className: String)
private data class Span(val span: HTMLSpanElement, val commands: List<Command>)

private val slideCode by functionalComponent<SlideCodeProps> { props ->
    val selfRef = useRef<HTMLDivElement?>(null)
    var highlighted by useState(false)
    var spans by useState<List<Span>>(emptyList())

    useEffect(listOf(props.code, highlighted)) {
        val candidates = selfRef.current!!.querySelectorAll("span.c-marker")

        spans = candidates.asList()
                .filterIsInstance<HTMLSpanElement>()
                .map { span ->
                    val cmds = span.classList.asList()
                            .mapNotNull { Regex("c-([a-z0-9.@]+):([0-9\\-]+)").matchEntire(it) }
                            .map { result ->
                                val cmdArg = result.groupValues[1].split("@")
                                val states = result.groupValues[2]
                                        .split("-")
                                        .mapIndexed { i, it -> if (it.isEmpty()) { if (i == 0) 0 else Int.MAX_VALUE } else it.toInt() }
                                        .let { if (it.size == 1) it[0]..it[0] else it[0]..it[1] }
                                Command(cmdArg[0], cmdArg.getOrNull(1), states, result.value)
                            }
                    Span(span, cmds)
                }
                .filter { it.commands.isNotEmpty() }
    }

    styledDiv {
        ref = selfRef

        val hasFocus = spans.flatMap { it.commands } .count { (it.command == "f" || it.command == "ff") && props.state in it.states }

        css {
            position = Position.relative
        }

        sourceCode(props.lang, props.code, onHighlight = { highlighted = true }) {
            "span.txt" {
                transition("opacity", 0.3.s)
                opacity = if (hasFocus > 0) 0.25 else 1.0
            }

            "span.c-marker" {
//                display = Display.inlineBlock
            }

            "code" {
                overflow = Overflow.visible
            }

            spans.flatMap { it.commands } .forEach {
                "span.c-marker.${it.className.escapeCss()}" {
                    when (it.command) {
                        "f" -> {
                            display = Display.inlineBlock
                            position = Position.relative
                            transition("transform", 0.3.s)
                            transition("background-color", 0.3.s)
                            transition("box-shadow", 0.3.s)
                            if (props.state in it.states) {
                                zIndex = 100
                                transform { scale(it.arg?.toDouble() ?: 1.1) }
                                backgroundColor = Color("#2b2b2b")
                                boxShadow(Color.black, blurRadius = 0.125.em)
                            }
                            "span.txt" {
                                if (props.state in it.states) opacity = 1.0
                            }
                        }
                        "ff" -> {
                            display = Display.inlineBlock
                            position = Position.relative
                            transition("transform", 0.3.s)
                            transition("background-color", 0.3.s)
                            transition("padding", 0.3.s)
                            transition("margin", 0.3.s)
                            transition("border-radius", 0.3.s)
                            if (props.state in it.states) {
                                zIndex = 100
                                transform { scale(it.arg?.toDouble() ?: 1.6) }
                                backgroundColor = Color("#2b2b2b")
                                padding(0.4.em)
                                margin((-0.4).em)
                                borderRadius = 0.2.em
                                boxShadow(Color.black, blurRadius = 0.25.em)
                            }
                            "span.txt" {
                                if (props.state in it.states && hasFocus == 1) opacity = 1.0
                            }
                        }
                        "s" -> {
                            transition("font-size", 0.3.s)
                            fontSize = if (props.state in it.states) 1.em else 0.em
                        }
                        "l" -> {
                            transition("line-height", 0.3.s)
                            transition("opacity", 0.3.s)
                            lineHeight = if (props.state in it.states) LineHeight("1.2") else LineHeight("0")
                            opacity = if (props.state in it.states) 1.0 else 0.0
                        }
                    }
                }
            }

            props.style(this)
        }

    }
}

internal fun RBuilder.slideCode(
        state: Int,
        lang: String,
        code: String,
        style: RuleSet = {}
) {
    child(slideCode, jsObject { this.state = state ; this.lang = lang ; this.code = code ; this.style = style })
}