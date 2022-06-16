package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.*
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css

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
                "li" {
                    padding(0.3.em, 0.em)
                }
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
                Text(": Android & Multiplatform!")
            }
        }
    },

    Slide(
        name = "scope-creation",
        stateCount = 8
    ) { state ->
        SourceCode(
            lang = "Kotlin",
            code = """
                object SessionScope«colon: : »«weak:WeakContextScope<Session>()»«strong:Scope<Session> {»
                «map:    private val registries = HashMap<String, ScopeRegistry>()
                »«fun:
                    override fun getRegistry(context: Session): ScopeRegistry =
                «get:        registries.getOrPut(context.id) {
                            val registry = StandardScopeRegistry()
                «close:            session.onClose {
                                registries.remove(context.id)
                                «clear:registry.clear()»
                            }
                »            registry
                        }
                »»«strong:}»
            """.trimIndent()
        ) {
            "weak" { fontGrow(state == 1) }
            "colon" { fontGrow(state >= 1) }
            "strong" { fontGrow(state >= 2) }
            "map" { lineHeight(state >= 3) }
            "fun" { lineHeight(state >= 4) }
            "get" { lineHeight(state >= 5) }
            "close" { lineHeight(state >= 6) }
            "clear" { zoomed(state == 7) }
        }
    },

    Slide(
        name = "scoped-binding",
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                val di = DI {
                
                «ls:    bindSingleton { UserApi() }
                »«lc:    bind {
                «b:        scoped(SessionScope)
                »        «d:    .»singleton { UserApi(«c:context.id») }
                    }
                »    
                «ct:    registerContextTranslator<Request, Session> {
                        it.getSession()
                    }
                »}
            """.trimIndent()
        ) {
            "ls" { lineHeight(state < 1) }
            "lc" { lineHeight(state >= 1) }
            "b" { lineHeight(state >= 2) }
            "d" { fontGrow(state >= 2) }
            "c" { fontGrow(state >= 3) }
            "ct" { lineHeight(state >= 4) }
        }

        H4({
            style {
                fontFamily("JetBrains Mono")
                color(KodeinColor.kamethiste.css)
                letterSpacing(0.em)
            }
            shownIf(state >= 5, grow)
        }) {
            Text("REQUEST --> SESSION")
        }
    },

    Slide(
        name = "scoped-retrieval",
        stateCount = 3
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                class Container( «def:// <-- DEFAULT CONTEXT»
                    val req: Request
                ) : DIAware {
                
                    val api: UserApi by «on:on(context = req).»instance()
                
                }
            """.trimIndent()
        ) {
            "def" { fontGrow(state >= 1) }
            "on" { fontGrow(state >= 2) }
        }
    },

    Slide(
        name = "scope-closeable",
        stateCount = 3
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «com:// Only works on cleared scope registry!»
                class UserApi(val id: String)«sc: : ScopeCloseable» {
                
                    val log = openLogFile("user.${"$"}id.txt")
                
                «cl:    override fun close() {
                        log.close()
                    }
                
                »}
            """.trimIndent()
        ) {
            "sc" { fontGrow(state >= 1) }
            "cl" { lineHeight(state >= 1) }
            "com" { fontGrow(state >= 2) }
        }
    }

)