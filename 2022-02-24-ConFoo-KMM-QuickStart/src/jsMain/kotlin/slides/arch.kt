package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val arch = Slide(
    name = "arch",
    stateCount = 2
) { state ->
    Div({
        css {
            width((100.percent))
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)

            "div.row" {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
            }
            "div.col" {
                flex(1)
                flexBasis("0")
                backgroundColor(KodeinColor.kinzolin.css)
                border(.25.em, LineStyle.Solid, KodeinColor.kyzantium.css)
                borderRadius(.5.em)
                margin(.5.em)
                height(7.5.em)
            }
            "h2" {
                fontSize(1.3.em)
                fontFamily(KodeinFont.main.name)
            }
        }
    }) {
        Div({ classes("row") }) {
            Div({ classes("col") }) {
                H2 { Text("Communication") }
                Ul({ shownIf(state >= 1, fade) }) {
                    Li { Text("Ktor-Client") }
                }
            }
            Div({ classes("col") }) {
                H2 { Text("Persistence") }
                Ul({ shownIf(state >= 1, fade) }) {
                    Li { Text("SQLDelight") }
                    Li { Text("Kodein-DB") }
                    Li { Text("Okio") }
                }
            }
            Div({ classes("col") }) {
                H2 { Text("Behaviour") }
                Ul({ shownIf(state >= 1, fade) }) {
                    Li { Text("Simple MVI") }
                    Li { Text("MVIKotlin") }
                }
            }
        }
        Div({ classes("row") }) {
            Div({
                classes("col")
                style {
                    margin(.5.em, 15.percent)
                    height(5.em)
                }
            }) {
                H2 { Text("Modularization") }
                Ul({ shownIf(state >= 1, fade) }) {
                    Li { Text("Kodein-DI") }
                }
            }
        }
    }
}
