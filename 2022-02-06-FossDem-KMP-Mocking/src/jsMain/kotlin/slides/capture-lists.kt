package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text


val captureLists = Slide(
    name = "capture-list",
    stateCount = 4
) { state ->
    H3 { Text("Capture Lists") }

    SourceCode(
        lang = "kotlin",
        code = """
            «list2:val sessions = ArrayList<Session>()
            »mocker
                .every { delegate.session(isAny(«cap2:capture = sessions»)) }
                .returns(Unit)

            controller.startNewSession()

            «list1:val sessions = ArrayList<Session>()
            »«ver:mocker.verify { delegate.session(isAny(«cap1:capture = sessions»)) }
            
            »«close:val session = sessions.single()
            session.close()
            »
        """.trimIndent()
    ) {
        "list1" { lineHeight(state in 1..2) }
        "cap1" { fontGrow(state in 1..2) }
        "close" { lineHeight(state >= 2) }

        "ver" { lineHeight(state < 3) }

        "list2" { lineHeight(state >= 3) }
        "cap2" { fontGrow(state >= 3) }
    }
}