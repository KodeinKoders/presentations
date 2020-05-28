package org.kodein.kpres

import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.css.properties.transform as transforms


interface Transition {
    fun CSSBuilder.prepare(forward: Boolean)
    fun stateDuration(remaining: Int, state: Int): Int = remaining
    fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean)

    open class Set(val appear: Transition, val disappear: Transition)
}

object Fade : Transition.Set(In, Out) {
    object In : Transition {
        override fun CSSBuilder.prepare(forward: Boolean) {
            opacity = 0.0
        }

        override fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean) {
            transition(::opacity, duration.ms)
            opacity = 1.0
        }
    }
    object Out : Transition {
        override fun CSSBuilder.prepare(forward: Boolean) {
            opacity = 1.0
        }

        override fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean) {
            transition(::opacity, duration.ms)
            opacity = 0.0
        }
    }
}

object Move : Transition.Set(In, Out) {
    object In : Transition {
        override fun CSSBuilder.prepare(forward: Boolean) {
            opacity = 0.0
            transforms { translateX(if (forward) 50.pct else (-50).pct) }
        }

        override fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean) {
            transition(::transform, duration.ms)
            transition(::opacity, duration.ms)
            transforms {}
            opacity = 1.0
        }
    }
    object Out : Transition {
        override fun CSSBuilder.prepare(forward: Boolean) {
            opacity = 1.0
            transforms {}
        }

        override fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean) {
            transition(::transform, duration.ms)
            transition(::opacity, duration.ms)
            transforms { translateX( if (forward) (-50).pct else 50.pct) }
            opacity = 0.0
        }
    }
}

object Flip : Transition.Set(In, Out) {
    object In : Transition {
        override fun CSSBuilder.prepare(forward: Boolean) {
            opacity = 0.0
            transforms {
                perspective(90.em)
                rotateY(if (forward) 90.deg else (-90).deg)
            }
        }
        override fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean) {
            transition(::transform, duration.ms)
            transition(::opacity, duration.ms)
            transforms {
                perspective(90.em)
            }
            opacity = 1.0
        }
    }
    object Out : Transition {
        override fun CSSBuilder.prepare(forward: Boolean) {
            opacity = 1.0
            transforms {
                perspective(90.em)
            }
        }
        override fun CSSBuilder.execute(state: Int, duration: Int, forward: Boolean) {
            transition(::transform, duration.ms)
            transition(::opacity, duration.ms)
            transforms {
                perspective(90.em)
                rotateY(if (forward) (-90).deg else 90.deg)
            }
            opacity = 0.0
        }
    }
}
