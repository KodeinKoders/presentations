import androidx.compose.ui.graphics.Color
import org.kodein.pres.Slides
import org.kodein.pres.desktop.cupApplication
import slide.*
import theme.KodeinPresentation
import theme.KodeinSlides
import theme.closingSlide
import theme.openingSlide


data class KodeinPresentationBackground(
    val color: Color,
) {
    companion object Key
}



private val slides = Slides(
    openingSlide(
        title = "Kotlin/Multiplatform Stable!",
        location = "Kotlin Meetup Paris",
        date = "12 / 12 / 2023",
        speaker = "Salomon Brys & Romain Boisselle",
        gde = true
    ),
    KotlinMultiplatform,
    Ecosystem,
    Stable,
    SwiftViaObjc,
    Compose,
    FleetAndCompose,
    KodeinSlides,
    closingSlide(
        location = "Kotlin Meetup Paris",
        date = "12 / 12 / 2023",
        speaker = "Salomon Brys & Romain Boisselle",
        email = "salomon@kodein.net - romain@kodein.net"
    )
)

fun main(): Unit = cupApplication(
    title = "2023-12-12-KMP-Stable",
) {
    KodeinPresentation(slides)
}
