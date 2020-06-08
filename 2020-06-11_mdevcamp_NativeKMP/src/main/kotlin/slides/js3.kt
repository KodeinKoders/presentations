package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.css
import styled.styledDiv
import styled.styledH1
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.js3() = slide(stateCount = 5) { props ->

    styledH1 {
//        css {
//            margin(0.em, 0.em, 0.2.em, 0.em)
//        }
        s {
            css { fontWeight = FontWeight.w200 }
            +"Wasm part 3: "
        }
        s {
            +"Tests!"
        }
    }

    styledDiv {
        css {
            marginBottom = 0.5.em
            zIndex = if (props.state == 1) 10 else 0
            opacity(props.state >= 1)
            transform(props.state == 1) { scale(2) }
        }

        slideCode(props.state, "javascript", """
        // webpack.config.d/wasm.config.js

        config.resolve.alias = {
            cpp_base64_js: path.resolve(__dirname,
                '../../../../cpp_native/build/cmake/out/base64Wasm/js/cpp_base64_js.js')
        }
        config.plugins.push(new webpack.IgnorePlugin(/(fs)/));
    """.trimIndent()) {
            fontSize = 0.4.em
        }
    }

    styledDiv {
        css {
            marginBottom = 0.5.em
            zIndex = if (props.state == 2) 10 else 0
            opacity(props.state >= 2)
            transform(props.state == 2) { scale(2) }
        }

        slideCode(props.state, "kotlin", """
            // build.gradle.kts
    
            tasks.withType<KotlinJsTest> {
                dependsOn(":cpp_native:buildBase64Wasm")
                doFirst {
                    copy {
                        from(rootDir.resolve(
                            "cpp_native/build/cmake/out/base64Wasm/js/cpp_base64_js.wasm"))
                        into(compilation.npmProject.dir)
                    }
                }
            }
        """.trimIndent()) {
    //        opacity(props.state >= 1)
    //        transform(props.state < 1) { translate(0.px, -2.em) }

            fontSize = 0.4.em
        }
    }

    styledDiv {
        css {
            marginBottom = 0.5.em
            zIndex = if (props.state == 3) 10 else 0
            opacity(props.state >= 3)
            transform(props.state == 3) { scale(2) }
        }

        slideCode(props.state, "javascript", """
            // karma.config.d/wasm.config.js
    
            config.files.push({
                pattern: __dirname + '/cpp_base64_js.wasm',
                included: false,
                served: true,
                type: 'wasm'
            })
        """.trimIndent()) {
            fontSize = 0.4.em
        }
    }

    styledDiv {
        css {
            marginBottom = 0.5.em
            zIndex = if (props.state == 4) 10 else 0
            opacity(props.state >= 4)
            transform(props.state == 4) { scale(2) }
        }

        slideCode(props.state, "bash", """
            ./gradlew jsTest
        """.trimIndent()) {
            fontSize = 0.4.em
        }
    }

}
