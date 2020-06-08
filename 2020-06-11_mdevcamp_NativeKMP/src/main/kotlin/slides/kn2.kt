package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.css
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.kn2() = slide(stateCount = 9) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"K/N part 2: "
        }
        s {
            +"Façade"
        }
    }

    slideCode(props.state, "kotlin", """
        «f:6«private fun CPointer<ByteVar>?.check() {
            if (this == null) return
            val message = this.toKString()
        «f:7«    nativeHeap.free(this)»
            throw NativeBase64.Error(message)
        }»
    
        override fun encode(bytes: ByteArray, url: Boolean): String {
        «f:2«    memScoped {»
        «f:3«        val resultMaxLen = base64_max_encoded_len(bytes.size)»
        «f:4«        val resultChars = allocArray<ByteVar>(resultMaxLen + 1)
                val resultLen = alloc<IntVar>()»
        «f:5«        base64_encode(bytes.toCValues(), bytes.size, url.toByte().toInt(),
                              resultChars, resultMaxLen, resultLen.ptr)»
        «f:6«            .check()»
        «f:8«        resultChars[resultLen.value] = 0
                return resultChars.toKString()»
            «f:2«}»
        }
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

}
