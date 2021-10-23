package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.util.d
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val example = Slide(
    name = "example",
    stateCount = 5
) { state ->
    H3 {
        Text("An example: Cryptography")
    }

    Table({
        css {
            fontSize(1.5.em)
            d("td") { padding(0.5.em, 0.4.em) }
            d("th") { padding(0.5.em, 0.4.em) }
            d("th.lib") { textAlign("right") }
            d("td.type") {
                fontWeight(200)
            }
        }
    }) {
        Tr {
            Td()
            Td()
            Th({ shownIf(state >= 1, fade) }) {
                Img(src = "/img/logo-android.svg") {
                    style { height(2.0.em) }
                }
                Br()
                Text("Android")
            }
            Th({ shownIf(state >= 1, fade) }) {
                Img(src = "/img/logo-ios.svg") {
                    style { height(2.0.em) }
                }
                Br()
                Text("iOS")
            }
        }
        Tr {
            Th({ classes("lib") ; shownIf(state >= 1, fade) }) { Text("Sha256") }
            Td({ classes("type") ; shownIf(state >= 2, fade) }) { Text("Platform") }
            Td({ shownIf(state >= 2, fade) }) { Text("Kotlin") }
            Td({ shownIf(state >= 2, fade) }) { Text("Obj-C") }
        }
        Tr {
            Th({ classes("lib") ; shownIf(state >= 1, fade) }) { Text("Secp256k1") }
            Td({ classes("type") ; shownIf(state >= 3, fade) }) { Text("Native") }
            Td({ shownIf(state >= 3, fade) }) { Text("JNI") }
            Td({ shownIf(state >= 3, fade) }) { Text("C-Interop") }
        }
        Tr {
            Th({ classes("lib") ; shownIf(state >= 1, fade) }) { Text("Chacha20-Poly1305") }
            Td({ classes("type") ; shownIf(state >= 4, fade) }) { Text("Platform") }
            Td({ shownIf(state >= 4, fade) }) { Text("Kotlin") }
            Td({ shownIf(state >= 4, fade) }) { Text("Swift!") }
        }
    }
}
