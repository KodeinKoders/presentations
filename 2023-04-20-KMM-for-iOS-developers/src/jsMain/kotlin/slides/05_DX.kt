package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transition
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.shownIf
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.Transitions
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.times
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import utils.Flex
import utils.SweepingHeader

val DX =
    Slide(name = "dx", stateCount = 6) { state ->
        SweepingHeader(state) {
            Span({ shownIf(state <= 1, fontGrow) }) { Text("Mobile") }
            Span({ shownIf(state > 1, fontGrow) }) { Text("KMM") }
            Text(" developer ")
            Span({ shownIf(state <= 1, fontGrow) }) { Text("common ") }
            Text("tasks")
        }

        Flex(direction = FlexDirection.Row, css = {
            height(80.percent)
            width(100.percent)
        }) {
            Flex(
                direction = FlexDirection.Column,
                justifyContent = JustifyContent.Start,
                alignItems = AlignItems.Center,
                css = {
                    height(100.percent)
                    width(50.percent)
                    "p" {
                        color(KodeinColor.dark.css)
                        backgroundColor(KodeinColor.korail.css)
                    }
                },
                attrs = { shownIf(state >= 1, fade) }
            ) {
                P({ shownIf(state in 1..2, plt(Dir.L)) }) { Text("I/O") }
                P({ shownIf(state == 1, plt(Dir.L)) }) { Text("Business logic") }
                P({ shownIf(state in 1..3, plt(Dir.L)) }) { Text("Behaviour") }
                P({ shownIf(state in 1..4, plt(Dir.L)) }) { Text("Test everything you can") }
                P({ shownIf(state >= 1, plt(Dir.L)) }) { Text("Jetpack Compose") }
            }

            Flex(
                direction = FlexDirection.Column,
                justifyContent = JustifyContent.Start,
                alignItems = AlignItems.Center,
                css = {
                    height(100.percent)
                    width(50.percent)
                    "p" {
                        color(KodeinColor.cute.css)
                        backgroundColor(KodeinColor.kyzantium.css)
                    }
                },
                attrs = { shownIf(state >= 1, fade) }
            ) {
                P({ shownIf(state in 1..2, plt(Dir.R)) }) { Text("I/O") }
                P({ shownIf(state == 1, plt(Dir.R)) }) { Text("Business logic") }
                P({ shownIf(state in 1..3, plt(Dir.R)) }) { Text("Behaviour") }
                P({ shownIf(state in 1..4, plt(Dir.R)) }) { Text("Test everything you can") }
                P({ shownIf(state >= 1, plt(Dir.R)) }) { Text("SwiftUI") }
            }

            Flex(
                direction = FlexDirection.Column,
                justifyContent = JustifyContent.Start,
                alignItems = AlignItems.Center,
                css = {
                    height(100.percent)
                    width(100.percent)
                    position(Position.Absolute)
                    "p" {
                        width(95.percent)
                        color(KodeinColor.dark.css)
                        backgroundColor(KodeinColor.kaumon.css)
                    }
                },
                attrs = { shownIf(state >= 2, fade) }
            ) {
                P({ shownIf(state >= 3, klt) }) { Text("I/O") }
                P({ shownIf(state >= 2, klt) }) { Text("Business logic") }
                P({ shownIf(state >= 4, klt) }) { Text("Behaviour") }
                P({ shownIf(state >= 5, klt) }) { Text("Test everything you can") }
            }
        }
    }

private enum class Dir(val m: Int) { L(-1), R(1) }

// Platform Layer Transition
private fun plt(d: Dir) = object : Transition {
    override val cssTransitions: Transitions.() -> Unit = {
        "opacity" { duration = 1.s }
        "transform" { duration = 500.ms }
    }
    override val hiddenStyle: StyleScope.() -> Unit = {
        transform {
            translateX(5.em * d.m)
        }
        opacity(0)
    }
}

// Kotlin Layer Transition
private val klt = object : Transition {
    override val cssTransitions: Transitions.() -> Unit = {
        "opacity" { duration = 1.s }
        "transform" { duration = 500.ms }
    }
    override val hiddenStyle: StyleScope.() -> Unit = {
        transform {
            scale(0.8)
        }
        opacity(0)
    }
}
