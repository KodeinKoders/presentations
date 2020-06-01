package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.IterationCount
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


fun PresentationBuilder.commonInterface() = slide(stateCount = 3) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"Common Kotlin "
        }
        +"interface"
    }

    styledDiv {
        css {
            zIndex = 2
        }
        slideCode(
                state = props.state,
                lang = "kotlin",
                code = """
                    interface NativeBase64 {
                    
                        class Error(msg: String) : Exception(msg)
                    
                        fun encode(bytes: ByteArray, url: Boolean = false): String
                    
                        fun decode(b64: String): ByteArray
                    
                    }
                    «l:2-«
                    expect fun getCppNativeBase64(): NativeBase64»
                """.trimIndent()
        )
    }

    styledH3 {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.7.em
            zIndex = 1
        }
        +"Each "
        s {
            css { fontWeight = FontWeight.w700 }
            +"platform"
        }
        +" will provide"
        br {}
        +"its own native implementation"
    }

}
