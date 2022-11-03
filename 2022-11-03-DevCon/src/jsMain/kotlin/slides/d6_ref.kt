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


val d6_ref = listOf(
    Slide(
        name = "Ref:Declaration",
        stateCount = 3
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Le contenu d'une value class est inliné et n'a donc pas ")
            Br()
            Span({ shownIf(state < 2, Transitions.fontGrow) }) { Text("de référence") }
            Span({
                shownIf(state >= 2, Transitions.fontGrow)
                css { color(KodeinColor.korail.css) }
            }) { Text("d'identité") }
            Text(".")
        }
        Stamp(state == 1) { Text("Simplifié !") }
    },
    Slide(
        name = "Ref:Code"
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            //language=kotlin
            code = """
                @JvmInline value class Name(val name: String)
                
                fun printName(n: Name) { println(n) }
               
                fun printAny(a: Any) { println(a) }
                
                fun main() {
                    val n = Name("Salomon")
                    printName(n)
                    printAny(n)
                    printAny(listOf(Name("Salomon"), Name("Romain")))
                }
            """.trimIndent(),
            copyButton = true
        )
    },
    Slide(
        name = "Ref:Design",
    ) { state ->
        A(href = "https://github.com/Kotlin/KEEP/blob/master/notes/value-classes.md", {
            target(ATarget.Blank)
            css {
//                color(KodeinColor.kamethiste.css)
                fontSize(2.em)
            }
        }) {
            Text("https://github.com/Kotlin/KEEP/")
            Br()
            Text("blob/master/notes/value-classes.md")
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
