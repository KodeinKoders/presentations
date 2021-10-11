package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.shownIf
import net.kodein.pres.util.d
import net.kodein.theme.compose.pres.KodeinFrameworkComponent
import net.kodein.theme.compose.pres.kodeinFrameworkSlide
import net.kodein.theme.compose.pres.kodeinKodersSlide
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlin.time.Duration


val kodeinKoders = kodeinKodersSlide()

val kodeinFramework = kodeinFrameworkSlide(
    component = KodeinFrameworkComponent(
        name = "DI",
        color = Color("#EF822B")
    )
)
