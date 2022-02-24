package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.grow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.theme.KodeinFont
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.dom.*


val coroutines = listOf(
    Slide(
        name = "coroutines-launch",
        stateCount = 2
    ) { state ->
        H2 { Text("KotlinX Coroutines") }

        SourceCode(
            lang = "kotlin",
            """
                val client = HttpClient()
                
                «ll:MainScope().launch {
                »«lc:    »val user: User = client.get("${'$'}API_URL/user/42")
                «lc:    »displayUser(user)
                «ll:}
                »
            """.trimIndent()
        ) {
            "ll" { lineHeight(state >= 1) }
            "lc" { fontGrow(state >= 1) }
        }
    },

    Slide(
        name = "coroutines-parallelism"
    ) {
        H2 { Text("Coroutines != Multi-thread") }

        Img(src = "img/event-loop.png")
    },

    Slide(
        name = "coroutines-boom",
        stateCount = 3
    ) { state ->
        H3 {
            Text("Coroutines + Kotlin/Native")
            Br()
            Text("+ Multi-Thread = ${Emoji.bomb}")
            Br()
            Small({
                shownIf(state >= 1, grow)
                css {
                    fontSize(0.6.em)
                    fontFamily(KodeinFont.main.name)
                }
            }) {
                Text("At the moment (December 2021)!")
            }
        }

        Br()

        H3({
            shownIf(state >= 2, grow)
        }) {
            Text("Coroutines + Kotlin/Native")
            Br()
            Text("+ iOS Event-Loop = ${Emoji.smile}")
        }
    }

)
