package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.notes
import org.kodein.kpres.sourceCode


fun PresentationBuilder.simpleUsage() = slide(
        stateCount = 8,
        notes = notes {
            0 ("Une data class toute simple")
            2 ("ID String donc UUID")
            4 ("Comment indiquer à la librairie quel champs est l'ID ?")
            5 ("Base de données NoSQL = indexes explicites")
            6 ("Plusieurs utilisateurs peuvent avoir la même addresse")
        }
) { props ->

    sourceCode(
            "kotlin",
            """
                data class User(
                    «ano1«@Id »val uid: String,
                    val firstName: String,
                    «ano2«@Indexed("lastName") »val lastName: String
                    val address: «key«Key<»Address«key«>»?
                )«opn«
    
                val db = DB.open("mydb")»«put«
                
                val user = User(UUID.randomUUID(),
                    "Salomon", "BRYS", Address("near Paris"))
                val key = db.put(user)»«get«

                val key = db.newKey<User>("01234567-89ab-cdef...")
                val user = db[key]»«fin«

                val allBrys = db.find<User>()
                        .byIndex("lastName", "BRYS")
                        .models().toList()»
            """.trimIndent()
    ) {
        "code" {
            overflow = Overflow.hidden
        }

        "span.c-marker" {
            opacity = 1.0
            transition(::opacity, 300.ms)
            transition(::lineHeight, 300.ms)
            fun CSSBuilder.minState(min: Int) {
                opacity = if (props.state < min) 0.0 else 1.0
                lineHeight = LineHeight(if (props.state < min) "0" else "1.2")
            }
            +"c-opn" { minState(1) }
            +"c-put" { minState(2) }
            +"c-get" { minState(3) }
            +"c-fin" { minState(4) }
            +"c-ano1" {
                fontSize = 1.em
                transition(::fontSize, 300.ms)
                if (props.state < 5) fontSize = 0.em
                fontWeight = FontWeight.w500
                universal { color = Color.white }
                verticalAlign = VerticalAlign.middle
            }
            +"c-ano2" {
                fontSize = 1.em
                transition(::fontSize, 300.ms)
                if (props.state < 6) fontSize = 0.em
                fontWeight = FontWeight.w500
                universal { color = Color.white }
                verticalAlign = VerticalAlign.middle
            }
            +"c-key" {
                fontSize = 1.em
                transition(::fontSize, 300.ms)
                if (props.state < 7) fontSize = 0.em
                fontWeight = FontWeight.w500
                color = Color.white
                verticalAlign = VerticalAlign.middle
            }
        }
    }
}
