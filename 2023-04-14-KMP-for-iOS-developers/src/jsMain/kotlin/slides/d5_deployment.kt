package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import utils.JetBrains
import utils.LangMarker
import utils.dimmed
import kotlin.time.Duration.Companion.seconds

val d5_deployment = listOf(
    Slide(
        name = "deployment-title",
    ) {
        P({ css { fontSize(2.em) } }) {
            Text("What can we, as KMM developers,")
            Br()
            Text("do to improve")
            Br()
            Span({ css { color(KodeinColor.orange.css) } }) {
                Text("deployment of artifacts")
            }
            Br()
            Text("to iOS developers?")
        }
    },
    Slide(
        name = "deployment-problem",
        stateCount = 6
    ) { state ->
        P({ css { fontSize(2.em) } }) {
            Span({ css { color(KodeinColor.korail.css) } }) {
                Span({ shownIf(state < 2, fontGrow) }) { Text("Cocoa-Pod") }
                Span({ shownIf(state >= 2, fontGrow) }) { Text("Swift Package Manager") }
            }
            Br()
            Span({ shownIf(state < 4, fontGrow) }) {
                Text("is ")
                Span({
                    css { fontWeight(700) }
                    shownIf(state >= 2, fontGrow)
                }) {
                    Text("not ")
                }
            }
            Span({
                shownIf(state >= 4, fontGrow)
                css { fontWeight(700) }
            }) { Text("will be ") }
            Text("officially supported")
            Span({ shownIf(state < 4, fontGrow) }) { Text(".") }
            Span({ shownIf(state >= 4, fontGrow) }) { Text("!") }
        }
        Div({
            css {
                position(Position.Relative)
                width(2.em)
                height(1.em)
                textAlign("center")
                fontSize(4.em)
            }
        }) {
            @Composable
            fun abs(text: String, attrs: AttrBuilderContext<HTMLDivElement>) {
                Div({
                    css {
                        position(Position.Absolute)
                        top(0.px)
                        left(0.px)
                        width(100.percent)
                        textAlign("center")
                    }
                    attrs.invoke(this)
                }) {
                    Text(text)
                }
            }
            abs(Emoji.headstone) { shownIf(state == 1, stamp) }
            abs(Emoji.cry) { shownIf(state in 2..3, stamp) }
            abs(Emoji.grin) { shownIf(state >= 4, stamp) }
        }
        JetBrains({
            shownIf(state in 3..4, stamp)
        }) {
            Text("We will start working on")
            Br()
            Span({
                css {
                    fontSize(1.2.em)
                    fontWeight(700)
                }
            }) {
                Text("SPM support")
            }
            Br()
            Br()
            Span({
                css { fontSize(0.75.em) }
                shownIf(state >= 4, Transitions.fade)
            }) {
                Text("once we've stabilized the Gradle plugin API.")
            }
        }
    },
    Slide(
        name = "xcode-spm",
        stateCount = 6
    ) { state ->
        SubSlide(state in listOf(0, 1, 4, 5), fade) {
            @Composable
            fun Img(file: String, top: CSSSizeValue<*>, bottom: CSSSizeValue<*>, attrs: AttrBuilderContext<HTMLImageElement>? = null) {
                Img(src = "img/$file") {
                    css {
                        width(8.em)
                        height(8.em)
                        property("object-fit", "contain")
                        position(Position.Absolute)
                        property("top", 50.percent + top)
                        property("left", 50.percent + bottom)
                    }
                    attrs?.invoke(this)
                }

            }

            Img("XCode.png", 1.em, (-4).em)
            Img("arrows.svg", (-1).em, (-12).em) { shownIf(state >= 1, Transitions.Fade(0.6.seconds)) }
            Img("Git.png", (-9.5).em, (-15).em) { shownIf(state >= 1, stamp) }
            Img("arrows.svg", (-1).em, 4.em) {
                shownIf(state >= 5, Transitions.Fade(0.6.seconds))
                css { transform { rotate((-90).deg) } }
            }
            Img("storage.svg", (-9.5).em, 7.em) { shownIf(state >= 5, stamp) }
        }
        SubSlide(state in listOf(2, 3), fade) {
            SourceCode("swift", """
                let package = Package(
                    name: "MyAmazingSharedLib",
                    platforms: [ .iOS(.v12), .watchOS(.v9) ],
                    products: [ .library(
                        name: "MyAmazingSharedLib",
                        targets: ["MyAmazingSharedLib"]
                    ), ],
                    targets: [
                        .«f:binary»«s:t»«f:T»arget(
                            name: "MyAmazingSharedLib",
                «l:            url: "https://bin.kompa.ny/framework.zip",
                            checksum: "6d988a1a27418674b4d7c31732f6d..."
                »        ),
                    ]
                )
            """.trimIndent()) {
                "s" { fontGrow(state < 3) }
                "f" { fontGrow(state >= 3) }
                "l" { lineHeight(state >= 3) }
            }
        }
    },

    Slide(
        name = "spm-needs",
        stateCount = 4
    ) { state ->
        H2 { Text("SPM means:") }
        Ul({ css { fontSize(1.5.em) } }) {
            Li({ shownIf(state >= 1, fade) }) {
                Span({ hiddenIf(state == 3, dimmed) }) { Text("Infrastructure:") }
                Ul {
                    Li({ hiddenIf(state == 3, dimmed) }) { Text("A Git repository for the package") }
                    Li({ hiddenIf(state == 3, dimmed) }) { Text("A file backend for the XCFramework") }
                }
            }
            Li({ shownIf(state >= 2, fade) }) {
                Span { Text("Logic:") }
                Ul {
                    Li({ hiddenIf(state == 3, dimmed) }) { Text("A way to compile an XCFramework") }
                    Li { Text("A way to upload that XCFramework") }
                    Li { Text("A way to generate a Package.swift") }
                    Li { Text("A way to commit & push that Package.swift") }
                }
            }
        }
    },
    Slide(
        name = "kmm-bridge",
        stateCount = 2
    ) { state ->
        H1({ css { color(KodeinColor.korail.css) } }) { Text("KMM-Bridge") }
        P({
            shownIf(state >= 1, fade)
            css { fontSize(1.5.em) }
        }) {
            Text("A Gradle plugin by Touchlab, companion to Kotlin/Multiplatform, that manages SPM deployment.")
        }
    }

).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
