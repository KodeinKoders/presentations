package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.dom.*
import utils.errorUnder


val immutability = Slide(
    name = "immutability",
    stateCount = 4
) { state ->
    P({
        style {
            marginBottom(.3.em)
        }
    }) {
        Text("The ")
        B { Text("model cache") }
        Text(" needs you to enforce the following")
    }
    H3({
        style {
            marginTop(0.em)
        }
    }) {
        Span({ style { fontWeight(200) } }) { Text("Contract: ") }
        Text("Datum Immutability")
    }

    SourceCode("kotlin", """
        «p:println(userDB.get(uuid).name) // Jane

        »val user = userDB.get(uuid)
        «n:val newUser = »«u:user.«n:copy(»name = "John"«n:)»»
 
        «p:println(userDB.get(uuid).name) // «u:«o:John»«n:Jane»»

        »db.put(«o:user»«n:newUser»)
        
        «p:println(userDB.get(uuid).name) // John
        »
    """.trimIndent()) {
        "p" { lineHeight(state >= 1) }
        "o" { fontGrow(state < 3) }
        "n" { fontGrow(state >= 3) }

        "u" { errorUnder(state == 2) }
    }

}
