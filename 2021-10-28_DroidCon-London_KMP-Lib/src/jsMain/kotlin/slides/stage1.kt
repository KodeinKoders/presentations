package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.*


val stage1 = listOf(
    Slide(
        name = "stage1-platform",
        stateCount = 3
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 1:")
            }
            Text(" expect platform implems")
        }

        SourceCode(
            lang = "kotlin",
            code = """
                «common:// Common
                public expect fun sha256(): Sha256
    
                »«platform:// Android
                public actual fun sha256(): Sha256 = Sha256Android()
    
                // iOS
                public actual fun sha256(): Sha256 = Sha256Ios()
                »
            """.trimIndent()
        ) {
            "common" { lineHeight(state >= 1) }
            "platform" { lineHeight(state >= 2) }
        }
    },

    Slide(
        name = "stage1-ios-platform",
        stateCount = 9
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 1:")
            }
            Text(" iOS Platform APIs")
        }

        SourceCode(
            lang = "kotlin",
            code = """
                «memScoped:memScoped {
                »«alloc:«indent:    »val ctx = «nativeHeap:nativeHeap.»alloc<CC_SHA256_CTX>()
                »«init:«indent:    »CC_SHA256_Init(ctx.ptr)
                »«update:
                «indent:    »CC_SHA256_Update(ctx.ptr, «refTo:input.refTo(0)», len)
                »«final:
                «indent:    »output.asUByteArray().«pinned:usePinned» {
                    «indent:    »CC_SHA256_Final(«pinned:it.addressOf(0)», ctx.ptr)
                «indent:    »}
                »«free:
                «indent:    »nativeHeap.free(c)
                »«memScoped:}
                »
            """.trimIndent()
        ) {
            "alloc" { lineHeight(state >= 1) }
            "init" { lineHeight(state >= 2) }
            "final" { lineHeight(state >= 3) }
            "pinned" { zoomed(state == 4) }
            "update" { lineHeight(state >= 5) }
            "refTo" { zoomed(state == 6) }
            "free" { lineHeight(state == 7) }
            "memScoped" { lineHeight(state >= 8) }
            "indent" { fontGrow(state >= 8) }
            "nativeHeap" { fontGrow(state < 8) }
        }
    },

    Slide(
        name = "stage1-code",
        stateCount = 2
    ) { state ->
        H2 {
            Span({
                css {
                    fontWeight(200)
                }
            }) {
                Text("Stage 1:")
            }
            Br()
            Text("Use Platform APIs")
        }

        H1({ shownIf(state >= 1, Transitions.grow) }) { Text(Emoji.stars) }

    }
)