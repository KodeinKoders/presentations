package ws.slides

import kotlinx.css.opacity
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import kotlinx.css.transition
import react.dom.h1
import styled.css
import styled.styledH1
import styled.styledSpan
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes


private val infos = SlideInfos(
        stateCount = 3,
//        notes = notes("""
//            OK, so, back to the title of this talk.
//
//            I feel that there's a keyword staring at us, and that I need to address the reason of its presence before going further, and that of course id the **NoSQL keyword**.
//
//            [1]
//            However, even before addressing why (spoiler alert) I think that NoSQL is the way to go in mobile embedded databases, I need to address my frustrations of **SQL**.
//
//            [2]
//            So, let's talk about **SQLite**.
//            .
//        """.trimIndent())
        notes = notes {
            0 ("Préambule : titre, expliquer...")
            1..2 ("...No**SQL**")
        }
)

fun PresentationBuilder.noSQL() = slide(infos) { props ->
    h1 {
        styledSpan {
            key = "1"
            css {
                if (props.state >= 1) opacity = 0.1
                transition(::opacity, 300.ms)
            }
            +"Embedded "
        }
        styledSpan {
            key = "2"
            css {
                if (props.state >= 2) opacity = 0.1
                transition(::opacity, 300.ms)
            }
            +"No"
        }
        +"SQL"
        styledSpan {
            key = "3"
            css {
                if (props.state >= 1) opacity = 0.1
                transition(::opacity, 300.ms)
            }
            +" data persistence, everywhere!"
        }
    }
}
