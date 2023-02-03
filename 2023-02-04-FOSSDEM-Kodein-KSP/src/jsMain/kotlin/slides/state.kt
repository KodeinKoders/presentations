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
import net.kodein.theme.compose.pres.KodeinAttrs
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import utils.Stamp

val state = listOf(
    Slide(
        name = "is-it-live",
    ) { H2 { Text("Is this live?") } },

    Slide(
        name = "soon",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 5
    ) { state ->
        H2 {
            Text("Soon!")
        }
        H3({ shownIf(state > 0, Transitions.fontGrow) }) {
            Ul {
                Li({ shownIf(state > 0, Transitions.fade) }) { Text("using Tags") }
                Li({ shownIf(state > 1, Transitions.fade) }) { Text("managing modules") }
                Li({ shownIf(state > 2, Transitions.fade) }) { Text("handling/declaring scopes") }
                Li({ shownIf(state > 3, Transitions.fade) }) { Text("...") }
            }
        }
    },
    Slide(
        name = "preview-tags",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 3
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            val oldWay: String = di.instance(tag = "breweries-api")
           «tag:
            // ------------------------------------------------ //
            
            @Resolved
            interface AppDependencies {
                @Tag("breweries-api")
                fun breweriesPath(): String
            }
            »«new:
            val newWay = deps.breweriesPath()
            »
            """.trimIndent()
        ) {
            "tag" { lineHeight(state > 0) }
            "new" { lineHeight(state > 1) }
        }
    },
    Slide(
        name = "preview-modules",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 3
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            «mod2:@ResolvedModule(name = "my-module")»«mod1:@Resolved(module = "my-module")»
            interface AppDependencies {
                fun breweriesController(): BreweriesController
            }
            """.trimIndent()
        ) {
            "mod1" { fontGrow(state == 0) }
            "mod2" { fontGrow(state > 0) }
        }

        Stamp(state == 2) { Img(src = "img/troll.png") }
    },
    Slide(
        name = "preview-scope",
        config = { KodeinAttrs(workInProgress = true) },
        stateCount = 4
    ) { state ->
        SourceCode(
            lang = "kotlin", code = """
            val ctrl: Controller = di.on<MyScope>.instance()
            «scope:
             // ----------------------------------------- //
            
            @Resolved(scope = MyScope::class) 
            interface ScopedAppDependencies {
                fun breweriesController(): BreweriesController
            }
            »«new:
            val newCtrl = with(MyScopeInstance) {
                deps.breweriesPath()
            }
            »
            """.trimIndent()
        ) {
            "scope" { lineHeight(state > 0) }
            "new" { lineHeight(state > 1) }
        }
        Stamp(state == 3) { Text("context receivers?") }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))