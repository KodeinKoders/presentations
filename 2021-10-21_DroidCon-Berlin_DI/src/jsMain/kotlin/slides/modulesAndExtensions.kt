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
        name = "modules-and-extensions"
    ) {
        H3 { Text("Modularization and Composition") }
    },
    Slide(
        name = "modules-usecase"
    ) {
        H2 { Text("A use case") }
        SourceCode(
            lang = "kotlin",
            code = """
                class AppParameters
                
                class AppHttpClient(
                    val parameters: AppParameters
                ) : HttpClient
                
                class UserDB(
                    val parameters: AppParameters
                ) : DB
                
                class UserRepository(
                    val db: UserDB, 
                    val httpClient: HttpClient
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
                val internalModule = DI.Module("Internal") {
                «bind-in:    bindInstance { applicationParameters }
                    bindProvider<HttpClient> { AppHttpClient(instance()) } 
                »}
            
                val businessModule = DI.Module("Business") {
                «bind-in:    bindSingleton<DB> {  UserDB(instance()) }
                    bindSingleton { UserRepository(instance(), instance()) }
                »}
    
                »val appContainer = DI {
                «bind-out:    bindInstance { applicationParameters }
                    bindProvider<HttpClient> { AppHttpClient(instance()) }
                    bindSingleton<DB> {  UserDB(instance()) }
                    bindSingleton { UserRepository(instance(), instance()) }
                »«imp-all:   importAll(appModule, businessModule) 
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
                val accountModule = DI.Module("Account") {«conf-out:}»
                «imp-conf:    «import-zoom:import«once:Once»(internalModule)»
                }»
                val checkoutModule = DI.Module("Checkout") {«conf-out:}»
                «imp-conf:    «import-zoom:import«once:Once»(internalModule)»
                }»
                val appContainer = DI {
                   «import-zoom:importAll(«conf-out:internalModule, »accountModule, checkoutModule)»
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
                top(4.8.em)
                right(7.em)
            }
            shownIf(state == 3, Transitions.stamp)
        }) {
            Text(Emoji.x)
        }
        Div({
            css {
                position(Position.Fixed)
                fontSize(2.em)
                top(4.8.em)
                right(5.em)
            }
            shownIf(state == 4, Transitions.stamp)
        }) {
            Text(Emoji.white_check_mark)
        }
    },
    Slide(
        name = "modules-override",
        stateCount = 7
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «containers:«test-out:val internalModule = DI.Module("Internal") {
                    bindInstance { productionParameters }
                }
                
                »val appContainer = DI { import(internalModule) }
                
                «test-in:val testContainer = DI«silent:(allowSilentOverride = true)» {
                    import(internalModule)    
                «bind:    bindInstance«override:(overrides = true)» { testParameters }
                »}
                »»«result:
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
            "override" { fontGrow(state in 3..5) }
            "containers" { lineHeight(state != 4) }
            "result" {
                lineHeight(state >= 4)
                zoomed(state == 4)
            }
            "silent" { fontGrow(state >= 6) }
        }
    },
    Slide(
        name = "modules-vs-extensions",
        stateCount = 2,
    ) { state ->
        H3 {
            Text("A module ")
            B { Text("declares") }
            Text(" bindings")
        }
        H4({ shownIf(state >= 1, Transitions.fade) }) {
            Text("while")
        }
        H3({ shownIf(state >= 1, Transitions.Fade(Duration.Companion.seconds(1))) }) {
            Text("An extension ")
            B { Text("creates") }
            Text(" a new DI container")
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
                val mainRepo: UserRepository by main.instance()
                val subRepo: UserRepository by sub.instance()
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
        stateCount = 4
    ) { state ->
        H3 { Text("Copy strategies") }
        SourceCode(
            lang = "kotlin",
            code = """
        val child = DI {
            extend(parent«copy:, copy = Copy.NonCached») «copy:// Default behavior»
            /* «dot:...»«values:None / All / Custom» */
        }
        """.trimIndent(),
        ) {
            "copy" { fontGrow(state >= 1) }
            "dot" { fontGrow(state < 2) }
            "values" { fontGrow(state >= 2) }
        }
        P({
            shownIf(state >= 2, Transitions.fade)
        }) {
            Text("bindings that are ")
            B { Text("copied") }
            Text(" represent ")
            B { Text("new instances") }
        }
        P({
            shownIf(state >= 3, Transitions.fade)
        }) {
            Text("bindings that are ")
            B { Text("NOT copied") }
            Text(" represent ")
            B { Text("references") }
            Text(" to the parent's instances")
        }
    }
)
