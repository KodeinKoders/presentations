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

fun PresentationBuilder.memCopy() = slide(stateCount = 4) { child(memCopySlide, it) }


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
        +"Memory copy"
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
                    // IN
                    env->GetPrimitiveArrayCritical(jBytes, nullptr);
                    env->GetStringUTFChars(jString, nullptr);
                    
                    // OUT
                    env->NewStringUTF(chars);
                    env->SetByteArrayRegion(array, 0, len, buffer);
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
                    // IN
                    bytes.toCValues()
                    input.toString() // Auto convert to const char*
                    
                    // OUT
                    result.toKString()
                    ByteArray(len.value) { result[it] }
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
                    // IN
                    w.HEAP8.set(bytes, offset = bytesPtr)
                    w.HEAP8.set(b64.encodeToByteArray(), offset = b64Ptr)
                    
                    // OUT
                    js("[]").slice.call(w.HEAP8.subarray(ptr, ptr + len))
                    result.decodeToString()
                """.trimIndent())
            }
        }
    }

}
