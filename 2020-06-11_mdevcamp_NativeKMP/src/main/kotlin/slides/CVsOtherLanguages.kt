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
import styled.*
import ws.utils.*
import ws.utils.fontSize
import ws.utils.opacity


private fun CSSBuilder.listStyle() {
    li {
        paddingBottom = 1.em
        fontSize = 1.25.em

        div { +"code" {
            height = 0.px
            overflow = Overflow.hidden
            transition(::height, 300.ms)

            pre {
                marginTop = 0.3.em
            }
        }}
    }
}

fun PresentationBuilder.CVsOtherLanguages() = slide(stateCount = 9) { child(CVsOtherLanguagesSlide, it) }


private val CVsOtherLanguagesSlide by functionalComponent<SlideContentProps> { props ->
    val ul = useRef<HTMLElement?>(null)

    useEffect(listOf(props.state)) {
        val codes = ul.current!!.querySelectorAll("div.code").asList()
        codes.forEach {
            it as HTMLElement
            val showState = it.attributes["data-show-state"]!!.value.toInt()
            it.style.height = if (props.state == showState) "calc(${(it.firstChild!! as HTMLElement).clientHeight}px + 0.6em)" else "0"
        }
    }

    styledUl {
        ref = ul
        css { listStyle() }

        styledLi {
            s {
                fontSize { if (props.state <= 1) 1.em else 0.em }
                +"Why "
            }
            s {
                opacity { if (props.state != 4) 1.0 else 0.2 }
                +"C"
            }
            s {
                fontSize { if (props.state <= 1) 1.em else 0.em }
                +"?"
            }
            s {
                fontSize { if (props.state <= 1) 0.em else 1.em }
                s {
                    opacity { if (props.state != 4) 1.0 else 0.2 }
                    +": "
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        b { +"stable" }
                        +" "
                    }
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }

            div("code") {
                attrs["data-show-state"] = 5
                sourceCode("C", "int addition(int l, int r)")
            }
        }
        styledLi {
            opacity { if (props.state == 0) 0.0 else 1.0 }

            s {
                opacity { if (props.state !in 4..5) 1.0 else 0.2 }
                +"C++"
            }
            s {
                fontSize { if (props.state <= 1) 1.em else 0.em }
                +"?"
            }
            s {
                fontSize { if (props.state <= 1) 0.em else 1.em }
                s {
                    opacity { if (props.state !in 4..5) 1.0 else 0.2 }
                    +": "
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        +"unstable "
                    }
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }
            s {
                fontSize { if (props.state <= 2) 0.em else 1.em }
                s {
                    opacity { if (props.state !in 4..5) 1.0 else 0.2 }
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        +" & "
                    }
                    +"compatible with C "
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }
            div("code") {
                attrs["data-show-state"] = 6
                sourceCode("C++", "extern \"C\" int addition(int l, int r)")
            }
        }
        styledLi {
            opacity { if (props.state == 0) 0.0 else 1.0 }

            s {
                opacity { if (props.state !in 4..6) 1.0 else 0.2 }
                +"Rust"
            }
            s {
                fontSize { if (props.state <= 1) 1.em else 0.em }
                +"? "
                styledImg(src = "images/hearts.png") {
                    css {
                        height = 0.7.em
                    }
                }
            }
            s {
                fontSize { if (props.state <= 1) 0.em else 1.em }
                s {
                    opacity { if (props.state !in 4..6) 1.0 else 0.2 }
                    +": "
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        +"unstable "
                    }
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }
            s {
                fontSize { if (props.state <= 2) 0.em else 1.em }
                s {
                    opacity { if (props.state !in 4..6) 1.0 else 0.2 }
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        +" & "
                    }
                    +"compatible with C "
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }
            div("code") {
                attrs["data-show-state"] = 7
                sourceCode("Rust", "#[no_mangle]\npub extern fn addition(l: i32, r: i32) -> i32")
            }
        }
        styledLi {
            opacity { if (props.state == 0) 0.0 else 1.0 }

            s {
                opacity { if (props.state !in 4..7) 1.0 else 0.2 }
                +"Go"
            }
            s {
                fontSize { if (props.state <= 1) 1.em else 0.em }
                +"?"
            }
            s {
                fontSize { if (props.state <= 1) 0.em else 1.em }
                s {
                    opacity { if (props.state !in 4..7) 1.0 else 0.2 }
                    +": "
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        +"unstable "
                    }
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }
            s {
                fontSize { if (props.state <= 2) 0.em else 1.em }
                s {
                    opacity { if (props.state !in 4..7) 1.0 else 0.2 }
                    s {
                        fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                        +" & "
                    }
                    +"compatible with C "
                }
                s {
                    fontSize(props.state >= 4) { if (props.state >= 5) 0.em else 1.em }
                    +"ABI"
                }
            }
            div("code") {
                attrs["data-show-state"] = 8
                sourceCode("Go", "//export addition\nfunc addition(l C.int, r C.int) C.int")
            }
        }
    }

}
