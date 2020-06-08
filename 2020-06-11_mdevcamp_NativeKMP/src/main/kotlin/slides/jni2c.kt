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

fun PresentationBuilder.jni2c() = slide(stateCount = 4) { props ->

    styledH1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 2c:  "
        }
        s {
            +"Android"
        }
        s {
            css { fontWeight = FontWeight.w200 }
            +" bridge"
        }
    }

    slideCode(props.state, "Kotlin", """
        // build.gradle.kts
        
        android {
                defaultConfig {
                    externalNativeBuild {
                        cmake {
        «f:2«                    arguments.addAll(listOf(
                                    "-DJAVA_AWT_LIBRARY=NotNeeded",
                                    "-DJAVA_JVM_LIBRARY=NotNeeded",
                                    "-DJAVA_INCLUDE_PATH2=NotNeeded",
                                    "-DJAVA_AWT_INCLUDE_PATH=NotNeeded"
                            ))»
                        }
                    }
                }
                externalNativeBuild {
        «f:3«            cmake {
                        setPath("${'$'}rootDir/cpp_jni/CMakeLists.txt")
                    }»
                }
            }
        """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

}
