package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

val ios = listOf(
    Slide(name = "ios-head") {
        H2 { Text("Use it on iOS!") }
    },

    Slide(
        name = "ios-vm",
    stateCount = 4
    ) { state ->
        SourceCode(
            "swift",
            """
        class BreweryViewModel«p1: : ObservableObject» {«pub:
            «z:@Published var breweries = [Brewery]()»
            let service = Service()»
            func fetchBreweries() {«p2: }»«fun:
                service.getBreweries { 
                    (breweries: [Brewery]?, error: Error?) in
                    if let breweries = breweries {
                        «z:self.breweries = breweries»
                    }
                    if let err = error {
                        print(err)
                    }
                }
            }
        »}
    """.trimIndent()
        ) {
            "pub" { lineHeight(state >= 1) }
            "p1" { fontGrow(state >= 1) }
            "p2" { fontGrow(state < 2) }
            "fun" { lineHeight(state >= 2) }
            "z" { zoomed(state == 3) }
        }
    },
    Slide(
        name = "ios-view1",
        stateCount = 4,
        outAnimation = Animations.Fade(300.milliseconds)
    ) { state ->
        SourceCode(
            "swift",
            """
                struct ContentView: View {«obs:
                    @ObservedObject var viewModel = BreweryViewModel()
                
               »     var body: some View {«stack:
                        VStack {
                            Text("Breweries List (\(viewModel.breweries.count))")«list:
                            List(viewModel.breweries.indices, id: \.self) { index in
                                let brewery = viewModel.breweries[index]
                                Text(${"\"\"\""}
                                     \(brewery.name), \(brewery.type) 
                                     \(brewery.city), \(brewery.state)
                                     ${"\"\"\""})
                            }
                    »         }»
                    }
                }
            """.trimIndent()
        ) {
            "obs" { lineHeight(state >= 1) }
            "stack" { lineHeight(state >= 2) }
            "list" { lineHeight(state >= 3) }
        }

    },
    Slide(
        name = "ios-view2",
        inAnimation = Animations.Fade(300.milliseconds)
    ) {
        SourceCode(
            "swift",
            """
                VStack {
                    Text("Breweries List (\(viewModel.breweries.count))")
                    List(viewModel.breweries.indices, id: \.self) { index in
                        let brewery = viewModel.breweries[index]
                        Text(${"\"\"\""}
                             \(brewery.name), \(brewery.type) 
                             \(brewery.city), \(brewery.state)
                             ${"\"\"\""})
                    }
                }.onAppear {
                    viewModel.fetchBreweries()
                }
            """.trimIndent()
        )

    },
)