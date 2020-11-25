package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.Flip
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.Swipe
import org.kodein.kpres.subSlide
import react.dom.br
import styled.*
import ws.charter.kodein
import ws.utils.*

fun PresentationBuilder.mvi() = slide(
    inTransitions = Flip,
    inTransitionDuration = 1500,
    containerStyle = {
        ".inner-container" {
            backgroundColor = Color.transparent
            transition(::background, 1500.ms)
        }
    },
    stateCount = 28,
) { props ->
    titledSlide("Model View Intent") {

        subSlide(0..6, props.state, Swipe) {
            styledDiv {
                css {
                    width = 100.pct
                    height = 20.rem
                    position = Position.relative
    //                backgroundColor = Color.kodein.darker

                    "h3, h4" {
                        position = Position.absolute
                        fontWeight = FontWeight.light
                        margin(0.em)
                    }

                    "h4" {
                        fontSize = .8.em
                        fontFamily = "jetbrains mono"
                    }

                    "span, div" {
                        position = Position.absolute
                    }
                }

                styledH3 {
                    css {
                        width = 12.rem
                        left = 50.pct - 6.rem
                        top = 0.rem
                        color = Color.kodein.kaumon
                    }
                    +"Controller"
                }

                styledH3 {
                    css {
                        width = 12.rem
                        left = 50.pct - 6.rem
                        top = 14.rem
                        color = Color.kodein.kaumon
                    }
                    +"View"
                }

                styledDiv {
                    css {
                        left = 54.pct - 5.rem
                        top = 8.rem
                        opacity(props.state >= 1)
                        if (props.state < 1) transform { translateY((-2).rem) }
                        if (props.state < 2) transform { translateX((-2.5).rem) }
                        transition(::opacity, 0.6.s)
                        transition(::transform, 0.6.s)
                    }
                    arrow(Color.kodein.kamethiste, width = 10.rem) {
                        transform { rotate(90.deg) }
                    }
                }

                styledH3 {
                    css {
                        width = 8.rem
                        left = 61.pct - 4.rem
                        top = 5.5.rem
                        color = Color.kodein.orange
                        opacity(props.state >= 1)
                        if (props.state < 1) transform { translateY((-2).rem) }
                        if (props.state < 2) transform { translateX((-2.5).rem) }
                        transition(::opacity, 0.6.s)
                        transition(::transform, 0.6.s)
                    }
                    +"Model"
                }

                styledH4 {
                    css {
                        width = 15.rem
                        left = 63.pct - 4.rem
                        top = 11.rem
                        color = Color.kodein.kamethiste
                        opacity(props.state >= 3)
                    }
                    +"display(model)"
                }

                styledDiv {
                    css {
                        left = 46.pct - 5.rem
                        top = 8.rem
                        opacity(props.state >= 2)
                        if (props.state < 2) transform { translateY(2.rem) }
                        transition(::opacity, 0.6.s)
                        transition(::transform, 0.6.s)
                    }
                    arrow(Color.kodein.kamethiste, width = 10.rem) {
                        transform { rotate((-90).deg) }
                    }
                }

                styledH3 {
                    css {
                        width = 8.rem
                        left = 39.pct - 4.rem
                        top = 8.rem
                        color = Color.kodein.orange
                        opacity(props.state >= 2)
                        if (props.state < 2) transform { translateY(2.rem) }
                        transition(::opacity, 0.6.s)
                        transition(::transform, 0.6.s)
                    }
                    +"Intent"
                }

                styledH4 {
                    css {
                        width = 15.rem
                        right = 63.pct - 4.rem
                        top = 2.5.rem
                        color = Color.kodein.kamethiste
                        opacity(props.state >= 5)
                    }
                    +"process(intent)"
                }
            }

            flexRow(JustifyContent.spaceAround) {
                css {
                    width = 100.pct
                    "h3" {
                        fontWeight = FontWeight.lighter
                        color = Color.kodein.cute
                    }
                }

                styledH3 {
                    opacity(props.state >= 6)
                    +"Unidirectional data flow"
                }

                styledH3 {
                    opacity(props.state >= 4)
                    +"Single source of truth"
                }
            }
        }

        subSlide(7..13, props.state, Swipe) {
            slideCode(props.state - 7, "kotlin",
                """
                    object MVI {
                        «f:1«abstract class Model
                    abstract class Intent»
        
                        «f:2«abstract class Controller<M : Model, I : Intent>»(val firstModel: M) {
        
                            «f:3«abstract fun subscribe(onModel: (M) -> Unit): () -> Unit»
        
                            «f:4«abstract fun intent(intent: I)»
        
                            «f:5«abstract fun stop()»
                        }
        
                    }
                """.trimIndent()) {
                width = 100.pct
                fontSize = 1.7.rem
            }
        }

        subSlide(14..19, props.state, Swipe) {
            slideCode(props.state - 14, "kotlin",
                """
                    object Counter {«l:1-«
                        data class Model(val count: Int) : MVI.Model()»«l:2-«
                        sealed class Intent : MVI.Intent() {
                            data class Add(val delta: Int) : Intent()
                            object Reset : Intent()
                        }»«l:3-«

                        class Controller : AbstractController<Model, Intent>(Model(0)) {
                            override fun process(intent: Intent) {
                                when (intent) {«l:4-«
                                    is Add -> model { copy(count = count + intent.delta) }»«l:5-«
                                    is Reset -> model(Model(0))
                    »            }
                            }
                        }
                    »}
                """.trimIndent()) {
                width = 100.pct
                fontSize = 1.7.rem
            }
        }

        subSlide(20..27, props.state, Swipe) {
            slideCode(props.state - 20, "kotlin",
                """
                    object Chat {«l:1-«
                        sealed class Model {
                            object Loading : Model()
                            data class Messages(val messages: List<Message>)
                        }»«l:2-«
                        sealed class Intent {
                            data class Send(val text: String) : Intent()
                        }»«l:3-«
                        
                        class Controller(val room: Room) : «f:4«AbstractController»<Model, Intent>(Model.Loading) {
                            init {
                                «f:4«launch» {«l:5-«
                                    room.newMessages().consumeEach {
                                        model { copy(messages = messages + it) }
                                    }
                    »            }«l:6-«
                                model(room.getAllMessages())
                    »        }«l:7-«
                            override fun process(intent: Intent) {
                                room.send((intent as! Intent.Send).text)
                            }
                    »    }
                    »}
                """.trimIndent()) {
                width = 100.pct
                fontSize = 1.5.rem
            }
        }

    }
}
