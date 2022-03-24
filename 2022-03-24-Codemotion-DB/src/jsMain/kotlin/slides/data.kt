package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.util.transition
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement


@Composable
private fun BigWord(attrs: AttrBuilderContext<out HTMLElement>? = null, content: @Composable () -> Unit) {
    H3({
        css {
            position(Position.Absolute)
            width(8.em)
            height(1.em)
            textAlign("center")
        }
        style {
            margin(0.em)
            fontWeight(200)
        }
        attrs?.invoke(this)
    }) {
        content()
    }
}

@Composable
private fun MediumWord(attrs: AttrBuilderContext<out HTMLElement>? = null, content: @Composable () -> Unit) {
    H4({
        css {
            position(Position.Absolute)
            width(8.em)
            height(1.em)
            textAlign("center")
        }
        style {
            margin(0.em)
        }
        attrs?.invoke(this)
    }) {
        content()
    }
}

@Composable
private fun SmallWord(attrs: AttrBuilderContext<out HTMLElement>? = null, content: @Composable () -> Unit) {
    P({
        css {
            position(Position.Absolute)
            width(8.em)
            height(1.em)
            textAlign("center")
        }
        style {
            margin(0.em)
            fontWeight(200)
        }
        attrs?.invoke(this)
    }) {
        content()
    }
}

@Composable
private fun Arrow(attrs: AttrBuilderContext<out HTMLElement>? = null) {
    Img("img/arrow.webp") {
        css {
            position(Position.Absolute)
            width(8.em)
        }
        attrs?.invoke(this)
    }
}

val data = Slide(
    name = "data",
    stateCount = 11
) { state ->

    Div({
        css {
            position(Position.Relative)
            width(100.percent)
            height(100.percent)
        }
    }) {
        Div({
            css {
                position(Position.Absolute)
                top(0.percent)
                left(0.percent)
                width(100.percent)
                height(100.percent)
                transition { "opacity"(300.ms) }
            }
            style {
                opacity(if (state < 10) 1 else 0.25)
            }
        }) {
            BigWord({
                css {
                    property("top", 0.5.em)
                    property("left", 45.percent - 4.em)
                }
            }) { Text("Model") }

            BigWord({
                shownIf(state >= 1, fade)
                css {
                    property("top", 50.percent - 0.5.em)
                    property("left", 22.percent - 4.em)
                }
            }) { Text("Blob") }

            BigWord({
                shownIf(state >= 3, fade)
                css {
                    property("top", 50.percent - 0.5.em)
                    property("left", 68.percent - 4.em)
                }
            }) { Text("Metadata") }

            BigWord({
                shownIf(state >= 7, fade)
                css {
                    property("top", 100.percent - 1.5.em)
                    property("left", 45.percent - 4.em)
                }
            }) { Text("Datastore") }

            Div({
                shownIf(state >= 4, fade)
                css {
                    position(Position.Absolute)
                    height(8.em)
                    display(DisplayStyle.Flex)
                    justifyContent(JustifyContent.Center)
                    alignItems(AlignItems.Center)
                    property("top", 50.percent - 4.em)
                    property("right", 2.em)
                }
            }) {
                Ul {
                    Li { Text("Type") }
                    Li { Text("id") }
                    Li { Text("[indexes]*") }
                }
            }

            Arrow {
                shownIf(state >= 1, fade)
                css {
                    transform { rotate(130.deg) }
                    top(20.percent)
                    left(22.percent)
                }
            }
            Arrow {
                shownIf(state >= 3, fade)
                css {
                    transform { rotate(50.deg) }
                    top(20.percent)
                    left(47.percent)
                }
            }
            Arrow {
                shownIf(state >= 7, fade)
                css {
                    transform { rotate(50.deg) }
                    top(62.percent)
                    left(22.percent)
                }
            }
            Arrow {
                shownIf(state >= 7, fade)
                css {
                    transform { rotate(130.deg) }
                    top(62.percent)
                    left(47.percent)
                }
            }
            Arrow {
                shownIf(state >= 9, fade)
                css {
                    transform { rotate(230.deg) }
                    top(62.percent)
                    left(18.percent)
                }
            }
            Arrow {
                shownIf(state >= 9, fade)
                css {
                    transform { rotate(310.deg) }
                    top(20.percent)
                    left(18.percent)
                }
            }

            SmallWord({
                shownIf(state >= 6, fade)
                css {
                    property("top", 42.percent - 0.5.em)
                    property("left", 45.percent - 4.em)
                }
            }) { Text("Model layer") }

            SmallWord({
                shownIf(state >= 8, fade)
                css {
                    property("top", 58.percent - 0.5.em)
                    property("left", 45.percent - 4.em)
                }
            }) { Text("Data layer") }
        }
        Div({
            css {
                position(Position.Absolute)
                top(0.percent)
                left(0.percent)
                width(100.percent)
                height(100.percent)
            }
        }) {
            MediumWord({
                shownIf(state >= 2, fade)
                css {
                    property("top", 28.percent - 0.5.em)
                    property("left", 30.percent - 4.em)
                }
            }) {
                Span({
                    shownIf(state >= 9, fade)
                }) {
                    Text("(De)")
                }
                Text("Serializer")
            }

            MediumWord({
                shownIf(state >= 5, fade)
                css {
                    property("top", 28.percent - 0.5.em)
                    property("left", 60.percent - 4.em)
                }
            }) { Text("Definition") }
        }
    }
}
