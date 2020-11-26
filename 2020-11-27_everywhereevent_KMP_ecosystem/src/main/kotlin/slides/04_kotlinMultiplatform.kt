package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.Move
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.dom.b
import react.dom.br
import react.dom.li
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.*

fun PresentationBuilder.kotlinMultiplatform() = slide(stateCount = 5) { (state, _) ->
    titledSlide("What is Kotlin/Multiplatform ?") {
        styledP {
            css {
                +kodein.chapo
                specific {
                    textAlign = TextAlign.center
                    fontWeight = FontWeight.lighter
                }
                color = Color.kodein.klycine
                transition(::opacity, 0.3.s)
                opacity = when(state) {
                    0 -> 0.0
                    1 -> 1.0
                    else -> 0.8
                }

                "b" {
                    color = Color.kodein.korail
                    fontWeight = FontWeight.medium
                }
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
                padding(top = 4.rem)
                "span" {
                    color = Color.kodein.kaumon
                    transition("opacity", 0.3.s)
                    "&.common-first" {
                        opacity = when (state) {
                            in 0..1 -> 0.0
                            2 -> 1.0
                            else -> 0.4
                        }
                    }
                    "&.common-others" {
                        opacity = when (state) {
                            in 0..2 -> 0.0
                            else -> 0.4
                        }
                    }
                    "&.platform" {
                        opacity = when (state) {
                            in 0..2 -> 0.0
                            3 -> 1.0
                            else -> 0.4
                        }
                    }
                    "&.compiler" {
                        opacity = when (state) {
                            in 0..3 -> 0.0
                            4 -> 1.0
                            else -> 0.4
                        }
                    }
                }
            }

            li {
                s("common-others") { +"Common sources" }
                s("platform") { +" + JVM sources" }
                s("compiler") { +" → JVM Compiler" }
            }
            li {
                s("common-first") { +"Common sources" }
                s("platform") { +" + Native sources" }
                s("compiler") { +" → Native Compiler" }
            }
            li {
                s("common-others") { +"Common sources" }
                s("platform") { +" + JS sources" }
                s("compiler") { +" → JS Compiler" }
            }
        }
    }
}
