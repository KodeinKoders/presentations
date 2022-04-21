package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.FontGrow
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.util.transition
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import utils.StrikeThrough
import kotlin.time.Duration.Companion.seconds


@Composable
private fun Entry(title: @Composable () -> Unit, code: String?, displayAt: Int, state: Int) {
    Li({
        css {
            transition { "opacity"(300.ms) }
        }
        style {
            if (state < displayAt) opacity(0)
        }
    }) {
        Span({
            css {
                fontSize(1.5.em)
            }
        }) {
            title()
        }
        if (code != null) {
            Div({
                css {
                    paddingLeft(2.em)
                    height(8.em)
                    overflow("hidden")
                    transition { "height"(300.ms) }
                    "pre" {
                        margin(.2.em)
                    }
                }
                style {
                    if (state != displayAt) height(0.em)
                }
            }) {
                SourceCode(lang = "kotlin", code)
            }
        }
    }
}

val sqlite = listOf(
    Slide(
        name = "sqlite",
        stateCount = 9
    ) { state ->
        H2({
            style { margin(0.em) ; padding(0.em) }
        }) {
            Span({
                style { fontWeight(100) }
                shownIf(state >= 8, FontGrow(1.seconds))
            }) {
                Text("No")
            }
            Text("SQL")
            Span({
                style { fontWeight(100) }
                shownIf(state < 8, FontGrow(1.seconds))
            }) {
                Text("ite")
            }
        }

        Ul({
            css {
                listStyleType("none")
            }
        }) {
            Entry(
                { Text("Models") },
                """
                    data class User(
                        val uid: Int,
                        val firstName: String,
                        val lastName: String
                        val address: Address?
                    )
                """.trimIndent(),
                1,
                state
            )

            Entry(
                { StrikeThrough(state >= 8) { Text("Structure") } },
                """
                    CREATE TABLE User(
                        uid INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                        firstName TEXT NOT NULL,
                        lastName TEXT NOT NULL,
                        addressId INT
                    );
                """.trimIndent(),
                2,
                state
            )

            Entry(
                { StrikeThrough(state >= 8) { Text("Creation") } },
                """
                    fun getDB() =
                        if (!db_exists("mydb.sql"))
                            create_db().also { initiate_schema(it) }
                        else
                            open_db("mydb.sql")
                """.trimIndent(),
                3,
                state
            )

            Entry(
                { StrikeThrough(state >= 8) { Text("Insertion") } },
                """
                    fun SqlDb.putUser(u: User) = execStatement(
                        ""${'"'}INSERT INTO Users
                        (id, firstName, lastName, addressId)
                        VALUES (?, ?, ?, ?)""${'"'},
                        u.id, u.firstName, u.lastName, u.address.id
                    )
                """.trimIndent(),
                4,
                state
            )

            Entry(
                { StrikeThrough(state >= 8) { Text("ORM") } },
                """
                    fun SQLResult.toUser() = User(
                        uid = getInt("uid")
                        firstName = getString("firstName"),
                        lastName = getString("lastName"),
                        address = db.getAddress(getString("addressId"))
                    )
                """.trimIndent(),
                5,
                state
            )

            Entry(
                { StrikeThrough(state >= 8) { Text("Query") } },
                """
                    fun SqlDb.getUserByLastName(lastName: String) =
                        execQuery("GET * FROM Users WHERE lastName = ?")
                        .map { it.toUser() }
                        .toList()
                """.trimIndent(),
                6,
                state
            )

            Entry({ StrikeThrough(state >= 8) { Text("...") } }, null, 7, state)

        }
    },

    Slide(
        name = "no-need-sql",
        stateCount = 2
    ) { state ->
        Div({
            css {
                border(.2.em, LineStyle.Solid, Color.white)
                borderWidth(0.em, 0.em, 0.em, .2.em)
                padding(.8.em)
            }
            style { textAlign("left") }
        }) {
            H4({
                style { textAlign("left") }
            }) {
                Text("« Most of the time, in mobile")
                Br()
                Text("or end-user applications,")
                Br()
                Text("SQL is not needed")
                Br()
                Text("for embedded data management »")
            }
            P({
                shownIf(state >= 1, fade)
            }) {
                Text("-- Salomon BRYS (me)")
            }
        }
    }
)