package slides

import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.listStyle
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transitions
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.compose.html.css.css
import org.kodein.compose.html.pres.Animations
import org.kodein.compose.html.pres.Slide
import org.kodein.compose.html.pres.Slides
import org.kodein.compose.html.pres.Transitions
import org.kodein.compose.html.pres.hiddenIf
import org.kodein.compose.html.pres.shownIf


val multiplatform = Slides(Animations.Move(towards = Animations.Move.Towards.Bottom)) {

  +Slide(name = "kotlin-compilation", stateCount = 4) { state ->
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
        when (state) {
          in 0..1 -> opacity(1.0)
          else -> opacity(0.6)
        }
      }
      style { transitions {} }
    }) {
      Text("Kotlin Multiplatform is")
      Br {}
      Text("a ")
      Span({ classes("highlights") }) { Text("Gradle and IDE plugin") }
      Br {}
      Text("that defines ")
      Span({ classes("highlights") }) { Text("which sources") }
      Br {}
      Text("are to be compiled by ")
      Span({ classes("highlights") }) { Text("which compiler") }
      Text(".")
    }
    Ul({
      css {
        paddingTop(4.em)
        fontSize(1.1.em)
        listStyle("none")

        "span" { color(KodeinColor.kaumon.css) }

        "span.common-first" {
          opacity(
            when (state) {
              0 -> 0.0
              1 -> 1.0
              else -> 0.4
            })
        }
        "span.common-others" {
          opacity(
            when (state) {
              in 0..1 -> 0.0
              else -> 0.4
            })
        }
        "span.platform" {
          opacity(
            when (state) {
              in 0..1 -> 0.0
              2 -> 1.0
              else -> 0.4
            })
        }
        "span.compiler" {
          opacity(
            when (state) {
              in 0..2 -> 0.0
              else -> 1.0
            })
        }
      }
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
      Li {
        Span({ classes("common-others") }) { Text("Common sources") }
        Span({ classes("platform") }) { Text(" + WASM sources") }
        Span({ classes("compiler") }) { Text(" → WASM Compiler") }
      }
    }
  }

  +Slide(
    name = "sharing-is-caring",
  ) {
    H2 { Text("So, what can be shared?") }
  }

  +Slide("platforms", stateCount = 3) { state ->
    Div({
      css {
        position(Position.Absolute)
        width(100.percent)
        height(100.percent)
        top(0.percent)
        left(0.percent)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.Center)
      }
    }) {
      Img(src = "img/kmm3.svg") {
        css { height(20.em) }
        hiddenIf(state < 2, Transitions.Fade())
      }
    }
    Div({
      css {
        position(Position.Absolute)
        width(100.percent)
        height(100.percent)
        top(0.percent)
        left(0.percent)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.Center)
      }
    }) {
      Img(src = "img/kmm2.svg") {
        css { height(20.em) }
        hiddenIf(state != 1, Transitions.Fade())
      }
    }
    Div({
      css {
        position(Position.Absolute)
        width(100.percent)
        height(100.percent)
        top(0.percent)
        left(0.percent)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.Center)
      }
    }) {
      Img(src = "img/kmm.svg") {
        css { height(20.em) }
        hiddenIf(state >= 1, Transitions.Fade())
      }
    }
  }
}
