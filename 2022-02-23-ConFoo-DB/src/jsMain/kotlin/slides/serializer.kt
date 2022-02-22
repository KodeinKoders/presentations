package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.*


val definition = listOf(
    Slide(
        name = "definition",
        stateCount = 5
    ) { state ->

        Div {
            SourceCode("kotlin", """
                «ser:@Serializable»
                data class User(
                    «id:@Id val uid: UUID»,
                    val firstName: String,
                    «idx:@Indexed val lastName: String»
                )
            """.trimIndent()) {
                "id" { zoomed(state == 1) }
                "idx" { zoomed(state == 2) }
                "ser" { zoomed(state == 3) }
            }

            Br()

            SourceCode("kotlin", """
                «id:object UserDef : ModelDefinition<User, UUID>(
                    getId = { uid },
                «ser:    serializer = User.serializer(),
                »    type = User::class
                ) «idx-f:{»
                »«idx-l:    val lastName by index { lastName }
                }
                »
            """.trimIndent()) {
                "id" { lineHeight(state >= 1) }
                "idx-l" { lineHeight(state >= 2) }
                "idx-f" { fontGrow(state >= 2) }
                "ser" { lineHeight(state >= 3) }
            }
        }

    },

    Slide(
        name = "cbor"
    ) {
        H2 { Text("CBOR") }
        P { Text("Concise Binary Object Representation (Rfc 7049)") }
        P({
            style { fontWeight(100) }
        }) { Text("(= binary JSON)") }
    }
)