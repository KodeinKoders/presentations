package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.css.properties.translate
import kotlinx.html.classes
import org.kodein.kpres.PresentationBuilder
import react.dom.*
import styled.*
import ws.utils.opacity
import ws.utils.s
import ws.utils.transform

fun PresentationBuilder.kotlinMultiplatform() = slide(stateCount = 4) { props ->

    styledH2 {
        css {
            fontWeight = FontWeight.w300
        }
        +"Kotlin/Multiplatform is"
        br {}
        +"a "
        b { +"Gradle and IDE plugin" }
        br {}
        +"that defines "
        b { +"which sources" }
        br {}
        +"are to be compiled by "
        b { +"which compiler" }
        +"."
    }

    styledUl {
        css {
            "span" {
                transition("opacity", 0.3.s)
                "&.common" {
                    opacity = when (props.state) {
                        0 -> 0.0
                        1 -> 1.0
                        else -> 0.4
                    }
                }
                "&.platform" {
                    opacity = when (props.state) {
                        in 0..1 -> 0.0
                        2 -> 1.0
                        else -> 0.4
                    }
                }
                "&.compiler" {
                    opacity = when (props.state) {
                        in 0..2 -> 0.0
                        3 -> 1.0
                        else -> 0.4
                    }
                }
            }
        }

        li {
            s("common") { +"Common sources" }
            s("platform") { +" + JVM sources" }
            s("compiler") { +" → JVM Compiler" }
        }
        li {
            s("common") { +"Common sources" }
            s("platform") { +" + Native sources" }
            s("compiler") { +" → Native Compiler" }
        }
        li {
            s("common") { +"Common sources" }
            s("platform") { +" + JS sources" }
            s("compiler") { +" → JS Compiler" }
        }
    }

}
