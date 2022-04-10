package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.shownIf
import net.kodein.pres.util.transition
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val kotlin = Slide(
    name = "kotlin",
    stateCount = 6
) { state ->
    SubSlide(state in 0..1, fade) {
        H2 {
            Text("Kotlin is ")
            Span({ shownIf(state >= 1, fontGrow) }) {
                Text("also ")
            }
            Text("for Android!")
        }
        P({
            shownIf(state >= 1, fade)
        }) {
            Text("It is also for JVM servers, for iOS, for the Web, for embedded targets, etc.")
        }
    }

    SubSlide(state in 2..5, fade) {
        P({
            css {
                fontSize(1.3.em)
                fontWeight(200)
                fontFamily(KodeinFont.extended.name)
                "span.highlights" {
                    fontWeight(700)
                    fontFamily(KodeinFont.main.name)
                    color(KodeinColor.korail.css)
                }
                when(state) {
                    in 2..3 -> opacity(1.0)
                    else -> opacity(0.6)
                }
            }
            style { transition { "opacity"(300.ms) } }
        }) {
            Text("Kotlin Multiplatform is")
            Br {}
            Text("a ")
            Span({ classes("highlights") }) {
                Text("Gradle and IDE plugin")
            }
            Br {}
            Text("that defines ")
            Span({ classes("highlights") }) {
                Text("which sources")
            }
            Br {}
            Text("are to be compiled by ")
            Span({ classes("highlights") }) {
                Text("which compiler")
            }
            Text(".")
        }

        Ul({
            css {
                paddingTop(4.em)
                fontSize(1.1.em)
                listStyle("none")

                "span" {
                    color(KodeinColor.kaumon.css)
                }

                "span.common-first" {
                    opacity(
                        when (state) {
                            in 0..2 -> 0.0
                            3 -> 1.0
                            else -> 0.4
                        }
                    )
                }
                "span.common-others" {
                    opacity(
                        when (state) {
                            in 0..3 -> 0.0
                            else -> 0.4
                        }
                    )
                }
                "span.platform" {
                    opacity(
                        when (state) {
                            in 0..3 -> 0.0
                            4 -> 1.0
                            else -> 0.4
                        }
                    )
                }
                "span.compiler" {
                    opacity(
                        when (state) {
                            in 0..4 -> 0.0
                            else -> 1.0
                        }
                    )
                }
            }
            style { transition { "opacity"(300.ms) } }
        }) {
            Li {
                Span({ classes("common-others") }) { Text("Common sources") }
                Span({ classes("platform") }) { Text(" + JVM sources") }
                Span({ classes("compiler") }) { Text(" → JVM Compiler") }
            }
            Li {
                Span({ classes("common-first") }) { Text("Common sources") }
                Span({ classes("platform") }) { Text(" + Native sources") }
                Span({ classes("compiler") }) { Text(" → Native Compiler") }
            }
            Li {
                Span({ classes("common-others") }) { Text("Common sources") }
                Span({ classes("platform") }) { Text(" + JS sources") }
                Span({ classes("compiler") }) { Text(" → JS Compiler") }
            }
        }
    }
}