package slides

import androidx.compose.runtime.Composable
import dbBlue
import net.kodein.pres.Slide
import net.kodein.pres.util.transition
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement


@Composable
private fun Cell(
    color: CSSColorValue,
    height: CSSNumeric = 2.em,
    shown: Boolean = true,
    content: @Composable () -> Unit
) {
    Div({
        css {
            flexGrow(1)
            flexBasis(0.em)
        }
    }) {
        Div({
            css {
                transition {
                    "height"(300.ms)
                    "opacity"(300.ms)
                }
                overflow("hidden")
            }
            style {
                height(if (shown) height else 0.em)
                opacity(if (shown) 1 else 0)
            }
        }) {
            Div({
                css {
                    padding(.125.em, 1.em)
                    margin(.25.em)
                    borderRadius(.2.em)
                    textAlign("start")
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    justifyContent(JustifyContent.Start)
                    alignItems(AlignItems.Center)
                    height(height - 0.75.em)
                    transition {
                        "background-color"(300.ms)
                    }
                }
                style {
                    backgroundColor(color)
                }
            }) {
                content()
            }
        }
    }
}

@Composable
private fun Flex(
    direction: FlexDirection,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    Div({
        css {
            display(DisplayStyle.Flex)
            flexDirection(direction)
        }
        attrs?.invoke(this)
    }) {
        content()
    }
}

@Composable
private fun Row(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) = Flex(FlexDirection.Row, attrs, content)

@Composable
private fun Column(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) = Flex(FlexDirection.Column, attrs, content)

@Composable
private fun SubColumn(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) = Flex(
    FlexDirection.Column,
    {
        css {
            flexGrow(1)
            flexBasis(0.em)
        }
        attrs?.invoke(this)
    },
    content
)

@Composable
private fun LayerCell(
    color: CSSColorValue,
    explain: String,
    shown: Boolean = true,
    content: @Composable () -> Unit
) {
    Cell(color, shown = shown) {
        Span({
            css {
                flexGrow(1)
            }
        }) {
            content()
        }
        Span({
            css {
                fontSize(0.9.em)
                fontWeight(200)
            }
        }) {
            Text(explain)
        }
    }
}

private val appGreen = Color("#89ab0a")

val layers = Slide(
    name = "layers",
    stateCount = 11
) { state ->
    Column({
        css {
            width(80.percent)
        }
    }) {
        Row {
            Cell(appGreen) {
                B { Text("Android & Desktop") }
            }
            Cell(appGreen) {
                B { Text("iOS & Native") }
            }
        }
        LayerCell(dbBlue, "Type safe DSL", state >= 1) {
            B { Text("DB API") }
        }
        LayerCell(KodeinColor.orange.css, "YOUR logic!", state >= 9) {
            Span {
                Text("Model ")
                B { Text("Middleware") }
            }
        }
        LayerCell(if (state < 10) dbBlue else KodeinColor.orange.css, "LRU cache optimization", state >= 3) {
            Text("Model ")
            B { Text("Cache") }
        }
        LayerCell(dbBlue, "Model ◄► Document & Metadata", state >= 2) {
            B { Text("Model") }
        }
        LayerCell(KodeinColor.orange.css, "YOUR logic!", state >= 8) {
            Span {
                Text("Data ")
                B { Text("Middleware") }
            }
        }
        LayerCell(dbBlue, "Document & index ◄► Key-Value", state >= 4) {
            B { Text("Data") }
        }
        LayerCell(KodeinColor.orange.css, "YOUR logic!", state >= 7) {
            Span {
                Text("Key-Value ")
                B { Text("Middleware") }
            }
        }
        LayerCell(dbBlue, "Multiplatform key-value store interface", state >= 5) {
            B { Text("Key-Value") }
        }
        Row {
            SubColumn {
                Cell(Color.dimgray, shown = state >= 6) {
                    B { Text("Kotlin JNI") }
                }
                Cell(Color.dimgray, shown = state >= 6) {
                    B { Text("C++ JNI") }
                }
            }
            Cell(Color.dimgray, 4.em, shown = state >= 6) {
                B {
                    Text("Kotlin/Native")
                    Br()
                    Text("C-Interop")
                }
            }
        }
        LayerCell(Color.darkslategray, "Data storage & file management", state >= 6) {
            Text("Google ")
            B { Text("LevelDB") }
        }
    }
}
