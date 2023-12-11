package slide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import org.kodein.pres.Slide
import org.kodein.pres.Slides
import org.kodein.pres.TransitionSet
import org.kodein.pres.copyWithInsideTransitions
import theme.*
import java.awt.Desktop
import java.net.URI


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
