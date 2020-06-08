package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import org.kodein.kpres.sourceCode
import org.w3c.dom.HTMLElement
import org.w3c.dom.asList
import org.w3c.dom.get
import react.*
import react.dom.b
import react.dom.br
import react.dom.div
import react.dom.h1
import styled.*
import ws.utils.*
import ws.utils.fontSize
import ws.utils.opacity


private fun CSSBuilder.listStyle() {
    li {
        paddingBottom = 0.8.em
        fontSize = 1.em

        div { +"code" {
            height = 0.px

//            fontSize = 0.9.em

            overflow = Overflow.hidden
            transition(::height, 300.ms)

            pre {
                marginTop = 0.3.em
            }
        }}
    }
}

fun PresentationBuilder.memAlloc() = slide(stateCount = 5) { child(memCopySlide, it) }


private val memCopySlide by functionalComponent<SlideContentProps> { props ->
    val ul = useRef<HTMLElement?>(null)

    useEffect(listOf(props.state)) {
        val codes = ul.current!!.querySelectorAll("div.code").asList()
        codes.forEach {
            it as HTMLElement
            val showState = it.attributes["data-show-state"]!!.value.toInt()
            it.style.height = if (props.state == showState) "calc(${(it.firstChild!! as HTMLElement).clientHeight}px + 0.6em)" else "0"
        }
    }

    styledH1 {
        css {
            margin(0.em, 0.em, 0.2.em, 0.em)
        }
        +"Memory Allocation"
    }

    styledUl {
        ref = ul
        css { listStyle() }

        styledLi {
            s {
                opacity {
                    when (props.state) {
                        0 -> 0.0
                        1 -> 1.0
                        else -> 0.2
                    }
                }
                +"JNI"
            }

            div("code") {
                attrs["data-show-state"] = 1
                sourceCode("C", """
                    auto ptr = malloc(size);
                    // ...
                    free(ptr);
                """.trimIndent())
            }
        }

        styledLi {
            s {
                opacity {
                    when (props.state) {
                        in 0..1 -> 0.0
                        2 -> 1.0
                        else -> 0.2
                    }
                }
                +"Kotlin/Native C-interop"
            }

            div("code") {
                attrs["data-show-state"] = 2
                sourceCode("Kotlin", """
                    memScoped {
                        val ptr = allocArray<ByteVar>(size)
                        // ...
                    }
                """.trimIndent())
            }
        }

        styledLi {
            s {
                opacity {
                    when (props.state) {
                        in 0..2 -> 0.0
                        3 -> 1.0
                        else -> 0.2
                    }
                }
                +"Js Wasm"
            }

            div("code") {
                attrs["data-show-state"] = 3
                sourceCode("Kotlin", """
                    val ptr = w._malloc(size)
                    // ...
                    w._free(ptr)
                """.trimIndent())
            }
        }
    }

    styledH3 {
        opacity(props.state >= 4)
        +"All pointers can be represented"
        br {}
        +"as a Long in Kotlin/Multiplatform."
    }

}
