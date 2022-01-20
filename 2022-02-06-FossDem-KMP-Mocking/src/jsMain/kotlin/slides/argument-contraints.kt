package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text


val argumentConstraints = Slide(
    name = "argument-constraints",
    stateCount = 6
) { state ->
    H3 { Text("Argument constraints") }

    SourceCode(
        lang = "kotlin",
        code = """
            every { view.render(«z:«any:isAny()»«notNull:isNotNull()»«42:42»«equal:isEqual(42)»«pos:isStrictlyPositive()»») } returns Unit
            «l:
            »//...
            «l:
            »verify { view.render(«z:«any:isAny()»«notNull:isNotNull()»«42:42»«equal:isEqual(42)»«pos:isStrictlyPositive()»») }
            

            «custom:fun ArgConstraintsBuilder.isStrictlyPositive(
                capture: MutableList<Int>? = null
            ): Int =
                isValid(ArgConstraint(capture, "isStrictlyPositive") {
                    if (it >= 0) ArgConstraint.Result.Success
                    else ArgConstraint.Result.Failure {
                        "Expected a strictly positive value, got ${'$'}it"
                    }
                })
            »
        """.trimIndent()
    ) {
        "z" { zoomed(state == 1) }
        "any" { fontGrow(state < 2) }
        "notNull" { fontGrow(state == 2) }
        "42" { fontGrow(state == 3) }
        "equal" { fontGrow(state == 4) }
        "pos" { fontGrow(state >= 5) }

        "l" { lineHeight(state < 5) }
        "custom" { lineHeight(state >= 5) }
    }
}