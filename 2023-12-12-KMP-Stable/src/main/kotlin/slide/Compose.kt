package slide

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.kodein.pres.Slide
import org.kodein.pres.Slides
import theme.Kodein
import theme.Title
import theme.URLText
import theme.VerticalSlides


private val ComposeMultiplatform by Slide(
    stepCount = 3
) { step ->
    @Composable
    fun TBox(
        text: String,
        progression: Float
    ) {
        val weight by animateFloatAsState(progression, tween(600))
        val color by animateColorAsState(if (progression < 0.6f) Color.Kodein.Zinzolin else Color.Kodein.Light, tween(600))
        val alpha by animateFloatAsState(if (progression > 0.1) 1f else 0f, tween(600))
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(4.dp))
        ) {
            Row {
                Box(Modifier.weight(1f - weight + 0.000001f).fillMaxHeight().background(Color.Kodein.Light))
                Box(Modifier.weight(weight + 0.000001f).fillMaxHeight().background(Color.Kodein.Zinzolin))
            }
            Text(text, fontSize = 1.4.em, color = color, modifier = Modifier.padding(start = 16.dp))
            Image(
                painter = painterResource("kotlin.svg"),
                contentDescription = "Kotlin logo",
                colorFilter = ColorFilter.tint(Color.Kodein.Amethyst),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterEnd)
                    .alpha(alpha)
            )
        }
    }

    AnimatedVisibility(
        visible = step >= 1,
        enter = fadeIn(tween(600)) + expandVertically(tween(600), clip = false),
        exit = fadeOut(tween(600)) + shrinkVertically(tween(600), clip = false),
    ) {
        Title("Compose Multiplatform", Modifier.padding(bottom = 8.dp))
    }

    TBox(
        text = "Business Logic",
        progression = 1f
    )
    TBox(
        text = "Behaviour Logic",
        progression = 1f
    )
    TBox(
        text = "User Interface",
        progression = if (step == 0) 0f else 1f
    )

    AnimatedVisibility(
        visible = step >= 2,
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                    .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                    .padding(8.dp)
            ) {
                Text("Minimum Viable Product")
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .border(2.dp, Color.Kodein.Orange, RoundedCornerShape(4.dp))
                    .background(Color.Kodein.Rust, RoundedCornerShape(4.dp))
                    .padding(8.dp)
            ) {
                Text("Domain Specific Application")
            }
        }
    }
}

private val ComposeGetStarted by Slide {
    Title("Get started with\nKotlin/Multiplatform\n& Compose!",)
    Spacer(Modifier.height(8.dp))
    URLText(
        url = "https://kmp.jetbrains.com",
        style = TextStyle(fontSize = 1.2.em)
    )
}

val Compose = VerticalSlides(
    ComposeMultiplatform,
    ComposeGetStarted
)