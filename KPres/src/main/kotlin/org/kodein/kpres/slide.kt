package org.kodein.kpres

import kotlinx.css.*
import kotlinx.html.classes
import org.kodein.kpres.utils.getValue
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.RProps
import react.functionalComponent
import react.useEffectWithCleanup
import react.useRef
import styled.css
import styled.styledDiv
import kotlin.browser.window
import kotlin.math.min


internal interface FullSlideProps : RProps {
    var state: Int
    var style: CSSBuilder.(Int) -> Unit
}

class SlideContentProps(val state: Int, val shouldAnim: Boolean) : RProps

internal val Slide by functionalComponent<FullSlideProps> { props ->
    val outer = useRef<HTMLDivElement?>(null)
    val inner = useRef<HTMLDivElement?>(null)

    useEffectWithCleanup {
        val listener = { _: Event? ->
            val factor = min(outer.current!!.offsetWidth.toDouble() / 1024.0, outer.current!!.offsetHeight.toDouble() / 640.0).takeIf { it > 1.0 } ?: 1.0
            inner.current!!.style.transform = "scale(${factor})"
        }
        listener(null)
        window.addEventListener("resize", listener)
        ({ window.removeEventListener("resize", listener) })
    }

    styledDiv {
        ref = outer
        css {
            width = 100.pct
            height = 100.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center

            props.style.invoke(this, props.state)
        }

        styledDiv {
            attrs.classes = setOf("inner-slide")
            ref = inner
            css {
                width = (1024.px - 2.em)
                height = (640.px - 2.em)
                padding(1.em)
                fontSize = 2.em
                display = Display.flex
                flexDirection = FlexDirection.column
                justifyContent = JustifyContent.center
                alignItems = Align.center
                alignContent = Align.center
                textAlign = TextAlign.center
                overflow = Overflow.hidden
                position = Position.relative
            }

            props.children()
        }
    }
}
