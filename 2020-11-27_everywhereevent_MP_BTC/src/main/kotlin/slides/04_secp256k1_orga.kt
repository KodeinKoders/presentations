package ws.slides

import kotlinx.css.*
import org.kodein.kpres.PresentationBuilder
import react.dom.li
import react.dom.span
import react.dom.ul
import styled.css
import styled.styledUl
import ws.charter.kodein
import ws.utils.titledSlide

fun PresentationBuilder.secp256k1_orga() = slide(
    containerStyle = { justifyContent = JustifyContent.flexStart },
) { props ->
    titledSlide("Secp256k1 project organisation") {
        styledUl {
            css {
                listStyleType = ListStyleType.disc
                color = Color.kodein.kaumon
                "ul" {
                    listStyleType = ListStyleType.circle
                    color = Color.kodein.kaumon
                    "ul" {
                        listStyleType = ListStyleType.square
                        "ul" {
                            listStyleType = ListStyleType.disc
                        }

                    }
                }

                "span.text" {
                    color = Color.kodein.kaumon
                }
                "span.explain" {
                    color = Color.kodein.orange
                    paddingLeft = 1.em
                }

                "li" {
                    paddingTop = .6.em
                }
            }

            li {
                span("text") { +"/" }
                span("explain") { +"(Interface & Native implementation)" }

                ul {
                    li {
                        span("text") { +"Native-Lib" }
                        span("explain") { +"(Secp256k1 library)" }
                    }
                    li {
                        span("text") { +"JNI" }
                        span("explain") { +"(Kotlin implementation & JNI bindings)" }

                        ul {
                            li {
                                span("text") { +"C" }
                                span("explain") { +"(C JNI bindings)" }
                            }
                            li {
                                span("text") { +"Android" }
                                span("explain") { +"(AAR packaging)" }
                            }
                            li {
                                span("text") { +"JVM" }
                                span("explain") { +"(JNI extraction)" }

                                ul {
                                    li {
                                        span("text") { +"Darwin, Linux, Mingw" }
                                        span("explain") { +"(JAR packaging)" }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
