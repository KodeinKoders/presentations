package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.translate
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import styled.*
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.jni4b() = slide(stateCount = 4) { props ->

    styledH1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"JNI part 4: "
        }
        s {
            +"Android tests"
        }
    }

    styledDiv {
        css {
            zIndex = 2
        }
        slideCode(props.state, "kotlin", """
            //build.gradle.kts:
            afterEvaluate {
                tasks.withType<AndroidUnitTest>().all {
                    enabled = false
                }
            }
        """.trimIndent()) {
            opacity(props.state >= 1)
            transform(props.state < 1) { translate(0.px, -2.em) }
        }
    }

    styledH3 {
        opacity(props.state >= 2)
        transform(props.state < 2) { translate(0.px, -2.em) }
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.7.em
            zIndex = 1
        }

        +"A file must exist in "

        s {
            css {
                fontSize = 0.8.em
                fontFamily = "fira code"
            }
            +"src/androidTests/"
            s {
                css { fontWeight = FontWeight.w700 }
                +"java"
            }
        }
    }

    slideCode(props.state, "bash", """
        ./gradlew connectedTests
    """.trimIndent()) {
        opacity(props.state >= 3)
        transform(props.state < 3) { translate(0.px, -2.em) }
    }

    br {}

}
