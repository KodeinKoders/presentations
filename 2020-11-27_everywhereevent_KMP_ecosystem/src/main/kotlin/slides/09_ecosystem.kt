package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.*
import react.dom.*
import styled.*
import ws.charter.kodein
import ws.comp.logo
import ws.utils.*


fun PresentationBuilder.ecosystemSection() = slide(
    inTransitions = Flip,
    inTransitionDuration = 1000
) {
    styledH1 {
        css {
            +kodein.display2
            fontWeight = FontWeight.lighter
            color = Color.kodein.orange
        }
        +"The ecosystem"
    }
}

fun PresentationBuilder.officialLibraries() = slide { props ->
    titledSlide("JetBrains' official libraries") {
        styledUl {
            css {
                listStyleType = ListStyleType.disc
                color = Color.kodein.kaumon

                "span.text" {
                    color = Color.kodein.kaumon
                }
                "span.explain" {
                    color = Color.kodein.orange
                    paddingLeft = 1.em
                }

                "li" {
                    paddingTop = .6.em
                    "&.no-bullet" {
                        listStyleType = ListStyleType.none
                    }
                }
            }

            li {
                span("text") { +"kotlin-stdlib" }
                span("explain") { +"(Standard Library)" }
            }
            li {
                span("text") { +"ktor-client" }
                span("explain") { +"(HTTP / Socket / WS)" }
            }
            li {
                span("text") { +"kotlinx-serialization" }
                span("explain") { +"(JSON / CBOR / Protobuf ...)" }
            }
            li {
                span("text") { +"kotlinx-coroutines" }
                span("explain") { +"(Suspendable jobs / async work)" }
            }
            li {
                span("text") { +"kotlinx-datetime" }
                span("explain") { +"(Date & Time API)" }
            }
            li("no-bullet") {
                span("text") { +"..." }
            }
        }
    }
}

fun PresentationBuilder.community() = slide(
    stateCount = 3,
    outTransitions = Flip
) { (state, _) ->
    titledSlide("Community is awesome") {

        flexColumn {
            css {
                "p" {
                    textAlign = TextAlign.left
                    color = Color.kodein.korail
                    margin(horizontal = .5.rem, vertical = 0.em)
                }

                "ul" {
                    listStyleType = ListStyleType.disc
                    color = Color.kodein.kaumon
                    "span.explain" {
                        color = Color.kodein.orange
                        paddingLeft = 1.em
                    }
                    "li" {
                        paddingBottom = .6.em
                        "&.no-bullet" {
                            listStyleType = ListStyleType.none
                        }
                        "a" {
                            color = Color.kodein.kaumon
                            textDecoration = TextDecoration.none
                        }
                    }
                }
            }

            styledP {
                opacity(state >= 1)
                +"Libraries"
                styledDiv {
                    css {
                        width = 3.75.em
                        backgroundColor = Color.kodein.kamethiste
                        height = 0.05.em
                    }
                }
            }
            styledUl {
                opacity(state >= 1)
                styledLi {
                    +"multiplatform-settings"
                    span("explain") { +"(key-value settings)" }
                }
                styledLi {
                    +"Sately"
                    span("explain") { +"(multi-threaded mutable collections)" }
                }
                styledLi {
                    +"Moko"
                    span("explain") { +"(maps / permissions / ...)" }
                }
            }
            styledP {
                opacity(state >= 2)
                +"Examples"
                styledDiv {
                    css {
                        width = 4.2.em
                        backgroundColor = Color.kodein.kamethiste
                        height = 0.05.em
                    }
                }
            }
            styledUl {
                opacity(state >= 2)
                li {
                    a(href = "https://github.com/joreilly/PeopleInSpace", target = "_blank") {
                        +"github.com/joreilly/PeopleInSpace"
                    }
                }
                li {
                    a(href = "https://github.com/joreilly/BikeShare", target = "_blank") {
                        +"github.com/joreilly/BikeShare"
                    }
                }
                li {
                    a(href = "https://github.com/touchlab/KaMPKit", target = "_blank") {
                        +"KaMPKit"
                        span("explain") { +"(Template)" }
                    }
                }
            }
        }

/*
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
                    "&.no-bullet" {
                        listStyleType = ListStyleType.none
                    }
                }
            }

            flexRow(JustifyContent.flexStart) {
                css { width = 100.pct }
                styledLi {
                    css { flexGrow = 1.0 }
                    span("text") { +"Libraries" }

                    ul {
                        li {
                            span("text") { +"" }
                            span("explain") { +"" }
                        }
                    }
                }
                styledLi {
                    css { flexGrow = 1.0 }
                    span("text") { +"Tools" }

                    ul {
                        li {
                            span("text") { +"" }
                            span("explain") { +"" }
                        }
                    }
                }


            }
            flexRow(JustifyContent.flexStart) {

                styledLi {
                    css { flexGrow = 1.0 }
                    span("text") { +"Examples" }

                    ul {
                        li {
                            span("text") { +"" }
                            span("explain") { +"" }
                        }
                    }
                }

                styledLi {
                    css {
                        flexGrow = 1.0
                        +"no-bullet"
                    }
                    span("text") { +"..." }
                }

            }
        }
*/
    }
}

