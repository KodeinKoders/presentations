package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.css.properties.translate
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.sourceCode
import react.dom.br
import react.dom.h1
import react.dom.h2
import styled.css
import styled.styledDiv
import styled.styledH2
import styled.styledH3
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform


fun PresentationBuilder.nativeCFunction() = slide(stateCount = 7) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"Native "
        }
        +"C"
        s {
            css { fontWeight = FontWeight.w200 }
            +" header:"
        }
    }

    styledDiv {
        css {
            zIndex = 2
        }
        slideCode(
                state = props.state,
                lang = "C",
                code = """
                    int base64_max_encoded_len(int str_len);
                    int base64_max_decoded_len(int sb64_len);
    
                    «f:5«char*» base64_encode(«f:1«const char* bytes, int bytes_len, int is_url»,
                                        «f:2«char *out_chars, int out_chars_maxlen, int* out_len»);
    
                    «f:5«char*» base64_decode(«f:3«const char* base64»,
                                        «f:4«char *out_buff, int out_buff_maxlen, int* out_len»);
                """.trimIndent()
        )
    }

    styledH3 {
        opacity(props.state >= 6)
        transform(props.state < 6) { translate(0.px, -2.em) }
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.7.em
            zIndex = 1
        }
        +"No matter the implementation language..."
        br {}
        +"...as long as it compiles to "
        s {
            css { fontWeight = FontWeight.w700 }
            +"all needed targets"
        }
        +"!"
    }

}
