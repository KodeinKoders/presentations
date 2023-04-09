package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.animatedWith
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css

val kotlin_native = listOf(
    Slide(name = "no-fool", stateCount = 2) { state ->
        H2 { Text("I am not a fool!") }
        H3({ shownIf(state > 0, fade) }) { Text("So, where is the catch?") }
    },
    Slide(
        name = "kotlin-objective-c",
        stateCount = 3
    ) { state ->
        H2 {
            Text("Kotlin/Native does not support Swift interop!")
        }
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceAround)
                alignItems(AlignItems.Center)
                width(90.percent)
                paddingTop(2.em)
            }
        }) {
            Img(src = "img/kotlin.svg") {
                css { height(4.em) }
            }
            Img(src = "img/arrows.svg") {
                css { height(2.em) }
                shownIf(state >= 1, fontGrow)
            }
            Img(src = "img/objective-c.svg") {
                css { height(4.em) }
                shownIf(state >= 1, fontGrow)
            }
            Img(src = "img/arrows.svg") {
                css { height(2.em) }
                shownIf(state >= 2, fontGrow)
            }
            Img(src = "img/swift.svg") {
                css { height(4.em) }
                shownIf(state >= 2, fontGrow)
            }
        }
    },
    Slide("swift-is-coming") {
        H1 { Text(Emoji.sparkles) }
        H4 { Text("JetBrains is dropping Objective-C interop!") }
        H4 { Text("Interop with Swift is coming!") }
    },
    Slide("suspend-fun") {
        SourceCode(
            "kotlin", """
            class Manager { 
                suspend fun runTask() { ... }
            }
        """.trimIndent()
        )
    },
    Slide("async-func", stateCount = 4) { state ->
        H1 { Text(Emoji.sparkles) }
        SourceCode(
            "swift", """
            class Manager : KotlinBase { 
                «cb:func runTask(completion: @escaping (Error?) -> Void)»
                «async:func runTask() async throw»
            }
        """.trimIndent()
        ) {
            "cb" { zoomed(state == 1) }
            "async" { zoomed(state == 2) }
        }
    },
    Slide("flows") {
        H3 { Text("Swift has Combine...") }
        H3 { Text("meanwhile Kotlin has Flows.") }
    },
    Slide("compiler-plugin") {
        H1 { Text(Emoji.thinking) }
        H3 { Text("A compiler plugin: \nKMP-NativeCoroutines") }
    },
    Slide("flow-wrapper") {
        H1 { Text(Emoji.sparkles) }
        SourceCode(
            "kotlin", """
            class FlowWrapper<T>(private val flow: Flow<T>) {
                var job: Job = Job()
            
                fun collect(
                    onEach: (T) -> Unit,
                    onComplete: (Throwable) -> Unit
                ): () -> Unit) {
                    job = MainScope().launch {
                        flow.onCompletion { onComplete(it) }
                            .collect { onEach(it }
                    }
                    return  { job.cancel() }
                }
            }
        """.trimIndent()
        )
    },
    Slide("flow-wrapper-in-swift") {
        SourceCode(
            "swift", """
            @Published
            public var state: State
        
            public init(store: KMM.Store<State, Action>) {
                FlowWrapper<State>(flow: store.stateFlow)
                    .collect(
                        onEach: { [weak self] state in
                            guard let state = state else { return }
                            self?.state = state
                        },
                        onComplete: { [weak self] error in
                            guard let error = error else { return }
                            Logger.error("Error: \(${'$'}error)")
                        }
                    )
            }
        """.trimIndent()
        )
    },
    Slide("build-tools", stateCount = 3) { state ->
        H3 { Text("Cocoa-Pod is supported...") }
        H3({ shownIf(state >= 1, fade) }) { Text("...SPM is not.") }
        H4({ shownIf(state == 2, fade) }) { Text("(at least officially)") }
    },
    Slide("kmm-bridge") {
        H1 { Text(Emoji.sparkles) }
        H3 { Text("KMM-Bridge") }
    },
    Slide("we-care") {
        H1 { Text(Emoji.heart_eyes) }
        H3 { Text("We do care about your developer experience !!!") }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
