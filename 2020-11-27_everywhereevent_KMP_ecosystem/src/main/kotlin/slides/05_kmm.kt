package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.classes
import org.kodein.kpres.Flip
import org.kodein.kpres.Move
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.SlideContentProps
import react.dom.b
import react.dom.br
import react.dom.li
import styled.*
import ws.charter.KodeinStyles
import ws.charter.kodein
import ws.comp.logo
import ws.utils.*

fun PresentationBuilder.kmm() = slide(stateCount = 5) { (state, _) ->
    titledSlide("Kotlin/Multiplatform Mobile") {
        flexColumn(justifyContent = JustifyContent.center, alignItems = Align.center) {
            css {
                height = 100.pct
                color = Color.kodein.kaumon
                textAlign = TextAlign.center

                "p" {
                    width = 50.pct
                    lineHeight = LineHeight("1.5")
                    margin(0.25.em)
                    padding(0.25.em)
                    verticalAlign = VerticalAlign.middle

                    "&.android" {
                        color = Color.kodein.kaumon
                        backgroundColor = Color.kodein.kuivre
                        border(.1.em, BorderStyle.solid, Color.kodein.orange, 0.25.em)
                    }

                    "&.ios" {
                        color = Color.kodein.klycine
                        backgroundColor = Color.kodein.kinzolin
                        border(.1.em, BorderStyle.solid, Color.kodein.purple, 0.25.em)
                    }
                }
            }

            flexRow {
                css { width = 100.pct }
                styledP {
                    attrs.classes += "android"
                    css {
                        opacity = if (state >= 4) 1 else 0
                    }
                    +"Android App"
                }

                styledP {
                    attrs.classes += "ios"
                    css {
                        opacity = if (state >= 4) 1 else 0
                    }
                    +"iOS App"
                }
            }


            flexRow {
                css { width = 100.pct }
                styledP {
                    attrs.classes += "android"
                    css {
                        opacity = if (state >= 3) 1 else 0
                    }
                    +"Android .aar"
                }

                styledP {
                    attrs.classes += "ios"
                    css {
                        opacity = if (state >= 3) 1 else 0
                    }
                    +"iOS Framework"
                }
            }

            flexRow {
                css { width = 100.pct }
                styledP {
                    attrs.classes += "android"
                    css {
                        flexGrow = 1.0
                        opacity = if (state >= 2) 1 else 0
                    }
                    +"JVM"
                }

                styledP {
                    css {
                        flexGrow = 1.0
                        color = Color.kodein.kyzantium
                        backgroundColor = Color.kodein.korail
                        border(.1.em, BorderStyle.solid, Color.kodein.orange, 0.25.em)
                        opacity = if (state >= 1) 1 else 0
                    }

                    +"Common"
                }

                styledP {
                    attrs.classes += "ios"
                    css {
                        flexGrow = 1.0
                        opacity = if (state >= 2) 1 else 0
                    }
                    +"Native"
                }
            }
        }
    }
}
