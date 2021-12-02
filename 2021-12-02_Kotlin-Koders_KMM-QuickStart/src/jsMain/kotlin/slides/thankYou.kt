package slides

import net.kodein.pres.Slide
import net.kodein.pres.util.transformOrigin
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.KodeinStyle
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


private object Rotation : StyleSheet() {
    private val rot by keyframes {
        from {
            transform { rotate(0.deg) }
        }
        to {
            transform { rotate(360.deg) }
        }
    }
    val anim by style {
        transformOrigin("41.8% 41.8%")
        animation(rot) {
            duration(10.s)
            iterationCount(null)
            timingFunction(AnimationTimingFunction.Linear)
        }
    }
}

val thankYou = Slide(
    name = "thank-you"
) {
    Style(Rotation)

    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
            width(100.percent)
            height(100.percent)
        }
    }) {
        Div({
            css {
                flex(1)
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
            }
        }) {
            H1 {
                Text("Thank You!")
            }

            P({
                css {
                    margin(0.1.em, 0.em, 1.em, 0.em)
                    fontWeight(200)
                }
            }) {
                Text("Kotlin KODERS - 02/12/2021")
            }

            P({
                css {
                    fontSize(1.5.em)
                    color(KodeinColor.kaumon.css)
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    alignItems(AlignItems.Center)
                }
            }) {
                Span({
                    css {
                        paddingTop(0.3.em)
                        marginRight(2.em)
                        fontWeight(300)
                    }
                }) {
                    Text("Salomon BRYS")
                }
                A(href = "https://kodein.net") {
                    Logo(KodeinColor.kaumon) { Text("Koders") }
                }
            }

            A(href = "https://kodeinkoders.github.io/presentations/2021-12-02_Kotlin-Koders_KMM-QuickStart/", {
                css {
                    paddingTop(2.em)
                    fontWeight(200)
                }
            }) {
                Text("https://kodeinkoders.github.io/presentations/")
                Br {}
                Text("2021-12-02_Kotlin-Koders_KMM-QuickStart/")
            }
        }

        Div({
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                padding(0.em, 0.em, 0.em, 1.em)
                alignItems(AlignItems.Center)
                justifyContent(JustifyContent.Center)
            }
        }) {
            H1({
                classes(Rotation.anim)
                style {
                    position(Position.Relative)
                    color(KodeinColor.orange.css)
                    fontWeight(700)
                    fontSize(12.em)
                    margin(0.em)
                }
            }) {
                Text("K")
            }
        }
    }
}