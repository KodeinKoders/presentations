package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.*
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.css.CSSMarginRule
import kotlin.time.Duration


val modulesAndExtensions = listOf(
    Slide(
        name = "modules-vs-extensions"
    ) {
        H3 { Text("Modularization vs Composition") }
    },
    Slide(
        name = "modules-usecase"
    ) {
        H2 { Text("A use case") }
        SourceCode(
            lang = "kotlin",
            code = """
                class AppConfiguration
                
                class FreeUserRepository(
                    val config: AppConfiguration
                ) : UserRepository
                
                class GetUserUseCase(
                    val repository: UserRepository, 
                    val conf: AppConfiguration
                )
            """.trimIndent(),
        )
    },
    Slide(
        name = "modules",
        stateCount = 4
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «modules:
                val configurationModule = DI.Module("Configuration") {
                «bind-in:    bindInstance { applicationConfiguration }
                »}
            
                val repositoryModule = DI.Module("Repository") {
                «bind-in:    bindSingleton<UserRepository> { FreeUserRepository(instance()) }
                »}
            
                val useCaseModule = DI.Module("UseCase") {
                «bind-in:    bindSingleton {  GetUserUseCase(instance(), instance()) }
                »}
    
                »val appContainer = DI {
                «bind-out:    bindInstance { applicationConfiguration }
                    bindSingleton<UserRepository> {  FreeUserRepository(instance()) }
                    bindSingleton {  GetUserUseCase(instance(), instance()) }
                »«imp-all:   importAll(configurationModule, repositoryModule, useCaseModule) 
                »}
            """.trimIndent(),
        ) {
            "modules" { lineHeight(state >= 1) }
            "bind-in" { lineHeight(state >= 2) }
            "bind-out" { lineHeight(state < 2) }
            "imp-all" { lineHeight(state >= 3) }
        }
    },
    Slide(
        name = "module-import-once",
        stateCount = 5
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                val configurationModule = DI.Module("Configuration") {} 

                val repositoryModule = DI.Module("Repository") {«conf-out:}»
                «imp-conf:    «import-zoom:import«once:Once»(configurationModule)»
                }»
                val useCaseModule = DI.Module("UseCase") {«conf-out:}»
                «imp-conf:    «import-zoom:import«once:Once»(configurationModule)»
                }»
                val appContainer = DI {
                   «import-zoom:importAll(«conf-out:configurationModule, »repositoryModule, useCaseModule)»
                }
            """.trimIndent(),
        ) {
            "imp-conf" { lineHeight(state >= 1) }
            "conf-out" { fontGrow(state < 1) }
            "import-zoom" { zoomed(state in 2..4) }
            "once" { fontGrow(state >= 4) }
        }
        Div({
            css {
                position(Position.Fixed)
                fontSize(2.em)
                top(5.4.em)
                left(12.em)
            }
            shownIf(state == 3, Transitions.stamp)
        }) {
            Text(Emoji.bomb)
        }
        Div({
            css {
                position(Position.Fixed)
                fontSize(2.em)
                top(5.4.em)
                left(12.em)
            }
            shownIf(state == 4, Transitions.stamp)
        }) {
            Text(Emoji.slightly_smiling_face)
        }
    },
    Slide(
        name = "modules-override",
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «test-out:val configurationModule = DI.Module("Configuration") {
                    bindInstance { productionConfiguration }
                }
                
                »val appContainer = DI { import(configurationModule) }
                
                «test-in:val testContainer = DI«silent:(allowSilentOverride = true)» {
                    import(configurationModule)    
                «bind:    bindInstance«override:(overrides = true)» { testConfiguration }
                »}
                »«result:
                assertEquals(
                    appContainer.instance<AppConfiguration>(), 
                    productionConfiguration
                )
                assertEquals(
                    testContainer.instance<AppConfiguration>(), 
                    testConfiguration
                )
                »
            """.trimIndent(),
        ) {
            "test-in" { lineHeight(state >= 1) }
            "test-out" { lineHeight(state < 1) }
            "bind" { lineHeight(state >= 2) }
            "override" { fontGrow(state in 3..4) }
            "result" { lineHeight(state >= 4) }
            "silent" { fontGrow(state >= 5) }
        }
    },
    Slide(
        name = "extension-basic",
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «main:val main = »DI {
                «main-hide:    bindSingleton<UserRepository> { 
                        FreeUserRepository(instance()) 
                    }
                »«main-com:    /* ... */
                »}
                «sub:
                val sub = DI {
                    extend(main)
                «override:    bindSingleton<UserRepository>(overrides = true) { 
                        PremiumUserRepository() 
                    }
                »}
                »«result:
                val mainRepo; UserRepository by main.instance()
                val subRepo; UserRepository by sub.instance()
                »«assert:assertEquals(mainRepo, subRepo) «true:// True!»«false:// False!»
                »
            """.trimIndent(),
        ) {
            "main" { fontGrow(state >= 1) }
            "sub" { lineHeight(state >= 1) }
            "result" { lineHeight(state >= 2) }
            "assert" { lineHeight(state in listOf(3, 5)) }
            "true" { fontGrow(state == 3) }
            "main-hide" { lineHeight(state < 4) }
            "main-com" { lineHeight(state >= 4) }
            "override" { lineHeight(state >= 4) }
            "false" { fontGrow(state == 5) }
        }
    },
    Slide(
        name = "extension-copy",
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
        «context:class UserService(val path: String)

        »val parent = DI {
            bindConstant("ENV") { "PROD" }
            «singleton:bindSingleton»«provider:bindProvider» { UserService(instance("ENV")) }
        }

        val child = DI {
        «ext:    extend(parent«non-cached:, copy = «non-cached-zoom:Copy.NonCached»») «non-cached:// Default behavior»
        »    bindConstant("ENV", overrides = true) { "TEST" }
        }
        
        «retrieve:val repository: UserService by sub.instance()
        »«non-cached-singleton:assertEquals(
        «zoom:   "PROD", 
            repository.env // repository is from 'parent'»
        )
        »«non-cached-provider:assertEquals(
        «zoom:    "TEST", 
            repository.env // repository is copied in 'child'»
        )
        »
        """.trimIndent(),
        ) {
            "context" { lineHeight(state < 3) }
            "singleton" {
                fontGrow(state in 0..4)
                zoomed(state == 4)
            }
            "ext" { lineHeight(state < 10) }
            "non-cached" { fontGrow(state in 1..7) }
            "cpy" { fontGrow(state >= 1) }
            "non-cached-zoom" { zoomed(state in 4..5) }
            "retrieve" { lineHeight(state >= 2) }
            "non-cached-singleton" { lineHeight(state in 3..4) }
            "zoom" { zoomed(state in 4..5) }
            "provider" {
                fontGrow(state >= 5)
                zoomed(state == 5)
            }
            "non-cached-provider" { lineHeight(state == 5) }
        }
    },
    Slide(
        name = "extension-copy-none",
        stateCount = 2
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
        val parent = DI {
            bindConstant("ENV") { "PROD" }
            bindProvider { UserService(instance("ENV")) }
        }

        val child = DI {
            extend(parent, copy = «zoom:Copy.None»)
            bindConstant("ENV", overrides = true) { "TEST" }
        }
        
        «retrieve:val repository: UserService by sub.instance()
        assertEquals(
        «zoom:    "PROD", 
            repository.env // repository is from 'parent'»
        )
        »
        """.trimIndent(),
        ) {
            "retrieve" { lineHeight(state >= 1) }
            "zoom" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "extension-copy-specific",
        stateCount = 2
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
        val parent = DI {
            bindConstant("ENV") { "PROD" }
            bindProvider { UserService(instance("ENV")) }
        }

        val child = DI {
            extend(
                parent, 
            «zoom:    copy = Copy {
                    copy the binding<UserRepository>()
                }»
            )
            bindConstant("ENV", overrides = true) { "TEST" }
        }
        
        «retrieve:val repository: UserService by sub.instance()
        assertEquals(
        «zoom:    "TEST", 
            repository.env // repository is copied in 'child'»
        )
        »
        """.trimIndent(),
        ) {
            "retrieve" { lineHeight(state >= 1) }
            "zoom" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "extension-copy-all",
        stateCount = 2
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
        val parent = DI {
            bindConstant("ENV") { "PROD" }
            bindProvider { UserService(instance("ENV")) }
        }

        val child = DI {
            extend(parent, «zoom:copy = Copy.All»)
            bindConstant("ENV", overrides = true) { "TEST" }
        }
        
        «retrieve:val repository: UserService by sub.instance()
        assertEquals(
        «zoom:    "TEST", 
            repository.env // repository is copied in 'child'»
        )
        »
        """.trimIndent(),
        ) {
            "retrieve" { lineHeight(state >= 1) }
            "zoom" { zoomed(state == 1) }
        }
    },
)
