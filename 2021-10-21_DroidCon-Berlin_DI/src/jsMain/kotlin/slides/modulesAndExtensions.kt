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


val modulesAndExtensionsSlides = listOf(
    Slide(
        name = "modules-vs-extensions"
    ) {
        H3 { Text("Modularization vs Composition") }
    },
    Slide(
        name = "modules-context"
    ) {
        H2 { Text("Some context") }
        SourceCode(
            lang = "kotlin",
            code = """
                class AppConfiguration {
                    companion object {
                        val applicationConfiguration = AppConfiguration()
                    }
                }
                class PersonDataSource(val config: AppConfiguration)
                class GetPersonUseCase(val dataSource: PersonDataSource)
                class UpdatePersonUseCase(
                    val dataSource: PersonDataSource, 
                    val conf: AppConfiguration
                )
            """.trimIndent(),
        )
    },
    Slide(
        name = "modules",
        stateCount = 9
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                «modules:
                val configurationModule = DI.Module("Configuration") {
                «bind-in:    bindInstance { applicationConfiguration }
                »}
            
                val dataSourceModule = DI.Module("DataSource") {
                «imp-conf:    «import-zoom:import«once:Once»(configurationModule)»
                »«bind-in:    bindSingleton<PersonDataSource> { PersonDataSource(instance()) }
                »}
            
                val useCaseModule = DI.Module("UseCase") {
                «imp-conf:    «import-zoom:import«once:Once»(configurationModule)»
                »«bind-in:    bindSingleton {  GetPersonUseCase(instance()) }
                    bindSingleton {  UpdatePersonUseCase(instance(), instance()) }
                »}
    
                »val appContainer = DI {
                «bind-out:    bindInstance { applicationConfiguration }
                    bindSingleton {  PersonDataSource(instance()) }
                    bindSingleton {  GetPersonUseCase(instance()) }
                    bindSingleton {  UpdatePersonUseCase(instance(), instance()) }
                »«imp-all:   importAll(«conf-out:configurationModule, »dataSourceModule, useCaseModule) 
                »}
            """.trimIndent(),
        ) {
            "modules" { lineHeight(state >= 1) }
            "bind-in" { lineHeight(state >= 2) }
            "bind-out" { lineHeight(state < 2) }
            "imp-all" { lineHeight(state >= 3) }
            "imp-conf" { lineHeight(state >= 4) }
            "conf-out" { fontGrow(state < 4) }
            "import-zoom" { zoomed(state in 5..7) }
            "once" { fontGrow(state >= 7) }
        }
        Div({
            css {
                position(Position.Fixed)
                fontSize(2.em)
                top(5.7.em)
                left(12.em)
            }
            shownIf(state == 6, Transitions.stamp)
        }) {
            Text(Emoji.bomb)
        }
        Div({
            css {
                position(Position.Fixed)
                fontSize(2.em)
                top(5.7.em)
                left(12.em)
            }
            shownIf(state == 7, Transitions.stamp)
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
                val configurationModule = DI.Module("Configuration") {
                    bindInstance { productionAppConfiguration }
                }
                
                val appContainer = DI {
                    import(configurationModule)
                }
                
                «test:val testContainer = DI«silent:(allowSilentOverride = true)» {
                    import(configurationModule)    
                «bind:    bindInstance«override:(overrides = true)» { testApplicationConfiguration }
                »}
                
                »«result:appContainer.instance<AppConfiguration>() == productionAppConfiguration   
                testContainer.instance<AppConfiguration>() == testApplicationConfiguration
                »
            """.trimIndent(),
        ) {
            "test" { lineHeight(state >= 1) }
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
                    bindSingleton<CandyFactory> { GummiesFactory() }
                }
                «sub:
                val sub = DI {
                    extend(main)
                «override:    bindSingleton<CandyFactory>(overrides = true) { ChocolateFactory() }
                »}
                »«result:
                val mainFactory; CandyFactory by main.instance()
                val subFactory; CandyFactory by sub.instance()
                »«assert:assertEquals(mainFactory, subFactory) «true:// True!»«false:// False!»
                »
            """.trimIndent(),
        ) {
            "main" { fontGrow(state >= 1) }
            "sub" { lineHeight(state >= 1) }
            "result" { lineHeight(state >= 2) }
            "assert" { lineHeight(state in listOf(3, 5)) }
            "true" { fontGrow(state == 3) }
            "override" { lineHeight(state >= 4) }
            "false" { fontGrow(state == 5) }
        }
    },
    Slide(
        name = "extension-copy",
        stateCount = 11
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
        «context:class CandyFactory(val env: String)

        »val main = DI {
            bindConstant("ENV") { "PROD" }
            «singleton:bindSingleton»«provider:bindProvider» { CandyFactory(instance("ENV")) }
        }

        val sub = DI {
        «ext:    extend(main«cpy:, copy = »«non-cached:«non-cached-zoom:Copy.NonCached»»«all:Copy.All»«none:Copy.None») «non-cached:// Default behavior»
        »«specific:    extend(main, copy = Copy {
                copy the binding<CandyFactory>()
            })
        »    bindConstant("ENV", overrides = true) { "TEST" }
        }
        
        «retrieve:val candyFactory by sub.instance<CandyFactory>()
        »«non-cached-single:
        assertNotEquals("TEST", subFactory.env) // candyFactory is from 'parent'
        »«non-cached-provider:
        assertEquals("TEST", subFactory.env) // candyFactory is copied in 'child'
        »«none-assert:
        assertNotEquals("TEST", subFactory.env) // candyFactory is from 'parent'
        »«all-assert:
        assertEquals("TEST", subFactory.env) // candyFactory is copied in 'child'
        »«specific-assert:
        assertEquals("TEST", subFactory.env) // candyFactory is copied in 'child'
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
            "non-cached-single" { lineHeight(state == 3) }
            "provider" {
                fontGrow(state >= 5)
                zoomed(state == 5)
            }
            "non-cached-provider" { lineHeight(state == 7) }
            "none" { fontGrow(state == 8) }
            "none-assert" { lineHeight(state == 8) }
            "all" { fontGrow(state == 9) }
            "all-assert" { lineHeight(state == 9) }
            "specific" { lineHeight(state >= 10) }
            "specific-assert" { lineHeight(state == 10) }
        }
    },

//    Slide(
//        name = "extension-copy",
//        stateCount = 10
//    ) { state ->
//        SourceCode(
//            lang = "kotlin",
//            code = """
//            «context:interface CandyFactory {
//                val appConfiguration: AppConfiguration
//            }
//
//            »val main = DI {
//                bindInstance { productionConfiguration }
//                bindSingleton<CandyFactory> { GummiesFactory() }
//            }
//
//            val sub = DI {
//                extend(main«non-cached:, copy = Copy.NonCached»«all:, copy = Copy.All»«none:, copy = Copy.None») «non-cached:// Default behavior»
//                bindInstance(overrides = true) { testApplicationConfiguration }
//            }
//            «retrieve:
//            val subFactory by sub.instance<CandyFactory>()
//            »«non-cached-assert:
//            assertSame(
//                productionApplicationConfiguration,
//                subFactory.appConfiguration // CandyFactory accessed from 'main'
//            )
//            »«none-assert:
//            assertSame(
//                productionApplicationConfiguration,
//                subFactory.appConfiguration // CandyFactory accessed from 'main'
//            )
//            »«all-assert:
//            assertSame(
//                testApplicationConfiguration,
//                subFactory.appConfiguration
//            )
//            »
//            """.trimIndent(),
//        ) {
//            "context" { lineHeight(state < 3) }
//            "non-cached" { fontGrow(state in 1..3) }
//            "retrieve" { lineHeight(state >= 2f) }
//            "non-cached-assert" { lineHeight(state == 3) }
//            "none" { fontGrow(state in 4..5) }
//            "all-assert" { lineHeight(state == 5) }
//            "all" { fontGrow(state in 6..7) }
//            "none-assert" { lineHeight(state == 7) }
//        }
//    },
)
