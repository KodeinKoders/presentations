package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

val android = listOf(
    Slide(name = "android-head") {
        H2 { Text("Use it on Android!") }
    },
    Slide(name = "android-vm", stateCount = 4) {state ->
        SourceCode(
            "kotlin",
            """
                class BreweryViewModel : ViewModel() {«obs:
                    private val service = Service()

                    «z:private val _breweries = 
                        MutableStateFlow<List<Brewery>>(emptyList())»
                    val breweries = _breweries.asStateFlow()
                    »     fun fetchBreweries() {«launch:
                        viewModelScope.launch {
                            «z:_breweries.emit(service.getBreweries())»
                        }
               »     }
                }
            """.trimIndent()
        ) {
            "obs" { lineHeight(state >= 1) }
            "launch" { lineHeight(state >= 2) }
            "z" { zoomed(state == 3) }
        }
    },
    Slide(name = "android-view", stateCount = 6) { state ->
        SourceCode(
            "kotlin",
            """
                @Composable
                fun ContentView(«vm:viewModel: BreweryViewModel») {«s:
                    val breweries = viewModel.breweries.collectAsState()»«col:
                    Column(modifier = Modifier.fillMaxHeight()) {»«count:
                       Text(
                            text = "Brewery List (${'$'}{breweries.value.count()})",
                            modifier = Modifier.padding(6.dp)
                        )»«list:
                       LazyColumn {
                            items(breweries.value) {
                                Text(
                                    text = ${"\"\"\""}${'$'}{it.name} : ${'$'}{it.type}
                                        |${'$'}{it.city} ${'$'}{it.state}${"\"\"\""}.trimMargin(),
                                )
                            }
                        }»«col:
                   }
                »}
            """.trimIndent()
        ) {
            "vm" { fontGrow(state >= 1) }
            "s" { lineHeight(state >= 2) }
            "col" { lineHeight(state >= 3) }
            "count" { lineHeight(state >= 4) }
            "list" { lineHeight(state >= 5) }
        }
    },
)