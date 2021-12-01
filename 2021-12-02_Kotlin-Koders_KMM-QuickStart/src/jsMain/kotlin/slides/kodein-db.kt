package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text


val kodeinDB = Slide(
    name = "kodein-db",
    stateCount = 3
) { state ->
    H2 { Text("Kodein-DB") }

    SourceCode(
        lang = "kotlin",
        """
            «z:@Serializable»
            data class User(«z:override val id»: Int,
                val firstName: String, val lastName: String
            ) {
                «z:override fun indexes()» = mapOf("lastName" to lastName)
            }
            
            val db = DB.open("path/to/db")

            db.put(User("Jane", "Doe"))
            db.put(User("Someone", "Else"))

            val does = db.find<User>().byIndex("lastName", "Doe").models()
        """.trimIndent()
    ) {
        "z" { zoomed(state == 1) }
    }
}
