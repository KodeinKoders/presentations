package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css


val stage2_0 = listOf(
    Slide(
        name = "stage2_0-c-lib",
        stateCount = 4
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 2.0:")
            }
            Text(" Compile a C library")
        }

        Ul({
            css {
                fontSize(1.5.em)

                "li" {
                    paddingBottom(0.6.em)

                    "span" {
                        fontWeight(200)
                    }
                }
            }
        }) {
            Li({ shownIf(state >= 1, fade) }) {
                Text("For ")
                B { Text("Android") }
                Text(": ")
                Span { Text("armeabi-v7a, arm64-v8a, x86, x86_64") }
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("For ")
                B { Text("iOS") }
                Text(": ")
                Span { Text("arm64, x86_64") }
                Span({ shownIf(state >= 3, fade) }) { Text(", simulatorArm64") }
            }
        }
    },

    Slide(
        name = "stage2_0-gradle",
        stateCount = 4
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 2.0:")
            }
            Text(" integrate with Gradle")
        }

        SourceCode(
            lang = "kotlin",
            code = """
                val buildSecp256k1Ios by tasks.creating(Exec::class) {
                    commandLine(bash, "build-ios.sh")
                «inout:
                    «em:inputs».files("${'$'}projectDir/build-ios.sh")
                    «em:inputs».files(fileTree("${'$'}projectDir/secp256k1/src") {
                        include("*.c", "*.h")
                        exclude("*-config.h")
                    })
                    «em:outputs».dir("${'$'}projectDir/build/ios")
                »}
            """.trimIndent()
        ) {
            "inout" { lineHeight(state >= 1) }
            "em" { zoomed(state == 2) }
        }
    },

    Slide(
        name = "stage2_0-code",
        stateCount = 2
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 2.0:")
            }
            Br()
            Text("set up native compilation")
        }

        H1({ shownIf(state >= 1, grow) }) { Text(Emoji.stars) }

    }
)