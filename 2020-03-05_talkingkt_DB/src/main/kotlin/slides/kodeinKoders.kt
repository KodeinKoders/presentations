package ws.slides

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import ws.comp.logo
import ws.kpres.Flip
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.notes


private val infos = SlideInfos(
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("rgba(0, 0, 0, 0)")
            }
        },
        outTransitions = Flip,
        notes = notes("""
            - Exclusivement Kotlin
            - Spécialisée sur Kotlin/Multiplatform et Kotlin/Native
            - Aspect business
        """)
)

fun PresentationBuilder.kodeinKoders() = slide(infos) {
    logo(division = "Koders", href = "https://kodein.net", zoom = 1.0 ) {
        +"painless technology"
    }
}

