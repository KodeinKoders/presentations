package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.css
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.kn3() = slide(stateCount = 3) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"K/N part 3: "
        }
        s {
            +"Test!"
        }
    }

    slideCode(props.state, "bash", """
        ./gradlew linuxX64Test
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

    br {}

    slideCode(props.state, "bash", """
        ./gradlew iosX64Test
    """.trimIndent()) {
        opacity(props.state >= 2)
        transform(props.state < 2) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.8.em
        }
    }

    br {}

}
