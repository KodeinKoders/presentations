package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Dimmed
import utils.Stamp
import utils.dimmed


val d5_ksp = listOf(
    Slide(
        name = "kerbal-space-program",
        stateCount = 6
    ) { state ->
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                width(100.percent)
                height(100.percent)
            }
        }) {
            Img(
                src = "img/kerbal-sp.png"
            ) {
                css {
                    width(40.percent)
                    property("object-fit", "cover")
                    property("object-position", "90%")
                    transitions { "width" { duration = 1.s } }
                }
                style {
                    width(if (state == 0) 100.percent else 50.percent)
                }
            }
            Div({
                css {
                    transitions { "width" { duration = 1.s } }
                    paddingLeft(1.5.em)
                }
                style {
                    width(if (state == 0) 0.percent else 50.percent)
                }
            }) {
                Ul({
                    css {
                        "li" {
                            padding(1.5.em, 0.em)
                        }
                    }
                }) {
                    Li({ shownIf(state >= 2, fade) }) { Text("Build a Rocket and explore space!") }
                    Li({ shownIf(state >= 3, fade) }) {
                        Text("Purposefully undocumented:")
                        Br()
                        Text("There is no manual for discovery!")
                    }
                    Li({ shownIf(state >= 4, fade) }) {
                        Text("Trial and error...")
                        Br()
                        Text("Not all Kerbals will survive the journey.")
                    }
                    Li({ shownIf(state >= 5, fade) }) {
                        Text("Great sense of accomplishment!")
                    }
                }
            }
        }
    },

    Slide(
        name = "kotlin-symbol-processor",
        stateCount = 6
    ) { state ->
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                width(100.percent)
                height(100.percent)
            }
        }) {
            Img(
                src = "img/kotlin-sp.png"
            ) {
                css {
                    width(40.percent)
                    property("object-fit", "cover")
                    property("object-position", "51.5%")
                    transitions { "width" { duration = 1.s } }
                }
                style {
                    width(if (state == 0) 100.percent else 50.percent)
                }
            }
            Div({
                css {
                    transitions { "width" { duration = 1.s } }
                    paddingLeft(1.5.em)
                }
                style {
                    width(if (state == 0) 0.percent else 50.percent)
                }
            }) {
                Ul({
                    css {
                        "li" {
                            padding(1.5.em, 0.em)
                        }
                    }
                }) {
                    Li({ shownIf(state >= 2, fade) }) { Text("Build a Compiler code processor!") }
                    Li({ shownIf(state >= 3, fade) }) {
                        Text("Lightly documented:")
                        Br()
                        Text("There is no manual for discovery!")
                    }
                    Li({ shownIf(state >= 4, fade) }) {
                        Text("Trial and error...")
                        Br()
                        Text("Not all neurons will survive the journey.")
                    }
                    Li({ shownIf(state >= 5, fade) }) {
                        Text("Great sense of accomplishment!")
                    }
                }
            }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
