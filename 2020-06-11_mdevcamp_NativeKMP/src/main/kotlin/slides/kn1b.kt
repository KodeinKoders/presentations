package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import org.kodein.kpres.sourceCode
import org.w3c.dom.HTMLElement
import org.w3c.dom.asList
import org.w3c.dom.get
import react.child
import react.dom.*
import react.functionalComponent
import react.useEffect
import react.useRef
import styled.css
import styled.styledUl
import ws.utils.*
import ws.utils.s

private fun CSSBuilder.listStyle() {
    li {
        paddingBottom = 0.5.em

        div { +"code" {
            height = 0.px
            overflow = Overflow.hidden
            fontSize = 0.8.em
            transition(::height, 300.ms)

            pre {
                marginTop = 0.3.em
            }
        }}
    }
}

fun PresentationBuilder.kn1b() = slide(stateCount = 5) { child(kn1bSlide, it) }

private val kn1bSlide by functionalComponent<SlideContentProps> { props ->
    val ul = useRef<HTMLElement?>(null)

    useEffect(listOf(props.state)) {
        val codes = ul.current!!.querySelectorAll("div.code").asList()
        codes.forEach {
            it as HTMLElement
            val showState = it.attributes["data-show-state"]!!.value.toInt()
            it.style.height = if (props.state == showState) "calc(${(it.firstChild!! as HTMLElement).clientHeight}px + 0.6em)" else "0"
        }
    }

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"K/N part 1b: "
        }
        s {
            +"compile"
        }
        s {
            css { fontWeight = FontWeight.w200 }
            +" for C-interop"
        }
    }

    styledUl {
        ref = ul
        css { listStyle() }

        li {
            s {
                opacity(props.state in listOf(0, 1), 0.2)
                +"Linux"
            }

            div("code") {
                attrs["data-show-state"] = 1
                sourceCode("Kotlin", """
                    "CMAKE_CXX_FLAGS:STRING" += "-D_GLIBCXX_USE_CXX11_ABI=0"
                    
                    val path = "${'$'}home/.konan/dependencies/target-gcc-toolchain-3-linux-x86-64"
                    "CMAKE_SYSROOT:PATH" += "${'$'}path/x86_64-unknown-linux-gnu/sysroot"
                    val cFlags = "--gcc-toolchain=${'$'}path"
                    "CMAKE_C_FLAGS:STRING" += cFlags
                    "CMAKE_CXX_FLAGS:STRING" += cFlags
                """.trimIndent())
            }
        }

        li {
            s {
                opacity(props.state in listOf(0, 2), 0.2)
                +"MacOS"
            }

            div("code") {
                attrs["data-show-state"] = 2
                sourceCode("Kotlin", """
                val cFlags = "-mmacosx-version-min=10.11"
                "CMAKE_C_FLAGS:STRING" += cFlags
                "CMAKE_CXX_FLAGS:STRING" += cFlags
                """.trimIndent())
            }
        }

        li {
            s {
                opacity(props.state in listOf(0, 3), 0.2)
                +"Windows"
            }

            div("code") {
                attrs["data-show-state"] = 3
                sourceCode("Kotlin", """
                    val path = "${'$'}home/.konan/dependencies/msys2-mingw-w64-x86_64-..."
                    "CMAKE_C_COMPILER:STRING" += "${'$'}path/bin/clang.exe"
                    "CMAKE_CXX_COMPILER:STRING" += "${'$'}path/bin/clang++.exe"
                    "CMAKE_SYSROOT:PATH" += path

                    val cFlags = "-target x86_64-pc-windows-gnu -femulated-tls"
                    "CMAKE_C_FLAGS:STRING" += cFlags
                    "CMAKE_CXX_FLAGS:STRING" += "${'$'}cFlags -std=c++11"
                """.trimIndent())
            }
        }

        li {
            s {
                opacity(props.state in listOf(0, 4), 0.2)
                +"iOS"
            }

            div("code") {
                attrs["data-show-state"] = 4
                sourceCode("Kotlin", """
                    "G" -= "Xcode"
                    "CMAKE_TOOLCHAIN_FILE:PATH" += "${'$'}pathToIosCmake/ios.toolchain.cmake"
                    "PLATFORM:STRING" += target // "os", "simulator64", ...
                """.trimIndent())
            }
        }
    }

}
