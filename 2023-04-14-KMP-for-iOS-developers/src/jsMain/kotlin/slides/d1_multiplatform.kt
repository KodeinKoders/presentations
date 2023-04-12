package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transition
import net.kodein.pres.Transitions.Fade
import net.kodein.pres.Transitions.fade
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.animatedWith
import net.kodein.pres.shownIf
import net.kodein.pres.util.zIndex
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import slides.Dir.L
import slides.Dir.R
import kotlin.time.Duration.Companion.seconds


class LayerStack(private val layerAttrs: AttrBuilderContext<HTMLDivElement>? = null) {
    @Composable
    fun Layer(attrs: AttrBuilderContext<HTMLDivElement>? = null, content: @Composable () -> Unit) {
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
                margin(0.25.em)
                padding(0.5.em)
            }
            layerAttrs?.invoke(this)
            attrs?.invoke(this)
        }) {
            content()
        }
    }
}

@Composable
private fun LayerStack(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    layerAttrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable LayerStack.() -> Unit
) {
    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            margin(0.5.em)
        }
        attrs?.invoke(this)
    }) {
        LayerStack(layerAttrs).content()
    }
}

@Composable
fun LayerLogo(
    img: String,
    attrs: AttrBuilderContext<HTMLImageElement>? = null
) {
    Img(src = "img/$img") {
        css {
            width(6.em)
            height(4.em)
            property("object-fit", "contain")
            property("object-position", "bottom")
            alignSelf(AlignSelf.Center)
            margin(0.25.em)
            transitions {
                "opacity" { duration = 1.s }
                "transform" { duration = 0.8.s }
            }
        }
        attrs?.invoke(this)
    }
}

private enum class Dir(val m: Int) { L(-1), R(1) }

// Platform Layer Transition
private fun plt(d: Dir) = object : Transition {
    override val cssTransitions: org.jetbrains.compose.web.css.Transitions.() -> Unit = {
        "opacity" { duration = 1.s }
        "transform" { duration = 1.s }
    }
    override val hiddenStyle: StyleScope.() -> Unit = {
        transform {
            translateX(5.em * d.m)
        }
        opacity(0)
    }
}

// Kotlin Layer Transition
private val klt = object : Transition {
    override val cssTransitions: Transitions.() -> Unit = {
        "opacity" { duration = 1.s }
        "transform" { duration = 1.s }
    }
    override val hiddenStyle: StyleScope.() -> Unit = {
        transform {
            scale(0.8)
        }
        opacity(0)
    }
}

val d1_multiplatform = listOf(
    Slide(
        name = "multiplatform-layers",
        stateCount = 7
    ) { state ->
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                width(80.percent)
                position(Position.Relative)
            }
        }) {
            LayerStack(
                attrs = {
                    css { flex(1) }
                },
                layerAttrs = {
                    css {
                        backgroundColor(Color("#3ddb85"))
                        color(Color.black)
                        zIndex(2)
                    }
                }
            ) {
                LayerLogo("Android.png") { css { if (state < 6) transform { translateY(2.8.em) } } }
                Layer({ shownIf(state >= 6, plt(L)) }) { Text("Integration") }
                Layer({ shownIf(state != 4, plt(L)) }) { Text(if (state < 5) "UI" else "Jetpack Compose") }
                Layer({ shownIf(state < 3, plt(L)) }) { Text("Behaviour") }
                Layer({ shownIf(state < 1, plt(L)) }) { Text("Business") }
                Layer({ shownIf(state < 2, plt(L)) }) { Text("Input / Output") }
            }
            LayerStack(
                attrs = {
                    css { flex(1) }
                },
                layerAttrs = {
                    css {
                        backgroundColor(Color("#c2c2c2"))
                        color(Color.black)
                        zIndex(2)
                    }
                }
            ) {
                LayerLogo("Apple.png") { css { if (state < 6) transform { translateY(2.8.em) } } }
                Layer({ shownIf(state >= 6, plt(R)) }) { Text("Integration") }
                Layer({ shownIf(state != 4, plt(R)) }) { Text(if (state < 5) "UI" else "SwiftUI") }
                Layer({ shownIf(state < 3, plt(R)) }) { Text("Behaviour") }
                Layer({ shownIf(state < 1, plt(R)) }) { Text("Business") }
                Layer({ shownIf(state < 2, plt(R)) }) { Text("Input / Output") }
            }

            LayerStack(
                attrs = {
                    css {
                        position(Position.Absolute)
                        width(100.percent)
                        top(0.px)
                        left(0.px)
                        marginLeft(0.px)
                        marginRight(0.px)
                    }
                },
                layerAttrs = {
                    css {
                        backgroundColor(Color("#7F52FF"))
                        color(Color.white)
                        width(80.percent)
                        alignSelf(AlignSelf.Center)
                        zIndex(1)
                    }
                }
            ) {
                LayerLogo("kotlin.svg") {
                    css {
                        if (state < 1) opacity(0)
                        if (state < 6) transform { translateY(2.8.em) }
                    }
                }
                Layer({ css { opacity(0) } }) { Text("Integration") }
                Layer({ shownIf(state == 4, klt) }) { Text("Compose Multiplatform!") }
                Layer({ shownIf(state >= 3, klt) }) { Text("Behaviour (MVI)") }
                Layer({ shownIf(state >= 1, klt) }) { Text("Business") }
                Layer({ shownIf(state >= 2, klt) }) { Text("Input / Output (Libs)") }
            }
        }
    },
    Slide(
        name = "title-clarification",
        stateCount = 2
    ) { state ->
        H1({
            style {
                fontSize(2.1.em)
            }
        }) {
            Text("Kotlin/Multiplatform")
            Br()
            Text("for iOS develop")
            Span({
                shownIf(state >= 1, fontGrow)
                css { color(KodeinColor.orange.css) }
            }) { Text("ers") }
            Span({ shownIf(state < 1, fontGrow) }) { Text("ment") }
        }
    },
    Slide(
        name = "objectives",
        stateCount = 5
    ) { state ->
        P({ css { fontSize(1.8.em) } }) {
            Text("What can ")
            Span({ css { color(KodeinColor.orange.css) } }) {
                Text("we, as KMM developers")
            }
            Text(",")
            Br()
            Text("do to improve...")

            Ul({
                css {
                    width(16.em)
                }
            }) {
                Li({ shownIf(state >= 1, fontGrow) }) { Text("...the API exposed to...") }
                Li({ shownIf(state >= 2, fontGrow) }) { Text("...communication with...") }
                Li({ shownIf(state >= 3, fontGrow) }) { Text("...deployment of binaries to...") }
                Li({ shownIf(state >= 4, fontGrow) }) { Text("...mutual project engagement with...") }
            }

            Span({ shownIf(state >= 1, fade) }) {
                Text("...iOS developers?")
            }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
