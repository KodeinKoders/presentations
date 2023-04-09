package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.grow
import net.kodein.pres.animatedWith
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.shownIf
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text
import org.kodein.cic.css


val redux = listOf(
    Slide(
        name = "uniflow",
        stateCount = 1
    ) { _ ->
        H3 { Text("Unidirectional Data Flow") }

        Img(src = "img/uniflow.png") {
            css {
                height(22.em)
            }
        }
      },

    Slide(
        name = "uniflow-structure",
        stateCount = 6
    ) { state ->
        SourceCode(
            lang = "kotlin",
            """
                    abstract class State
                    abstract class Action
                    abstract class Mutation
                    
                    «a2:interface UseCase : (Action) -> Mutation»
                    «a3:interface Reducer<S: State> : (S, Mutation) -> S»
                    
                    abstract class Store<«a1:S : State», «a2:A : Action»>(
                        «a1:val initialState: S»,
                        «a3:val reducer: Reducer<S>»,
                        «a2:val useCases: Collection<UseCase>»,
                    «a4-in:) : CoroutineScope {
                        val stateFlow: StateFlow<S>
                        fun sendAction(action: A)
                        fun stop()
                    }»«a4-out:) : CoroutineScope»
                """.trimIndent()
        ) {
            "a1" { zoomed(state == 1) }
            "a2" { zoomed(state == 2) }
            "a3" { zoomed(state == 3) }
            "a4-in" { lineHeight(state > 4) }
            "a4-out" { shownIf(state <= 4, grow) }
        }
    },
            Slide(
            name = "uniflow-action",
    stateCount = 4
) { state ->
            SourceCode(
                lang = "kotlin",
                """
                    abstract class Store<S : State, A : Action>(...) : CoroutineScope {
                        private val actionFlow: MutableSharedFlow<A>()
                        private val internalStateFlow: MutableStateFlow<S>()
                        
                        «state:val state = internalStateFlow.asStateFlow()»
                        
                        fun sendAction(action: A) «act-out:{ ... }»«act-in1:{ »«act-in2:
                            launch { actionFlow.emit(action) }
                        }
                        
                        »    fun stop() «stop-out:{ ... }»«stop-in:{ job.cancel() }»
                    }
                """.trimIndent()
            ) {
                "state" { zoomed(state == 1) }
                "act-out" { fontGrow(state < 2) }
                "act-in1" { fontGrow(state >= 2) }
                "act-in2" { lineHeight(state >= 2) }
                "stop-out" { fontGrow(state < 3) }
                "stop-in" { fontGrow(state >= 3) }
            }
        },
    Slide(
        name = "uniflow-impl",
        stateCount = 4
    ) { state ->
            SourceCode(
                lang = "kotlin",
                """
                    abstract class Store<S : State, A : Action>(...) : CoroutineScope {
                        private val actionFlow: MutableSharedFlow<A>()
                        private val «emit:internalStateFlow»: MutableStateFlow<S>()
                        
                        init {
                            launch {«act-in:        
                                actionFlow.flatMapMerge { action ->
                                    allUseCases.flatMapMerge { useCase ->
                                        useCase(action)
                                    }
                                }»«red-in:            .mapNotNull { 
                                    reducer.reduce(internalStateFlow.value, it) 
                                }»«emit-in:            .collect { internalStateFlow.emit(it) }»
                            }
                        }
                    }
                """.trimIndent()
            ) {
                "act-in" { lineHeight(state >= 1) }
                "red-in" { lineHeight(state >= 2) }
                "emit-in" { lineHeight(state >= 3) }
            }
        },
    Slide(
        name = "uniflow-state",
        stateCount = 4
    ) { state ->
            SourceCode(
                lang = "kotlin",
                """
                    data class BreweryState(
                        val breweries: List<Breweries> = emptyList(),
                    ): State
                    «action:
                    sealed interface BreweryAction: Action {
                        object GetBreweries: Action
                    }
                    »«mutation: 
                    object UpdateBreweries(
                        val breweries: List<Breweries>
                    ): Mutation
                    »«store:
                    class BreweryStore() : Store<BreweryState, BreweryAction>(
                        initialState = BreweryState(),
                        useCases = listOf(GetBreweriesUseCase()),
                        reducer = BreweryReducer()
                    )
                    »
                """.trimIndent()
            ) {
                "action" { lineHeight(state >= 1) }
                "mutation" { lineHeight(state >= 2) }
                "store" { lineHeight(state >= 3) }
            }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))