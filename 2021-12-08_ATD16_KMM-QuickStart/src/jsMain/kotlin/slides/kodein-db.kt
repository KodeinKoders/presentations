package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.*


val kodeinDB = listOf(
    Slide(
        name = "kodein-db",
        stateCount = 5
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
                
                «open:val db = DB.open("path/to/db")
                »
                «put:db.put(User("Jane", "Doe"))
                db.put(User("Someone", "Else"))
                »
                «query:val does = db.find<User>().byIndex("lastName", "Doe").models()
                »
            """.trimIndent()
        ) {
            "z" { zoomed(state == 1) }
            "open" { lineHeight(state >= 2) }
            "put" { lineHeight(state >= 3) }
            "query" { lineHeight(state >= 4) }
        }
    },

    Slide(
        name = "kodein-db-state",
        stateCount = 2
    ) { state ->
        H3 { Text("Kodein-DB BETA supports...") }

        P {
            Text("NoSQL document operations, Indexing, Composite keys & indexes, Write batches, Object LRU cache, ")
            B { Text("Single Source of Truth reactive streams") }
            Text(".")
        }

        Div({ shownIf(state >= 1, fade) }) {
            H3 { Text("Kodein-DB WILL supports...") }

            P {
                Text("Migration API, Coroutines support, Compiler plugin, IndexedDB JS support, Cloud sync, Data export / visualisation, Full text search")
            }
        }
    }
)
