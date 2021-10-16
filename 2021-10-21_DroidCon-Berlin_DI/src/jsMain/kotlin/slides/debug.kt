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
        stateCount = 3
    ) { state ->
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
            »    importAll(configModule, «forgotten:repositoryModule, »useCaseModule)
            }
            
            val useCase: GetUserUseCase by di.instance()
            «exception:
            /* DI.NotFoundException: 
                No binding found for bind<UserRepository> { ? { ? } }
            «full:    Registered in this DI container:
                    module Configuration {
                        bind<AppConfiguration> ...
                    }
                    module UseCase {
                        bind<GetFreeUserUseCase> ...
                    }
            »*/»
            """.trimIndent()
        ) {
            "forgotten" { fontGrow(state < 1) }
            "exception" { lineHeight(state >= 2) }
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
            "exception" { lineHeight(state >= 1) }
            "app" { lineHeight(state < 1) }
            "override" { fontGrow(state >= 2) }
        }
    },
    Slide(
        name = "binding-recursion",
        stateCount = 4
    ) { state ->
        H4 { Text("Binding recursion") }
        SourceCode(
            lang = "kotlin",
            code = """
            // Use case
            class A(val c: C)
            class B(val a: A)
            class C(val b: B)
            
            // Binding
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
            "exception" { lineHeight(state ==1 ) }
        }
    },
)