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

fun PresentationBuilder.js1() = slide(stateCount = 7) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"Wasm part 1: "
        }
        s {
            +"Emscripten"
        }
    }

    slideCode(props.state, "cmake", """
        if (WASM)
            add_executable(cpp_base64_js src/base64.cpp src/base64_c.cpp)
            set_target_properties(
                    cpp_base64_js
                    PROPERTIES LINK_FLAGS "\
        «f:2«            -s WASM=1 -s MODULARIZE=1 -s EXPORT_NAME=cpp_base64_js \»
        «f:3«            -s DISABLE_EXCEPTION_CATCHING=0 \»
        «f:4«            -s EXPORTED_FUNCTIONS=[\
                        _base64_encode, _base64_decode, \
                        _base64_max_encoded_len, _base64_max_decoded_len \
                    ] \»
        «f:5«            -s EXTRA_EXPORTED_RUNTIME_METHODS=[UTF8ToString] \»
            ")
        «f:3«    target_compile_options(cpp_base64_js
                PUBLIC -fexceptions -s DISABLE_EXCEPTION_CATCHING=0)»
        
        «f:6«    install(
                    TARGETS cpp_base64_js
                    RUNTIME DESTINATION js
            )
            install(FILES ${'$'}{CMAKE_CURRENT_BINARY_DIR}/cpp_base64_js.wasm DESTINATION js)»
        endif ()
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

}
