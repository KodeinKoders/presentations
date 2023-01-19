package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.animatedWith
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.cic.css
import utils.Stamp

val problem = listOf(
    Slide(
        name = "problem:declaration",
        stateCount = 5
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
            «dots:...
            
            »«datas:val ds1 = NetworkDataSource()»«ucs:
            val uc1 = UseCase1(«ds-in:ds1»«ds-out: ??? »)»«datas:
            val ds2 = LocalDataSource()»«ucs:
            val uc2 = UseCase2(«ds-in:ds2»«ds-out: ??? »)
            val uc3 = UseCase3(«ds-in:ds1, ds2»«ds-out: ??? »)
            
            »val viewModel = WhateverViewModel(«vm-in:uc1, uc2, uc3»«vm-out: ??? »)«dots:
            ...»
            """.trimIndent()
        ) {
            "vm-out" { fontGrow(state == 0) }
            "vm-in" { fontGrow(state > 0) }
            "ucs" { lineHeight(state > 1) }
            "datas" { lineHeight(state > 2) }
            "ds-out" { fontGrow(state <= 2) }
            "ds-in" { fontGrow(state > 2) }
            "dots" { lineHeight(state > 3) }
        }
    },
    Slide(
        name = "problem:di",
        stateCount = 9
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
            val di = DI {«di-out: ... }»«di-in:
                bindSingleton { NetworkDataSource() }
                bindSingleton { UseCase(«what: ??? »«di-arg:instance()») }
                bindSingleton { WhateverViewModel(«what: ??? »«di-arg:instance()») }
            }
            »«retrieve:
            «get:// get() | inject() | ¯\_(ツ)_/¯
            »val ds: NetworkDataSource by di.instance()
            «ann:// @Inject | @Provide
            »val uc: UseCase by di.instance()
            «wtf:// Singleton? | Factory?
            »val viewModel: WhateverViewModel by di.instance(«arg:arg = ???»)
            »""".trimIndent()
        ) {
            "retrieve" { lineHeight(state > 0) }
            "get" { lineHeight(state > 1) }
            "ann" { lineHeight(state > 2) }
            "wtf" { lineHeight(state > 3) }
            "arg" { fontGrow(state > 3) }
            "di-out" { fontGrow(state <= 5) }
            "di-in" { lineHeight(state > 5) }
            "what" { fontGrow(state <= 6) }
            "di-arg" { fontGrow(state > 6) }
        }
        Stamp(state == 5) { Text("Misleading API") }
        Stamp(state == 8) { Text("No compile-time check") }
    },
    Slide(name = "problem:monster") {
        H1 { Text("Did we just create a monster?") }
        H1 { Text((Emoji.see_no_evil)) }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
