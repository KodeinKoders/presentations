package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.animatedWith
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

val android = listOf(
    Slide(name = "android-head") {
        H2 { Text("Use it on Android!") }
    },
    Slide(name = "android-view", stateCount = 2) { state ->
        SourceCode(
            "kotlin",
            """
            @Composable
            fun ContentView(store: BreweryStore) {
                «z:val state = store.stateFlow.collectAsState()»
                
                Column(modifier = Modifier.fillMaxHeight()) {
                   Text(
                        «z:text = "Brewery List (${'$'}{state.count()})"»,
                        modifier = Modifier.padding(6.dp)
                    )
                   LazyColumn {
                        «z:items(state) {
            Text(
                text = ${"\"\"\""}${'$'}{it.name} : ${'$'}{it.type}
                    |${'$'}{it.city} ${'$'}{it.state}${"\"\"\""}
                        .trimMargin()
            )
        }»
                    }
               }
            }
            """.trimIndent()
        ) {
            "z" { zoomed(state == 1) }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
