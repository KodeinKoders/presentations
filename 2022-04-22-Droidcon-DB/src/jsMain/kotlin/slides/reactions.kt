package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text


val reactions = listOf(
    Slide(
        name = "reaction-local",
        stateCount = 5
    ) { state ->
        H3({ shownIf(state >= 4, fade) }) { Text("Local reaction") }

        SourceCode("kotlin", """
            usersDB.operation«f:Flow().collect {»
                when (it) {
                    is Operation.DidPut<User> -> {
                        println("Added a User: ${'$'}{it.model}.")
                        updateView()
                    }
                    
            «r:        is Operation.WillDelete<User> -> it.model()
            »        is Operation.DidDelete<User> -> {
                        println("Deleted a User«m:: ${'$'}{it.model()}».")
                        updateView()
                    }
                }
            }
        """.trimIndent()) {
            "f" { zoomed(state == 1) }
            "r" { lineHeight(state >= 3) }
            "m" { fontGrow(state >= 3) }
        }
    },

    Slide(
        name = "reaction-global",
        stateCount = 4
    ) { state ->
        H3({ shownIf(state >= 3, fade) }) { Text("Global reaction") }

        SourceCode("kotlin", """
            usersDB.operationFlow().collect {
                when (it) {
            «p:        is Operation.WillPut<User> -> {
                        require(it.model.name.isNotEmpty()
                    }
            »«d:        
                    is Operation.WillDelete<User> -> it.model()
                    is Operation.DidDelete<User> -> {
                        messagesDB.query { userId eq it.model().id }
                            .deleteAll()
                    }
            »    }
            }
        """.trimIndent()) {
            "p" { lineHeight(state >= 1) }
            "d" { lineHeight(state >= 2) }
        }
    },

    Slide(
        name = "reaction-option",
        stateCount = 4
    ) { state ->
        SourceCode("kotlin", """
            «cls:data class MessageContext(val fromNetwork: Boolean)
                : Options.Listeners
            
            »messagesDB.operationFlow().filterIsInstance<DidPut>()
                .collect {
            «get:        val context = it.options
                        .filterIsInstance<MessageContext>()
                        .firstOrNull()
                        
            »        if («con:context?.fromNetwork»«def:FROM_NETWORK») {
                        displayNotification(it.model)
                    }
                }
            }
            «put:
            usersDB.put(message, MessageContext(fromNetwork = true))
            »
        """.trimIndent()) {
            "cls" { lineHeight(state >= 1) }
            "get" { lineHeight(state >= 2) }
            "def" { fontGrow(state < 2) }
            "con" { fontGrow(state >= 2) }
            "put" { lineHeight(state >= 3) }
        }
    }

)
