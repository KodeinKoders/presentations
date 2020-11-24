//package ws.utils
//
//import kotlinx.browser.window
//import kotlinx.css.*
//import org.kodein.kpres.Fade.In.prepare
//import org.kodein.kpres.Move
//import org.kodein.kpres.Transition
//import react.*
//import styled.css
//
//
//private interface SubSlideProps : RProps {
//    var states: IntRange
//    var currentState: Int
//    var transitionSet: Transition.Set
//}
//
//private sealed class TransitionState {
//    data class Prepare(val forward: Boolean) : TransitionState()
//    data class Execute(val forward: Boolean, val state: Int, val remaining: Int) : TransitionState()
//}
//
//sealed class CssStatus {
//    object Hidden : CssStatus()
//    object Visible : CssStatus()
//    class Transitioning(val ruleSet: RuleSet) : CssStatus()
//}
//
//private val SubSlide = functionalComponent<SubSlideProps> { props ->
//    var currentState: Int by useState(props.currentState)
//    var cssStatus: CssStatus by useState { if (props.currentState !in props.states) CssStatus.Hidden else CssStatus.Visible }
//
//    var currentTransition: Pair<Transition, TransitionState>? by useState(null)
//
//    useEffect(listOf(props.currentState)) {
//        val was = currentState in props.states
//        val willBe = props.currentState in props.states
//        currentTransition = when {
//            !was && !willBe -> null
//            was && willBe -> null
//            !was && willBe -> props.transitionSet.appear to TransitionState.Prepare(currentState < props.currentState)
//            was && !willBe -> props.transitionSet.disappear to TransitionState.Prepare(currentState < props.currentState)
//            else -> error("Impossible")
//        }
//        currentState = props.currentState
//    }
//
//    useEffectWithCleanup(listOf(currentTransition)) effect@ {
//        val (transition, state) = currentTransition ?: run {
//            cssStatus = if (props.currentState !in props.states) CssStatus.Hidden else CssStatus.Visible
//            return@effect ({})
//        }
//
//        val handle = when (state) {
//            is TransitionState.Prepare -> {
//                cssStatus = CssStatus.Transitioning { with(transition) { prepare(state.forward) } }
//                window.setTimeout({ currentTransition = transition to TransitionState.Execute(state.forward, 0, 300) }, 10)
//            }
//            is TransitionState.Execute -> {
//                val duration = transition.stateDuration(state.remaining, state.state)
//                cssStatus = CssStatus.Transitioning { with(transition) { execute(state.state, duration, state.forward) } }
//                val remaining = state.remaining - duration
//                when (remaining) {
//                    0 -> window.setTimeout({ currentTransition = null }, duration)
//                    else -> window.setTimeout({ currentTransition = transition to TransitionState.Execute(state.forward, state.state + 1, remaining) }, duration)
//                }
//
//            }
//        }
//
//        ({ window.clearTimeout(handle) })
//    }
//
//    flexColumn(JustifyContent.center, Align.center) {
//        css {
//            width = 100.pct
//            height = 100.pct
//            position = Position.absolute
//            left = 0.em
//            top = 0.em
//
//            when (val s = cssStatus) {
//                is CssStatus.Hidden -> { display = Display.none }
//                is CssStatus.Visible -> {}
//                is CssStatus.Transitioning -> { s.ruleSet.invoke(this) }
//            }
//        }
//        props.children()
//    }
//}
//
//
//fun RBuilder.subSlide(states: IntRange, currentState: Int, transitionSet: Transition.Set = Move, content: RBuilder.() -> Unit) {
//    child(SubSlide) {
//        attrs.states = states
//        attrs.currentState = currentState
//        attrs.transitionSet = transitionSet
//
//        content()
//    }
//}
