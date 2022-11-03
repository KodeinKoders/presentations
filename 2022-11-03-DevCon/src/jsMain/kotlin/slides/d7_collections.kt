package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp


val d7_collections = listOf(
    Slide(
        name = "Collections:Declaration",
        stateCount = 2
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Les collections sont optimisées pour la programmation fonctionnelle.")
        }
        Stamp(state == 1) { Text("Fallacieux !") }
    },
    Slide(
        name = "Collections:Code"
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            //language=kotlin
            code = """
                fun main() {
                    println(
                        measureTimeMillis {
                            println(
                                buildList {
                                    repeat(10_000) { add(Random.nextInt()) }
                                }
                                    .filter { it % 15 == 0 }
                                    .map { it / 15 }
                                    .sum()
                            )
                        }
                    )
                }
            """.trimIndent(),
            copyButton = true
        )
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
