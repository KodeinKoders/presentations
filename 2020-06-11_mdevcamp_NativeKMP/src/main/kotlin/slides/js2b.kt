package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.css
import styled.styledH1
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.js2b() = slide(stateCount = 4) { props ->

    styledH1 {
        css {
            margin(0.em, 0.em, 0.2.em, 0.em)
        }
        s {
            css { fontWeight = FontWeight.w200 }
            +"Wasm part 2b: "
        }
        s {
            +"Load & compile"
        }
    }

    slideCode(props.state, "kotlin", """
        class NativeBase64Js : NativeBase64 {

            companion object {
                internal lateinit var w: CppBase64Js

                private var loader: Promise<*>? = null
        
                «f:2«fun init(): Promise<*>» {
        «f:3«            if (loader == null) {
                        loader = cpp_base64_js().then(
                                onFulfilled = { w = it },
                                onRejected = { error("Could not load cpp_base64_js") }
                        )
                    }
                    return loader!!»
                }
            }
            
            /*...*/
        }
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

}
