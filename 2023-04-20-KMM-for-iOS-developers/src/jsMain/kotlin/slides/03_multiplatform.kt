package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.animatedWith
import net.kodein.pres.hiddenIf
import net.kodein.theme.KodeinColor
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css

val multiplatform =
    listOf(
            Slide(name = "kotlin-compilation", stateCount = 4) { state ->
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
              }
            },
      Slide(
        name = "sharing-is-caring",
      ) {
        H2 { Text("So, what can be shared?") }
      },
      Slide("platforms", stateCount = 3) { state ->
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
            hiddenIf(state < 2, fade)
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
            hiddenIf(state != 1, fade)
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
            hiddenIf(state >= 1, fade)
          }
        }
      }
    )
        .animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
