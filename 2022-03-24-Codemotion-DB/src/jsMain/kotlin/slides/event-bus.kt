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
fun Word(attrs: AttrBuilderContext<out HTMLElement>? = null, content: @Composable () -> Unit) {
    H3({
        css {
            position(Position.Absolute)
            width(6.em)
            height(1.em)
            textAlign("center")
            transition {
                "top"(500.ms)
                "left"(500.ms)
                "opacity"(500.ms)
                "transform"(500.ms)
            }
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
private fun Arrow(width: CSSNumeric = 8.em, attrs: AttrBuilderContext<out HTMLElement>? = null) {
    Img("img/arrow.webp") {
        css {
            position(Position.Absolute)
            width(width)
            transition {
                "top"(500.ms)
                "left"(500.ms)
                "opacity"(500.ms)
                "transform"(500.ms)
            }
        }
        attrs?.invoke(this)
    }
}



val eventBus = Slide(
    name = "event-bus",
    stateCount = 7
) { state ->
    Div({
        css {
            position(Position.Relative)
            width(100.percent)
            transition { "height"(500.ms) }
        }
        style {
            height(if (state >= 6) 13.em else 22.em)
        }
    }) {
        Word({
            css {
                property("top", 0.em)
                property("left", 50.percent - 3.em)
            }
        }) { Text("Business") }

        Word({
            shownIf(state >= 1, fade)
            css {
                property("top", 3.em)
                property("left", 0.percent)
            }
        }) { Text("Request") }

        Word({
            shownIf(state >= 3, fade)
            css {
                property("top", 3.em)
                property("right", 0.percent)
            }
        }) { Text("Service") }

        Word({
            shownIf(state >= 2, fade)
            css {
                property("left", 50.percent - 3.em)
            }
            style {
                top(if (state in 4..5) 11.em else 6.em)
            }
        }) { Text("DataBase") }

        Word({
            shownIf(state in 4..5, fade)
            css {
                property("top", 6.em)
                property("left", 50.percent - 3.em)
            }
            style {
                transform {
                    if (state !in 4..5) translateY((-100).px)
                }
            }
        }) { Text("Event Bus") }

        Arrow {
            shownIf(state >= 1, fade)
            css {
                transform { rotate(155.deg) }
                top(1.6.em)
            }
            style {
                left(if (state < 5) 7.em else 9.em)
            }
        }
        Arrow {
            shownIf(state >= 2, fade)
            style {
                if (state < 5) {
                    transform { rotate((-25).deg) }
                    property("top", 1.6.em)
                    property("left", 9.em)
                } else {
                    transform {
                        rotate((-90).deg)
                        scale(1.1)
                    }
                    property("top", 4.25.em)
                    property("left", 50.percent - 96.px)
                }
            }
        }
        Arrow {
            shownIf(state >= 2, fade)
            css {
                transform { rotate(25.deg) }
                top(7.em)
                left(9.em)
            }
        }
        Arrow {
            shownIf(state >= 3, fade)
            style {
                if (state < 5) {
                    transform { rotate(205.deg) }
                    top(1.6.em)
                    left(21.7.em)
                } else {
                    transform {
                        rotate(270.deg)
                        scale(1.1)
                    }
                    property("top", 4.25.em)
                    property("left", 50.percent - 96.px)
                }
            }
        }
        Arrow {
            shownIf(state >= 3, fade)
            css {
                transform { rotate(155.deg) }
                top(7.em)
                left(21.7.em)
            }
        }
        Arrow(7.em) {
            shownIf(state in 4..5, fade)
            css {
                top(14.4.em)
                property("left", 50.percent - 84.px)
            }
            style {
                transform {
                    if (state !in 4..5) translateY((-100).px)
                    rotate(90.deg)
                }
            }
        }
    }
}