package slide

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.kodein.pres.Slide
import org.kodein.pres.Slides
import org.kodein.pres.TransitionSet
import org.kodein.pres.copyWithInsideTransitions
import theme.*
import utils.AnimatedSlideUpContent

@Composable
private fun TextBlock(
    text: String,
    color: Color
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(color)
            .padding(4.dp)
    )
}

@Composable
private fun MiddleText(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = fontWeight,
        modifier = Modifier.fillMaxWidth()
    )
}

private val KmpCompilers by Slide(
    stepCount = 3
) { step ->
    Text(
        text = "Kotlin/Multiplatform Code",
        color = Color.Kodein.Coral,
        textAlign = TextAlign.Center,
        fontSize = 1.4.em,
        fontFamily = FontFamily.Picon.Extended,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(Color.Kodein.Byzantium)
            .padding(8.dp)
    )
    Row {
        Column(Modifier.weight(1f)) {
            AnimatedVisibility(step >= 1) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Kotlin/\nAndroid", Color.Kodein.Byzantium)
                    MiddleText("=")
                    MiddleText(".aar", fontWeight = FontWeight.Bold)
                }
            }
            AnimatedVisibility(step >= 2) {
                Column(Modifier.weight(1f)) {
                    MiddleText("+")
                    TextBlock("Kotlin", Color(0xFF_A4_C6_39))
                    MiddleText("=\nApp\nAndroid")
                }
            }
        }
        Column(Modifier.weight(1f)) {
            AnimatedVisibility(step >= 1) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Kotlin/\niOS", Color.Kodein.Byzantium)
                    MiddleText("=")
                    MiddleText(".framework", fontWeight = FontWeight.Bold)
                }
            }
            AnimatedVisibility(step >= 2) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Swift", Color(0xFF_F1_51_39))
                    MiddleText("=\nApp\niOS")
                }
            }
        }
        Column(Modifier.weight(1f)) {
            AnimatedVisibility(step >= 1) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Kotlin/\nWeb", Color.Kodein.Byzantium)
                    MiddleText("=")
                    MiddleText(".js", fontWeight = FontWeight.Bold)
                }
            }
            AnimatedVisibility(step >= 2) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("TypeScript", Color(0xFF_32_79_C6))
                    MiddleText("=\nApp\nWeb")
                }
            }
        }
        Column(Modifier.weight(1f)) {
            AnimatedVisibility(step >= 1) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Kotlin/\nServer", Color.Kodein.Byzantium)
                    MiddleText("=")
                    MiddleText(".jar", fontWeight = FontWeight.Bold)
                }
            }
            AnimatedVisibility(step >= 2) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Spring", Color(0xFF_6C_B5_2C))
                    MiddleText("=\nApp\nServer")
                }
            }
        }
        Column(Modifier.weight(1f)) {
            AnimatedVisibility(step >= 1) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Kotlin/\nDesktop", Color.Kodein.Byzantium)
                    MiddleText("=")
                    MiddleText(".jar", fontWeight = FontWeight.Bold)
                }
            }
            AnimatedVisibility(step >= 2) {
                Column(Modifier.fillMaxWidth()) {
                    MiddleText("+")
                    TextBlock("Compose", Color(0xFF_19_19_1C))
                    MiddleText("=\nApp\nDesktop")
                }
            }
        }
    }
}

private val KmpCrossAndNative by Slide(
    stepCount = 2
) { step ->
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val anim by animateFloatAsState(if (step == 0) 1f else 0f, tween(2000))
        Box(
            Modifier
                .size(128.dp)
                .offset(x = -(84 * anim).dp)
                .rotate(45f * (anim * 2 - 1))
                .border((2 + (1 - anim) * 3).dp, Color.Kodein.Orange, RoundedCornerShape(24.dp))
        )
        Box(
            Modifier
                .size(128.dp)
                .offset(x = (84 * anim).dp)
                .rotate(45f * (anim * 2 - 1) * -1)
                .border((2 + (1 - anim) * 3).dp, Color.Kodein.Orange, RoundedCornerShape(24.dp))
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            CompositionLocalProvider(
                LocalTextStyle provides LocalTextStyle.current.copy(
                    fontSize = 2.em,
                    fontFamily = FontFamily.Picon.Extended,
                    fontWeight = FontWeight.Bold
                )
            ) {
                Text("Cross-Platform")
                AnimatedSlideUpContent(step, durationMillis = 2000) {
                    Text(if (it == 0) "OR" else "AND", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
                Row {
                    Text("Native")
                    AnimatedSlideUpContent(step, durationMillis = 2000, modifier = Modifier.width(20.dp)) {
                        Text(if (it == 0) "?" else "!")
                    }

                }
            }
        }
    }
}

private val kmpProgressive by Slide(
    stepCount = 6
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

    TBox(
        text = "Business Logic",
        progression = when (step) {
            0 -> 0f
            1 -> 0.15f
            2 -> 0.5f
            else -> 1f
        }
    )
    TBox(
        text = "Behaviour Logic",
        progression = when  {
            step < 4 -> 0f
            step == 4 -> 0.5f
            else -> 1f
        }

    )
    TBox(
        text = "User Interface",
        progression = 0f
    )
}

private val kmpCaseStudies by Slide {
    Title("Kotlin/Multiplatform Case Studies",)
    Spacer(Modifier.height(8.dp))
    URLText(
        url = "https://kotl.in/kmp-case-studies",
        style = TextStyle(fontSize = 1.2.em)
    )
}

val KotlinMultiplatform = VerticalSlides(
    KmpCompilers,
    KmpCrossAndNative,
    kmpProgressive,
    kmpCaseStudies
)
