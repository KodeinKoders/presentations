package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.h1
import styled.css
import styled.styledH1
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.jni2b() = slide(stateCount = 4) { props ->

    styledH1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 2b: bridge "
        }
        s {
            +"Compilation"
        }
    }

    slideCode(props.state, "CMake", """
        project(CPP_Base64_JNI)

        find_package(JNI REQUIRED)
        include_directories(${'$'}{JNI_INCLUDE_DIRS})

        «f:2«add_subdirectory(../cpp_native EXCLUDE_FROM_ALL cpp_base64)
        include_directories(../cpp_native/src)»
        
        «f:3«add_library(cpp_base64_jni SHARED src/base64_jni.cpp)
        target_link_libraries(cpp_base64_jni cpp_base64)»
        
        install(
            TARGETS cpp_base64_jni
            ARCHIVE DESTINATION lib
            LIBRARY DESTINATION lib
            COMPONENT library
        )
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
//            fontSize = 0.6.em
        }
    }

}
