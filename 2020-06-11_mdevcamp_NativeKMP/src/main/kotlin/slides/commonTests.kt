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


fun PresentationBuilder.commonTests() = slide(stateCount = 4) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"Common Kotlin "
        }
        +"tests"
    }

    styledDiv {
        css {
            zIndex = 2
        }
        slideCode(
                state = props.state,
                lang = "kotlin",
                code = """
                    class B64Tests {
                        private val b64 = getCppNativeBase64()
                    
                    «f:1«    @Test fun simpleB64Encode() = initAndRun {
                            assertEquals("U2Fsb21vbg==", b64.encode("Salomon".encodeToByteArray()))
                            assertEquals("AQIDBAU=", b64.encode(byteArrayOf(1, 2, 3, 4, 5)))
                        }»
                    
                    «f:2«    @Test fun simpleB64Decode() = initAndRun {
                            assertEquals("Salomon", b64.decode("U2Fsb21vbg==").decodeToString())
                            assertTrue(byteArrayOf(1, 2, 3, 4, 5).contentEquals(b64.decode("AQIDBAU=")))
                        }»
                    «f:3«
                        @Test fun invalidCharacter() = initAndRun {
                            val ex = assertFailsWith<NativeBase64.Error> { b64.decode("AB*CD") }
                            assertEquals("Input is incorrect: found non-base64 caracter 0x2a '*'.", ex.message)
                        }»
                    }
                """.trimIndent()
        ) {
            "span.txt" {
                fontSize = 0.8.em
            }
        }
    }

}
