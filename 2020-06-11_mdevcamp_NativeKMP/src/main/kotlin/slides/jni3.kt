package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.translate
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.*
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.jni3() = slide(stateCount = 6) { props ->

    styledH1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 3: "
        }
        s {
            +"Kotlin façade"
        }
    }

    styledDiv {
        css {
            zIndex = 2
        }
        slideCode(props.state, "C++", """
            class NativeBase64Jvm : NativeBase64 {
            
            «l:2-«    companion object {
                    init {
                        «f:3«System.loadLibrary("cpp_base64_jni")»
                    }
                }
    
            »
                external override fun encode(bytes: ByteArray, url: Boolean): String
            
                external override fun decode(b64: String): ByteArray
            
            }
        """.trimIndent()) {
            opacity(props.state >= 1)
            transform(props.state < 1) { translate(0.px, -2.em) }

            "span.txt" {
                fontSize = 0.8.em
            }
        }
    }

    styledH3 {
        opacity(props.state >= 4)
        transform(props.state < 4) { translate(0.px, -2.em) }
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.7.em
            zIndex = 1
        }
        s {
            css { fontWeight = FontWeight.w700 }
            +"On the JVM:"
        }
        +" distribute or bundle the "
        br {}
        +" native lib for "
        s {
            css { fontWeight = FontWeight.w700 }
            +"each platform"
        }
        +" with the .jar!"
        br {}
        styledSpan {
            opacity(props.state >= 5)
            css {
                color = Color.green
                top = 0.px
                left = 0.px
                width = 100.pct

            }
            +"Thanks, "
            s {
                css { fontWeight = FontWeight.w700 }
                +"Android"
            }
            +" for your "
            s {
                css { fontWeight = FontWeight.w700 }
                +"AAR"
            }
            +" bundle format!"
        }
    }

}
