package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.transform
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.Swipe
import org.kodein.kpres.subSlide
import react.dom.b
import react.dom.br
import react.dom.h3
import react.dom.h4
import styled.css
import styled.styledDiv
import styled.styledH3
import styled.styledH4
import ws.charter.kodein
import ws.utils.*


fun PresentationBuilder.declarativeUi() = slide(
    stateCount = 17
) { props ->
    titledSlide("Declarative UI") {

        subSlide(0..2, props.state, Swipe) {
            styledDiv {
                css {
                    "h4" {
                        color = Color.kodein.klycine
                        fontWeight = FontWeight.lighter
                        textAlign = TextAlign.left
                    }
                    "b" {
                        color = Color.kodein.korail
                        fontWeight = FontWeight.medium
                    }
                }
                h4 {
                    +"Declarative UI eliminates time by using a "
                    b { +"single source of truth" }
                    +"."
                    br {}
                    +"It and ensures consistency via "
                    b { +"unidirectional data flow" }
                    +"."
                }

                styledH4 {
                    opacity(props.state >= 1)
                    +"Essentially, your view becomes a "
                    b { +"function of your model" }
                    +"."
                }
            }

            flexRow(JustifyContent.spaceAround) {
                css {
                    width = 100.pct
                    marginTop = 1.5.em
                    opacity(props.state >= 2)

                    "h3" {
                        color = Color.kodein.orange
                        fontWeight = FontWeight.regular
                    }

                    h3 { +"Jetpack Compose" }
                    h3 { +"SwiftUI" }
                }
            }
        }

        subSlide(3..4, props.state, Swipe) {
            styledDiv {
                css {
                    width = 100.pct
                    height = 20.rem
                    position = Position.relative

                    "h3, h4" {
                        position = Position.absolute
                        fontWeight = FontWeight.light
                        margin(0.em)
                    }

                    "h4" {
                        fontSize = .8.em
                        fontFamily = "jetbrains mono"
                    }

                    "div" {
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
                        width = 18.rem
                        left = 50.pct - 9.rem
                        top = 14.rem
                        color = Color.kodein.kaumon
                    }
                    s {
                        fontSize(props.state <= 3)
                        +"View"
                    }
                    s {
                        css.fontFamily = "jetbrains mono"
                        fontSize(props.state >= 4)
                        +"view(model)"
                    }
                }

                styledDiv {
                    css {
                        left = 54.pct - 5.rem
                        top = 8.rem
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
                    }
                    +"Model"
                }

                styledDiv {
                    css {
                        left = 46.pct - 5.rem
                        top = 8.rem
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
                    }
                    +"Intent"
                }
            }
        }

        subSlide(5..10, props.state, Swipe) {
            styledH3 {
                css {
                    color = Color.kodein.kamethiste
                    fontWeight = FontWeight.light
                }
                +"SwiftUI"
            }

            slideCode(props.state - 5, "swift",
                """
                    struct CounterView: View {
                        var body: some View {
                            «f:1«MVIView({ getCounterController() })» «f:2«{ model, postIntent in»
                                VStack {
                                    «f:3«Text("Counter: \(model.counter)")»
                                    «f:4«Button { postIntent(Counter.IntentAdd(delta: 1)) }» label: {
                                        Text("Increment")
                                    }
                                    «f:4«Button { postIntent(Counter.IntentReset()) }» label: {
                                        Text("Reset!")
                                    }
                                }
                            }
                        }
                    }
                """.trimIndent()) {
                    fontSize = .8.em
                }
        }

        subSlide(11..16, props.state, Swipe) {
            styledH3 {
                css {
                    color = Color.kodein.kamethiste
                    fontWeight = FontWeight.light
                }
                +"Jetpack Compose"
            }

            slideCode(props.state - 11, "kotlin",
                """
                    @Composable
                    fun CounterView() {
                        «f:1«MVIView({ getCounterController() })» { «f:2«model, postIntent ->»
                            Column {
                                «f:3«Text("Counter: ${"$"}(model.counter)")»
                                «f:4«Button(onClick = { postIntent(Counter.Intent.Add(1)) })» {
                                    Text("Increment")
                                }
                                «f:4«Button(onClick = { postIntent(Counter.Intent.Reset) })» {
                                    Text("Reset!")
                                }
                            }
                        }
                    }
                """.trimIndent()) {
                fontSize = .8.em
            }

        }

    }
}
