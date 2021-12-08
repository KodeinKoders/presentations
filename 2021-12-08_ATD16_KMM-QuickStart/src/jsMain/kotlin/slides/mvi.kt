package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.pres.widget.SubSlide
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text


val mvi = listOf(
    Slide(
        name = "mvi",
        stateCount = 5
    ) { state ->
        H2 { Text("Model View Intent") }

        Img(src = "img/mvi.png") {
            css {
                height(10.em)
            }
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceAround)
                width(100.percent)
            }
        }) {
            Div({
                shownIf(state >= 1, fade)
            }) {
                SourceCode(
                    lang = "kotlin",
                    """
                        @Composable
                        fun ViewContent(
                            «z:model: Model»,
                            «z:post: (Intent) -> Unit»
                        )
                    """.trimIndent()
                ) { "z" { zoomed(state == 3) } }
            }

            Div({
                shownIf(state >= 2, fade)
            }) {
                SourceCode(
                    lang = "swift",
                    """
                        struct ViewContent: View {
                            var «z:model: Model»
                            var «z:post: (Intent) -> Void»
                        }
                    """.trimIndent()
                ) { "z" { zoomed(state == 3) } }
            }
        }
    },

    Slide(
        name = "mvi-by-hand",
        stateCount = 6
    ) { state ->
        SubSlide(state == 0, fade) {
            SourceCode(
                lang = "kotlin",
                """
                    object MVI {
                        abstract class Model
                        abstract class Intent
    
                        abstract class Controller<M : Model, I : Intent>(
                            val firstModel: M
                        ) {
                            abstract fun subscribe(onModel: (M) -> Unit): () -> Unit
                            abstract fun intent(intent: I)
                            abstract fun stop()
                        }
                    }
                """.trimIndent()
            )
        }
        SubSlide(state in 1..2, fade) {
            SourceCode(
                lang = "kotlin",
                """
                    abstract class AppController<M : MVI.Model, I : MVI.Intent>(
                        loggerFactory: LoggerFactory, firstModel: M
                    ) : MVI.Controller<M, I>(firstModel), «z:CoroutineScope» {
                    
                        private val job = Job()
                        «z:override val coroutineContext» = MainScope().coroutineContext + job
                        
                        init {
                            launchModels()
                            launchIntents()
                        }
                """.trimIndent()
            ) {
                "z" { zoomed(state == 2) }
            }
        }
        SubSlide(state == 3, fade) {
            SourceCode(
                lang = "kotlin",
                """
                    internal val models = MutableStateFlow(firstModel)
                    private val modelChanges = Channel<M.() -> M>()
                    
                    private fun launchModels() = launch {
                        modelChanges.consumeEach { change ->
                            models.emit(change(models.value))
                        }
                    
                    protected suspend fun model(change: M.() -> M) =
                        modelChanges.send(change)

                    protected suspend fun model(model: M) =
                        modelChanges.send { model }
                """.trimIndent()
            ) {
                "z" { zoomed(state == 2) }
            }
        }
        SubSlide(state == 4, fade) {
            SourceCode(
                lang = "kotlin",
                """
                    internal val intents = Channel<I>(Channel.UNLIMITED)
                    
                    protected abstract suspend fun process(intent: I)
                    
                    private fun launchIntents() = launch {
                        intents.consumeEach { process(it) }
                    }

                    override fun intent(intent: I) = intents.trySend(intent)
                """.trimIndent()
            ) {
                "z" { zoomed(state == 2) }
            }
        }
        SubSlide(state == 5, fade) {
            SourceCode(
                lang = "kotlin",
                """
                    override fun subscribe(onModel: (M) -> Unit): () -> Unit {
                        val subscription = launch {
                            models.collect { onModel(it) }
                        }
                        return ({ subscription.cancel() })
                    }
                    
                    override fun stop() {
                        job.cancel()
                    }
                """.trimIndent()
            ) {
                "z" { zoomed(state == 2) }
            }
        }
    },

    Slide(
        name = "mvi-kotlin"
    ) {
        H2 { Text("MVIKotlin") }
        Img(src = "img/mvikotlin.png") {
            css {
                height(18.em)
            }
        }
    }
)