package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.util.d
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val stage3_1 = listOf(
    Slide(
        name = "stage3_1-interop",
        stateCount = 3
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 3.1:")
            }
            Text(" iOS Swift only API")
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceAround)
                alignItems(AlignItems.Center)
                width(90.percent)
                paddingTop(2.em)
            }
        }) {

            Img(src = "img/kotlin.svg") {
                css { height(4.em) }
            }

            Img(src = "img/arrows.svg") {
                css { height(2.em) }
            }

            Img(src = "img/objective-c.svg") {
                css { height(4.em) }
            }

            Img(src = "img/arrows.svg") {
                css { height(2.em) }
                shownIf(state >= 2, fade)
            }

            Img(src = "img/swift.svg") {
                css { height(4.em) }
                shownIf(state >= 1, fade)
            }
        }

    },

    Slide(
        name = "stage3_1-swift",
        stateCount = 3
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 3.1:")
            }
            Text(" Swift-ObjC Bridge")
        }

        SourceCode(
            lang = "swift",
            code = """
                «interop:@objc public class» SwiftChachaPoly «interop:: NSObject» {

                    «interop:@objc public» class «func:func» encrypt(
                        key: Data, nonce: Data,
                        aad: Data?, plaintext: Data
                    ) «return:-> DataResult» «block:{}»

                    «interop:@objc public» class «func:func» decrypt(
                        key: Data, nonce: Data,
                        authenticatedData: Data, ciphertext: Data
                    ) «return:-> DataResult» «block:{}»
                    
                }
            """.trimIndent()
        ) {
            "block" { fontGrow(false) }
            "interop" { zoomed(state == 1) }
            "func" { zoomed(state in 1..2) }
            "return" { zoomed(state == 2) }
        }
    },

    Slide(
        name = "stage3_1-xcode",
        stateCount = 5
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 3.1:")
            }
            Text(" use XCode")
        }

        Ul({
            css {
                fontSize(1.5.em)

                d("li") {
                    paddingBottom(0.6.em)
                }
            }
        }) {
            Li({ shownIf(state >= 1, fade) }) {
                Text("Create ")
                B { Text("Swift static library") }
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("Write ")
                B { Text("Swift / Obj-C facade") }
            }
            Li({ shownIf(state >= 3, fade) }) {
                Text("Configure ")
                B { Text("bridging header") }
            }
            Li({ shownIf(state >= 4, fade) }) {
                Text("Add bridging header ")
                B { Text("export logic") }
            }
        }
    },

    Slide(
        name = "stage3_1-embed",
        stateCount = 6
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 3.1:")
            }
            Text(" embed in KMP library")
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                alignItems(AlignItems.Center)
            }
        }) {
            Ul({
                css {
                    fontSize(1.5.em)

                    d("li") {
                        paddingBottom(0.6.em)
                    }
                }
            }) {
                Li({ shownIf(state >= 1, fade) }) {
                    Text("Write ")
                    B { Text("C-Interop definition file") }
                }
                Li({ shownIf(state >= 2, fade) }) {
                    Text("Add ")
                    B { Text("Gradle compilation task") }
                }
                Li({ shownIf(state >= 3, fade) }) {
                    Text("Configure ")
                    B { Text("Gradle KMP C-Interop") }
                }
                Li({ shownIf(state >= 4, fade) }) {
                    Text("Write ")
                    B { Text("Kotlin facade") }
                }
            }

            Div({
                css {
                    fontSize(4.em)
                    padding(0.4.em)
                }
                shownIf(state >= 5, grow)
            }) {
                Text(Emoji.wink)
            }
        }
    },

    Slide(
        name = "stage3_1-code",
        stateCount = 2
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 3.1:")
            }
            Br()
            Text("Use Swift-only API")
        }

        H1({ shownIf(state >= 1, grow) }) { Text(Emoji.stars) }

    }
)
