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

fun PresentationBuilder.js2() = slide(stateCount = 12) { props ->

    styledH1 {
        css {
            margin(0.em, 0.em, 0.2.em, 0.em)
        }
        s {
            css { fontWeight = FontWeight.w200 }
            +"Wasm part 2: "
        }
        s {
            +"Façade"
        }
    }

    slideCode(props.state, "kotlin", """
        «ff:2-3«private inline fun <R> malloc(size: Int, block: (Ptr) -> R): R {
            val ptr = «f:11«w».«f:3«_malloc(size)»
            try { return block(ptr) } finally { «f:11«w».«f:3«_free(ptr)» }
        }»
    
        «ff:9«private fun Ptr.check() {
            if (this == nullPtr) return
            val str = «f:11«w».UTF8ToString(this)
            «f:11«w»._free(this)
            throw NativeBase64.Error(str)
        }»
    
        override fun encode(bytes: ByteArray, url: Boolean): String {
        «ff:4-5«    val resultMaxLen = «f:11«w»._base64_max_encoded_len(bytes.size)
            malloc(bytes.size) { bytesPtr ->
                «f:5«w.HEAP8.set(bytes.unsafeCast<Array<Byte>>(), offset = bytesPtr)»»
        «ff:6-8«        malloc(resultMaxLen) { resultPtr ->
                    malloc(Int.SIZE_BYTES) { resultLenPtr ->
                        val isUrl = if (url) 1 else 0
        «f:7«                «f:11«w»._base64_encode(bytesPtr, bytes.size, isUrl,
                                         resultPtr, resultMaxLen, resultLenPtr)»
                                «f:8«.check()»»
        «ff:10«                val resultLen = w.HEAP32[resultLenPtr / 4]
                        val resultTArray = w.HEAP8.subarray(resultPtr, resultPtr + resultLen)
                        val resultArray = js("[]").slice.call(resultTArray)
                                .unsafeCast<ByteArray>()
                        return resultArray.decodeToString()»
                    }
                }
            }
        }
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.55.em
        }
    }

}
