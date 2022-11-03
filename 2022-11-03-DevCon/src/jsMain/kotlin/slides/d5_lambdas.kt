package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp


val d5_Lambdas = listOf(
    Slide(
        name = "Lambdas:Declaration",
        stateCount = 2
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Les lambdas permettent de créer facilement des objets fonctions")
        }
        Stamp(state == 1) { Text("Généralisé !") }
    },
    Slide(
        name = "Lambdas:Code"
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            //language=kotlin
            code = """
                fun <T> Iterable<T>.myForEach(action: (T) -> Unit) {
                    for (element in this) action(element)
                }

                fun foo() {
                    val list = listOf(1, 2, 3)
                    list.myForEach { println(it) }
                    list.forEach { println(it) }
                }
            """.trimIndent(),
            copyButton = true
        )
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
