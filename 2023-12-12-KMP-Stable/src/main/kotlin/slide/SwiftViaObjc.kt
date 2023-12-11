package slide

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.zIndex
import org.kodein.pres.Slide
import theme.Kodein
import theme.URLText


val SwiftViaObjc by Slide(
    stepCount = 4
) { step ->
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Image(
                    painter = painterResource("kotlin.svg"),
                    contentDescription = "Kotlin",
                    colorFilter = ColorFilter.tint(Color.Kodein.Purple),
                    modifier = Modifier.height(92.dp).zIndex(2f).padding(end = 16.dp)
                )

                AnimatedVisibility(
                    visible = step == 0 || step >= 3,
                    enter = fadeIn() + expandHorizontally(expandFrom = Alignment.CenterHorizontally, clip = false),
                    exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.CenterHorizontally, clip = false)
                ) {
                    Image(
                        painter = painterResource("objc.svg"),
                        contentDescription = "Objective-C",
                        modifier = Modifier.height(92.dp).zIndex(1f)
                    )
                }

                Image(
                    painter = painterResource("swift.svg"),
                    contentDescription = "Swift",
                    modifier = Modifier.height(92.dp).zIndex(2f).padding(start = 16.dp)
                )
            }

            AnimatedVisibility(step >= 3) {
                URLText(
                    url = "https://kotl.in/interopedia",
                    style = TextStyle(fontSize = 1.2.em),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        val progress by animateFloatAsState(if (step == 2) 1f else 0f, tween(600, easing = FastOutLinearInEasing))
        Text(
            text = "WORK IN PROGRESS!",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 4.em,
                fontWeight = FontWeight.ExtraBold,
                shadow = Shadow(color = Color.Kodein.Dark, blurRadius = 8f)
            ),
            modifier = Modifier
                .rotate(-36f * progress)
                .scale(1 + (1 - progress))
                .alpha(progress)
        )

    }
}
