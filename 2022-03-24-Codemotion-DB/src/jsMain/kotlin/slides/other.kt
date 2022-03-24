package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.dom.*


val other = listOf(
    Slide(
        name = "atomicity"
    ) {
        Div({
            css {
                fontSize(1.25.em)
            }
        }) {
            H2 {
                Span({
                    css { fontWeight(100) }
                }) { Text("Also: ") }
                Text("Atomicity")
            }

            P { Text("A snapshot ensures that the queried data set reflects the state it was when created.") }

            P { Text("A batch modifies the database \"atomically\".") }

            P { Text("Therefore, a snapshot can never reflect part of a batch modification.") }

            P { Text("By the way, cursors use snapshots.") }
        }
    },

    Slide(
        name = "testing"
    ) {
        Div({
            css {
                fontSize(1.25.em)
            }
        }) {
            H2 {
                Span({
                    css { fontWeight(100) }
                }) { Text("Also: ") }
                Text("In-Memory testing")
            }

            P { Text("Implements the Key-Value DB with an in-memory HashMap.") }

            P { Text("Performance degrades fast with size!") }

            P { Text("Very quick and easy to set-up.") }
        }
    },

    Slide(
        name = "Encryption"
    ) {
        Div({
            css {
                fontSize(1.25.em)
            }
        }) {
            H2 {
                Span({
                    css { fontWeight(100) }
                }) { Text("Also: ") }
                Text("Encryption")
            }

            P { Text("Encrypts document blob content.") }

            P { Text("Hashes IDs & indexes. Ordering is therefore lost.") }

            P { Text("Indexes names & list of index by document are left clear.") }
        }
    },

    Slide(
        name = "next",
        stateCount = 2
    ) { state ->
        Div({
            css {
                "li" { fontSize(1.4.em) }
            }
        }) {
            H2 { Text("Next...") }

            Ul {
                Li { Text("Migration API") }
                Li { Text("Full Text Search") }
                Li { Text("Coroutines support") }
            }

            H2({ shownIf(state >= 1, fade) }) { Text("...and...") }

            Ul({ shownIf(state >= 1, fade) }) {
                Li { Text("Data export / visualisation") }
                Li { Text("IndexedDB JS support") }
                Li { Text("True compiler plugin") }
            }
        }
    }
)
