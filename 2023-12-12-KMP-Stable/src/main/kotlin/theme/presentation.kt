package theme

import KodeinPresentationBackground
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.kodein.pres.LocalPresentationState
import org.kodein.pres.Presentation
import org.kodein.pres.SlideGroup


@Composable
fun KodeinPresentation(
    slides: SlideGroup
) {
    Presentation(
        slides = slides,
        modifier = Modifier
            .background(Color.Kodein.Dark)
    ) {
        Image(
            painter = painterResource("logo-bg.svg"),
            contentDescription = null,
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (-16).dp, y = 64.dp)
        )
        val background by animateColorAsState(
            targetValue = (LocalPresentationState.current.currentSlide.user[KodeinPresentationBackground] as? KodeinPresentationBackground)?.color ?: Color.Transparent,
            animationSpec = tween(2_000)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
        ) {
            CompositionLocalProvider(
                LocalContentColor provides Color.Kodein.Light,
                LocalTextStyle provides LocalTextStyle.current.copy(fontFamily = FontFamily.Picon.Regular)
            ) {
                Slides()
            }
        }
    }
}