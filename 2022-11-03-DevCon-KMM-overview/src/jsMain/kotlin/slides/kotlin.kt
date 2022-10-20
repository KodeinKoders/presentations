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
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.listStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.cic.css
import utils.wordCloud


val kotlin = Slide(
    name = "kotlin",
    stateCount = 7
) { state ->
    SubSlide(state in 0..2, fade) {
        H2 {
            Text("Kotlin is ")
            Span({ shownIf(state >= 1, fontGrow) }) {
                Text("also ")
            }
            Text("for Android!")
        }
        wordCloud(state, 2, "JVM server") { top(18.percent); left(12.percent) }
        wordCloud(state, 2, "Windows") { top(18.percent); right(27.percent) }
        wordCloud(state, 2, "iOS") { top(32.percent); left(20.percent) }
        wordCloud(state, 2, "Embedded Systems") { top(32.percent); right(22.percent) }
        wordCloud(state, 2, "Linux") { bottom(30.percent); right(15.percent) }
        wordCloud(state, 2, "MacOS") { bottom(18.percent); left(10.percent) }
        wordCloud(state, 2, "JVM Desktop") { bottom(25.percent); right(38.percent) }
        wordCloud(state, 2, "WASM") { bottom(15.percent); right(10.percent) }
        wordCloud(state, 2, "Web") { bottom(35.percent); left(25.percent) }
    }

    SubSlide(state in 3..6, fade) {
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
                    in 3..4 -> opacity(1.0)
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
                            in 1..3 -> 0.0
                            4 -> 1.0
                            else -> 0.4
                        }
                    )
                }
                "span.common-others" {
                    opacity(
                        when (state) {
                            in 1..4 -> 0.0
                            else -> 0.4
                        }
                    )
                }
                "span.platform" {
                    opacity(
                        when (state) {
                            in 1..4 -> 0.0
                            5 -> 1.0
                            else -> 0.4
                        }
                    )
                }
                "span.compiler" {
                    opacity(
                        when (state) {
                            in 1..5 -> 0.0
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