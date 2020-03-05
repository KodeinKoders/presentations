package ws.slides

import kotlinx.css.*
import react.dom.p
import ws.comp.logo
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.utils.Background


private val infos = SlideInfos(
        containerStyle = {
            ".inner-container" {
                backgroundImage = Background.kodein
            }
        }
)

fun PresentationBuilder.kodeinKoders() = slide(infos) {
    attrs.style = {
        backgroundImage = Image("url('images/Kotlin_certified_training.svg')")
        backgroundRepeat = BackgroundRepeat.noRepeat
        backgroundPosition = "bottom 5% right 5%"
        backgroundSize = "20%"
    }

    logo(division = "Koders", href = "https://kodein.net", zoom = 1.0 ) {
        +"painless technology"
    }
}

