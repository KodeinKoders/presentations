package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.h1
import styled.css
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.jni1() = slide(stateCount = 3) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 1: "
        }
        s {
            +"Kotlin interface"
        }
    }

    slideCode(props.state, "kotlin", """
package org.example.nativeb64.cpp

class NativeBase64Jvm : NativeBase64 {

    «f:2«external» override fun encode(bytes: ByteArray, url: Boolean): String

    «f:2«external» override fun decode(b64: String): ByteArray

}
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

}
