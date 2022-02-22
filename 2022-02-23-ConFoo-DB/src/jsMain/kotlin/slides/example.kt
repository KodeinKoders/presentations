package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text


val example = listOf(
    Slide(
        name = "sample-model",
        stateCount = 4,
    ) { state ->
        H2 { Text("Define your models") }

        SourceCode("kotlin", """
            «ser:@Serializable»
            data class User(
                «id:@Id »val uid: UUID,
                val firstName: String,
                «idx:@Indexed »val lastName: String
            )
        """.trimIndent()) {
            "id" { fontGrow(state >= 1) }
            "idx" { fontGrow(state >= 2) }
            "ser" { fontGrow(state >= 3) }
        }
    },

    Slide(
        name = "sample-modelusage",
        stateCount = 7
    ) { state ->
        H2 { Text("Use the DataBase") }

        SourceCode("kotlin", """
            val db = DB.open("mydb")
            val usersDB = db.of(«gen:D.User»)
            «create:
            val uuid = UUID.randomUUID()
            usersDB.put( User(uuid, "Salomon", "BRYS") )
            »«get:
            val user = usersDB.getById(uuid)
            »«query:
            val allBrys = usersDB.query { «dsl:lastName eq "BRYS"» }
                .useToModelList()
            »
        """.trimIndent()) {
            "gen" { zoomed(state == 1) }
            "create" { lineHeight(state >= 2) }
            "get" { lineHeight(state >= 3) }
            "query" { lineHeight(state >= 4) }
            "dsl" { zoomed(state == 5) }
        }
    },
)
