package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import kotlin.time.Duration.Companion.milliseconds

val ios_head = Slide(name = "ios-head") {
    H2 { Text("Use it on iOS!") }
}

val ios_vm = listOf(
    Slide(
        name = "ios-vm-1",
        stateCount = 2,
        outAnimation = Animations.Fade(300.milliseconds)
    ) { state ->
        SourceCode(
            "swift",
            """
        open class AbstractViewModel<S: KMMSDK.State, A: KMMSDK.Action>
            : ObservableObject {
            
            «z:@Published public var state: S»
            private let store: KMMSDK.Store<S, A>
            
            public init(store: KMMSDK.Store<S, A>) { ... }
            func sendAction(action: A) { ... }
            deinit { ... }
        }
    """.trimIndent()
        ) {
            "z" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "ios-vm-2",
        stateCount = 3,
        outAnimation = Animations.Fade(300.milliseconds)
    ) { state ->
        SourceCode(
            "swift",
            """
        open class AbstractViewModel /* ... */ {
            
            «z:@Published public var state: S»
            private let store: KMMSDK.Store<S, A>
            
            public init(store: KMMSDK.Store<S, A>) { 
                self.store = store
                «z1:self.state = store.initialState»
                FlowWrapper<State>(flow: «z2:store.stateFlow»)
                    .«z2:collect»(
                        «z2:onEach: { self?.state = $0 }»,
                        onThrow: { Logger.error("Error: \(${'$'}0)") }
                    )
            }
            func sendAction(action: A) { ... }
            deinit { ... }
        }
    """.trimIndent()
        ) {
            "z" { zoomed(state > 0) }
            "z1" { zoomed(state == 1) }
            "z2" { zoomed(state == 2) }
        }
    },
    Slide(
        name = "ios-vm-3",
        stateCount = 2,
        outAnimation = Animations.Fade(300.milliseconds)
    ) { state ->
        SourceCode(
            "swift",
            """
        open class AbstractViewModel /* ... */ {
            
            @Published public var state: S
            private let store: KMMSDK.Store<S, A>
            
            public init(store: KMMSDK.Store<S, A>) { ... }
            
            «z:func sendAction(action: Action) { 
            store.sendAction(action: action)
        }»
            
            deinit { ... }
        }
    """.trimIndent()
        ) { "z" { zoomed(state == 1) } }
    },
    Slide(
        name = "ios-vm-4",
        stateCount = 2,
        outAnimation = Animations.Fade(300.milliseconds)
    ) { state ->
        SourceCode(
            "swift",
            """
        open class AbstractViewModel /* ... */ {
            
            @Published public var state: S
            private let store: KMMSDK.Store<S, A>
            
            public init(store: KMMSDK.Store<S, A>) { ... }
            func sendAction(action: A) { ... }
            
            «z:deinit {
            store.stop()
        }»
        }
    """.trimIndent()
        ) { "z" { zoomed(state == 1) } }
    },
    Slide(
        name = "ios-vm-5",
        stateCount = 1,
        outAnimation = Animations.Fade(300.milliseconds)
    ) { _ ->
        SourceCode(
            "swift",
            """
        class BreweryViewModel
            : AbstractViewViewModel<BreweryState, BreweryAction> {
            init() {
                super.init(store: BreweryStore())
            }
        }
        """.trimIndent()
        )
    }
)

val ios_view = Slide(
    name = "ios-view",
    stateCount = 3,
    outAnimation = Animations.Fade(300.milliseconds)
) { state ->
        SourceCode(
            "swift",
            """
            struct ContentView: View {
               «z:@ObservedObject
           var viewModel = BreweryViewModel()»
               
               var body: some View {
                 VStack {
                     «z:Text("Breweries List (\(viewModel.state.breweries.count))")
             List(viewModel.breweries.state.indices, id: \.self) { index in
                 let brewery = viewModel.state.breweries[index]
                 Text(${"\"\"\""}
                      \(brewery.name), \(brewery.type) 
                      \(brewery.city), \(brewery.state)
                      ${"\"\"\""})
             }»
                  }
               }
            }
            """.trimIndent()
        ) {
            "z" { zoomed(state == 1) }
        }
}