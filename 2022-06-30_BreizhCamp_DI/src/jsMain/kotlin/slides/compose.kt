package slides

import net.kodein.pres.Slide
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H4
import org.jetbrains.compose.web.dom.Text

val compose = listOf(
    Slide(
        name = "compose",
    ) {
        Div {
            H1 { Text("Kodein") }
            H1 { Text(Emoji.heart) }
            H1 { Text("Declarative UIs") }
        }
    },
    Slide(
        name = "compose-local",
        stateCount = 4
    ) { state ->
        H4 { Text("With Jetpack / JetBrains Compose") }
        SourceCode(
            lang = "kotlin",
            code = """
        @Composable
        «fun: fun App() {
        »«with: fun App() = withDI({ 
            bindInstance { productionParameters }
            bindSingleton { UserController(instance()) }
        }) {
        »    ContentView() 
        }
        
        «local:@Composable
        fun ContentView() {
            val di = LocalDI.current // Accessed through CompositionLocal
            val parameters: AppParameters by di.instance()
        }
        »«sub:@Composable
        fun ContentView() {
            subDI({  // Extends container from CompositionLocal
                bindSingleton { AccountController(instance()) }
            }) { /* .. */ }
        }»
      """.trimIndent()
        ) {
            "fun" { lineHeight(state < 1) }
            "with" { lineHeight(state >= 1) }
            "local" { lineHeight(state == 2) }
            "sub" { lineHeight(state == 3) }
        }
    },
    Slide(
        name = "compose-retrieve",
        stateCount = 2
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
        @Composable
        fun ContentView() {
        «local:    val di = LocalDI.current
            // Computed for every recomposition
            val parameters: AppParameters by di.instance()
        »«remember:     // Kept as state
            val parameters: AppParameters by rememberInstance()
        »}
      """.trimIndent()
        ) {
            "local" { lineHeight(state < 1) }
            "remember" { lineHeight(state >= 1) }
        }
    },
    Slide(
        name = "compose-android",
        stateCount = 5
    ) { state ->
        H4 { Text("Android specific") }
        SourceCode(
            lang = "kotlin",
            code = """
        class MainActivity : ComponentActivity(), «aware:DIAware» {  
            override val di = DI { importAll(/* .. */) }
        «out:    
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContent { App() }
            }
        »}
        
        @Composable
        fun App() {
        «di:    val di = «aware:androidContextDI()» 
        »«remember:    val userController: UserController by «aware:rememberInstance()»
            /* ... */
        »}
      """.trimIndent()
        ) {
            "di" { lineHeight(state in 1..2) }
            "aware" { zoomed(state in listOf(2,4)) }
            "out" { lineHeight(state < 3) }
            "remember" { lineHeight(state >= 3) }
        }
    },
)