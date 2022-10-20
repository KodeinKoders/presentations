package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.util.transition
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.kodein.cic.css


val sqlDelight = listOf(
    Slide(
        name = "sql-delight",
        stateCount = 2,
    ) { state ->
        H2 { Text("SQL-Delight") }

        SourceCode(
            lang = "text",
            """
                selectAll:
                SELECT * FROM breweries;
    
                insert:
                INSERT INTO breweries(id, name, type) VALUES (?, ?, ?);
            """.trimIndent()
        )

        Div({ shownIf(state >= 1, fade) }) {
            Hr {
                css {
                    width(100.percent)
                }
            }

            SourceCode(
                lang = "kotlin",
                """
                    val playerQueries: PlayerQueries = database.playerQueries
                    println(playerQueries.selectAll().executeAsList())
                    playerQueries.insert(number = 10, name = "Corey Perry")
                """.trimIndent()
            )
        }
    },

    Slide(
        name = "sql-problems",
        stateCount = 2
    ) { state ->
        H2 { Text("Is SQL a delight?") }

        Ul({
            css {
                fontSize(1.4.em)
                "li" {
                    transition { "opacity"(.3.s) }
                }
                "li.bad" {
                    opacity(0.3)
                }
            }
        }) {
            Li({ if (state >= 1) classes("bad") }) { Text("Pre-define structure & queries") }
            Li({ if (state >= 1) classes("bad") }) { Text("Generate model classes & query functions") }
            Li({ if (state >= 1) classes("bad") }) { Text("Handle database & schema creation") }
            Li { Text("Use models & database!") }
        }
    }
)
