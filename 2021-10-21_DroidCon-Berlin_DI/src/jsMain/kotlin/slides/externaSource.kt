package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

val externaSource = Slide(
    name = "exrternal-source",
    stateCount = 5
) { state ->
    H3 { Text("External Sources")}
    Div {
        SourceCode(
            lang = "kotlin",
            code = """
                «es:externalSources += ExternalSource { «em:key ->»
                    val getter = otherSource.binding(key)
                    if (getter != null) «em:externalFactory { getter.get() }»
                    else null
                }
                »
            """.trimIndent()
        ) {
            "es" { lineHeight(state >= 1) }
            "em" { zoomed(state >= 2) }
        }
    }
    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
        }
        shownIf(state >= 3, fontGrow)
    }) {
        SourceCode(
            lang = "kotlin",
            code = """
                public data class Key(
                    val contextType: TypeToken,
                    val argType: TypeToken,
                    val type: TypeToken,
                    val tag: Any?
                )
            """.trimIndent()
        )
        P({
            css {
                fontSize(5.em)
                padding(0.25.em, 0.75.em, 0.25.em, 0.75.em)
                margin(0.em)
            }
            shownIf(state >= 4, fontGrow)
        }) {
            Text(Emoji.shrug)
        }
    }
}