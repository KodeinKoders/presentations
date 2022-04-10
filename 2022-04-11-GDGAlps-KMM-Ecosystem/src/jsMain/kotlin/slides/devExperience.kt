package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.pres.util.transition
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.border
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import utils.Flex
import utils.SweepingHeader

val devExperience = Slide(
    name = "devExperience",
    stateCount = 4
) { state ->

    SubSlide(shownIf = state in 0..2, fade) {
        SweepingHeader(state, "Mobile developer common tasks")

        Flex(FlexDirection.Row, attrs = {
            css {
                top(10.em)
                height(80.percent); width(100.percent)
            }
        }) {
            Flex(
                direction = FlexDirection.Column,
                justifyContent = JustifyContent.Center,
                alignItems = AlignItems.Center,
                css = {
                    height(100.percent)
                    width(50.percent)
                    textAlign("center")

                    "p" {
                        width(90.percent)
                        lineHeight("1.4")
                        fontSize(1.2.em)
                        margin(0.25.em)
                        padding(0.25.em)
                        color(KodeinColor.dark.css)
                        backgroundColor(KodeinColor.korail.css)
                        border(0.1.em, LineStyle.Solid, KodeinColor.orange.css)
                        borderRadius(0.25.em)
                    }
                },
                attrs = { shownIf(state >= 1, fade) }
            ) {
                P { Text("Getting data from the backend") }
                P { Text("Serializing into a model") }
                P { Text("Saving the data for offline use") }
                P { Text("Test our use cases") }
                P({ shownIf(state >= 2, fade) }) { Text("Use it on Android") }
            }

            Flex(
                direction = FlexDirection.Column,
                justifyContent = JustifyContent.Center,
                alignItems = AlignItems.Center,
                css = {
                    height(100.percent)
                    width(50.percent)
                    textAlign("center")

                    "p" {
                        width(90.percent)
                        lineHeight("1.4")
                        fontSize(1.2.em)
                        margin(0.25.em)
                        padding(0.25.em)
                        color(KodeinColor.cute.css)
                        backgroundColor(KodeinColor.kyzantium.css)
                        border(0.1.em, LineStyle.Solid, KodeinColor.purple.css)
                        borderRadius(0.25.em)
                    }
                },
                attrs = { shownIf(state >= 2, fade) }
            ) {
                P { Text("Getting data from the backend") }
                P { Text("Serializing into a model") }
                P { Text("Saving the data for offline use") }
                P { Text("Test our use cases") }
                P({ shownIf(state >= 2, fade) }) { Text("Use it on iOS") }
            }
        }

    }

    SubSlide(shownIf = state == 3, fade) {
        H3({
            css {
                transition { "all"(1.s) }
                position(Position.Absolute)
                if (state > 0) {
                    top(0.5.em)
                } else {
                    top(6.em)
                }
                property("margin-left", "auto")
                property("margin-right", "auto")
            }
        }) {
            Text("KMM developer common tasks")
        }

        Flex(
            direction = FlexDirection.Column,
            justifyContent = JustifyContent.Center,
            alignItems = AlignItems.Center,
            css = {
                width(90.percent)
                textAlign("center")

                "p" {
                    width(85.percent)
                    lineHeight("1.4")
                    fontSize(1.2.em)
                    margin(0.25.em)
                    padding(0.25.em)
                    borderRadius(0.25.em)
                    color(KodeinColor.cute.css)
                    backgroundColor(KodeinColor.kyzantium.css)
                    border(0.1.em, LineStyle.Solid, KodeinColor.purple.css)
                }
            },
            attrs = { shownIf(state >= 1, fade) }
        ) {
            P { Text("Getting data from the backend") }
            P { Text("Serializing into a model") }
            P { Text("Saving the data for offline use") }
            P { Text("Test our use cases") }
        }

        Flex(
            direction = FlexDirection.Row,
            justifyContent = JustifyContent.Center,
            alignItems = AlignItems.Center,
            css = {
                width(80.percent)
                textAlign("center")

                "p" {
                    width(50.percent)
                    lineHeight("1.4")
                    fontSize(1.2.em)
                    margin(0.25.em)
                    padding(0.25.em)
                    borderRadius(0.25.em)
                    color(KodeinColor.dark.css)
                    backgroundColor(KodeinColor.korail.css)
                    border(0.1.em, LineStyle.Solid, KodeinColor.orange.css)
                }
            },
            attrs = { shownIf(state >= 1, fade) }
        ) {
            P { Text("Use it on Android") }
            P { Text("Use it on iOS") }
        }
    }
}
