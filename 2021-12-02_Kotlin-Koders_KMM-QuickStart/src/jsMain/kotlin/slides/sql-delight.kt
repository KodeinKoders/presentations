package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Hr
import org.jetbrains.compose.web.dom.Text


val sqlDelight = Slide(
    name = "sql-delight",
    stateCount = 2,
) { state ->
    H2 { Text("SQL-Delight") }

    SourceCode(
        lang = "text",
        """
            selectAll:
            SELECT * FROM hockeyPlayer;

            insert:
            INSERT INTO hockeyPlayer(number, name) VALUES (?, ?);
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
}
