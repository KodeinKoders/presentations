package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text


val kodeinDI = Slide(
    name = "kodein-di",
    stateCount = 4
) { state ->
    H2 { Text("Kodein-DI") }

    SourceCode(
        lang = "kotlin",
        """
            val di = DI {
                bind«z1:Singleton»<DB> { openDB() }
                bind«z1:Provider»<Controller> { controller(instance()) }
            }
            
            val controller: Controller «z2:by di.instance()»
        """.trimIndent()
    ) {
        "z1" { zoomed(state == 1) }
        "z2" { zoomed(state == 2) }
    }
}
