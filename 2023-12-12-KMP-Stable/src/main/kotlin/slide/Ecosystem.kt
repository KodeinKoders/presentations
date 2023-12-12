package slide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.kodein.pres.Slide
import theme.Title
import theme.URLText
import theme.VerticalSlides


private val EcoLibraries by Slide {
    Title("Multiplatform libraries")
    Spacer(Modifier.height(8.dp))
    Image(
        painter = painterResource("libraries.png"),
        contentDescription = "1500 libraries!",
        modifier = Modifier.fillMaxWidth().height(200.dp)
    )
}

private val EcoAwesome by Slide {
    Title("KMP-Awesome")
    Spacer(Modifier.height(8.dp))
    URLText(
        url = "https://github.com/terrakok/kmp-awesome",
        style = TextStyle(fontSize = 1.2.em)
    )
}

val Ecosystem = VerticalSlides(
    EcoLibraries,
    EcoAwesome
)
