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

fun PresentationBuilder.kn1() = slide(stateCount = 7) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"K/N part 1: "
        }
        s {
            +"C-interop"
        }
    }

    slideCode(props.state, "kotlin", """
        # src/main/nativeInterop/cinterop/cpp_base64.def
        headers = base64_c.h
        headerFilter = base64_c.h
        «l:5-«
        «f:5«noStringConversion = base64_encode»

        »
        linkerOpts = -lcpp_base64
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

    br {}

    slideCode(props.state, "kotlin", """
        «f:3«external fun base64_max_decoded_len(b64_len: Int): Int
        external fun base64_max_encoded_len(bytes_len: Int): Int»

        «f:4-5«external fun base64_encode(bytes: «s:5-«CValuesRef<ByteVar>»«s:-4«String»?, bytes_len: Int, is_url: Int,
            out_chars: CValuesRef<ByteVar>?, out_chars_maxlen: Int, out_len: CValuesRef<IntVar>?
        ): CPointer<ByteVar>?»

        «f:6«external fun base64_decode(base64: String?,
            out_buff: CValuesRef<ByteVar>?, out_buff_maxlen: Int, out_len: CValuesRef<IntVar>?
        ): CPointer<ByteVar>?»
    """.trimIndent()) {
        opacity(props.state >= 2)
        transform(props.state < 2) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

    br {}

}
