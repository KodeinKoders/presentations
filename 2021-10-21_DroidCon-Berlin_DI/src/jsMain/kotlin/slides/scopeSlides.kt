package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.hiddenIf
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val scopeSlides = listOf(
    Slide(
        name = "scopes",
        stateCount = 3
    ) { state ->
        H3 {
            Text("A scope defines the lifetime of a singleton (or multiton).")
        }
        P({
            shownIf(state >= 1, fade)
        }) {
            Text("User session. Screen controller. Etc.")
        }
        Ul({
            css {
                listStyleType("none")
                padding(0.em)
            }
            shownIf(state >= 2, fade)
        }) {
            Li {
                B { Text("Android") }
                Text(": Activity (retained), LifecycleOwner.")
            }
            Li {
                B { Text("KTor") }
                Text(": Session.")
            }
            Li {
                B { Text("TornadoFx") }
                Text(": Component, node.")
            }
            Li {
                B { Text("Compose") }
                Text(": Work In Progress!")
            }
        }
    },

    Slide(
        name = "scope-creation",
        stateCount = 7
    ) { state ->
        SourceCode(
            lang = "Kotlin",
            code = """
                object SessionScope«colon: : »«weak:WeakContextScope<Session>()»«strong:Scope<Session> {»
                «map:    private val registries = HashMap<Any, ScopeRegistry>()
                »«fun:
                    override fun getRegistry(context: Session): ScopeRegistry =
                «get:        registries.getOrPut(context.id) {
                            val registry = StandardScopeRegistry()
                «close:            session.onClose {
                                registries.remove(context.id)
                                registry.clear()
                            }
                »            registry
                        }
                »»«strong:}»
            """.trimIndent()
        ) {
            "weak" { fontGrow(state == 1) }
            "strong" { fontGrow(state >= 2) }
            "map" { lineHeight(state >= 3) }
            "fun" { lineHeight(state >= 4) }
            "get" { lineHeight(state >= 5) }
            "close" { lineHeight(state >= 6) }
        }
    }
)