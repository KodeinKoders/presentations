package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Dimmed
import utils.Stamp
import utils.dimmed


val d4_kotlinPoet = listOf(
    Slide(
        name = "kotlinPoet",
        stateCount = 3
    ) { state ->
        H4 {
            Text("KotlinPoet is a Kotlin and Java API for generating .kt source files.")
        }

        Div({ shownIf(state >= 1, Transitions.fade) }) {
            SourceCode("kotlin", """
            FunSpec.builder("hello")
              .addParameter("name", String::class)
              .addStatement("println(%P)", "Hello, \${'$'}name!")
              .build()
        """.trimIndent())
        }

        Div({ shownIf(state >= 2, Transitions.grow) }) {
            SourceCode("kotlin", """
                fun hello(name: String) {
                  println("hello, ${'$'}name!")
                }
            """.trimIndent())
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
