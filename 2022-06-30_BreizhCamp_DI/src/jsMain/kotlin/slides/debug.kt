package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text

val debug = listOf(
    Slide(
        name = "debug",
    ) {
        H3 { Text("Demystifying common mistakes") }
    },
    Slide(
        name = "binding-not-found",
        stateCount = 4
    ) { state ->
        H4 { Text("Binding not found") }
        SourceCode(
            lang = "kotlin",
            code = """
            val di = DI {
            «full:    fullContainerTreeOnError = true
            »    importAll(«forgotten:internalModule, »businessModule)
            }«repo:
            
            val repository: UserRepository by di.instance()
            »«exception:
            /* DI.NotFoundException: 
                No binding found for bind<HttpClient> { ? { ? } }
            «full:    Registered in this DI container:
                    module Business {
                        bind<DB> ...
                        bind<UserRepository> ...
                    }
            »*/»
            """.trimIndent()
        ) {
            "forgotten" { fontGrow(state < 1) }
            "exception" { lineHeight(state >= 2) }
            "repo" { lineHeight(state < 3) } // TODO SALOMON
            "full" { lineHeight(state >= 3) }
        }
    },
    Slide(
        name = "binding-override",
        stateCount = 3
    ) { state ->
        H4 { Text("Overriding an existing binding") }
        SourceCode(
            lang = "kotlin",
            code = """
                «app:val appContainer: DI = DI {
                    bindSingleton<UserRepository> { 
                        FreeUserRepository(instance()) 
                    }
                }
                
                »val testContainer: DI = DI {
                    extend(appContainer)
                    bindSingleton<UserRepository>«override:(override = true)» { 
                        PremiumUserRepository(instance()) 
                    }
                }
    
                val repository: PremiumUserRepository by instance()
                «exception:
                /* DI.OverridingException: 
                        Binding bind<PremiumUserRepository>() with ? { ? }  
                             must not override an existing binding.
                */»
            """.trimIndent()
        ) {
            "exception" { lineHeight(state == 1) }
            "app" { lineHeight(state < 1) }
            "override" { fontGrow(state >= 2) }
        }
    },
    Slide(
        name = "binding-recursion",
        stateCount = 2
    ) { state ->
        H4 { Text("Binding recursion") }
        SourceCode(
            lang = "kotlin",
            code = """
            class A(val c: C)
            class B(val a: A)
            class C(val b: B)
            
            bindSingleton { A(instance()) }
            bindSingleton { B(instance()) }
            bindSingleton { C(instance()) }
            «exception:
            /* DI.DependencyLoopException: Dependency recursion:
                 bind<A>()
                ╔╩>bind<C>()
                ║  ╚>bind<B>()
                ║    ╚>bind<A>()
                ╚══════╝
            */»
            """.trimIndent()
        ) {
            "exception" { lineHeight(state == 1 ) }
        }
    },
)