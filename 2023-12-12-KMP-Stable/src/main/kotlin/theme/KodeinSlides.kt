package theme

import KodeinPresentationBackground
import androidx.compose.animation.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kodein.pres.Slide
import org.kodein.pres.Slides
import org.kodein.pres.copyWithInsideTransitions


@Composable
private fun KodeinLogo(
    department: String,
    color: Color,
    description: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource("kodein-logo.svg"),
            contentDescription = "Kodein Koders logo",
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .height(72.dp)
                .padding(end = 8.dp)
        )
        Column {
            Row(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(
                    text = "KODEIN",
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    color = color
                )
                Text(
                    text = department,
                    fontWeight = FontWeight.Light,
                    fontSize = 38.sp,
                    color = color
                )
            }
            CompositionLocalProvider(
                LocalTextStyle provides LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    color = color,
                )
            ) {
                Box(Modifier.offset(y = (-8).dp)) {
                    description()
                }
            }
        }
    }
}

private val KodeinKoders by Slide(
    stepCount = 2
) { step ->
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        KodeinLogo(
            department = "KODERS",
            color = Color.Kodein.Orange,
            description = { Text("painless mobile technology") }
        )
    }

    @Composable
    fun Activity(
        title: String,
        subtitle: String
    ) {
        Column {
            Row {
                Image(
                    painter = painterResource("kotlin.svg"),
                    contentDescription = "Kotlin",
                    colorFilter = ColorFilter.tint(Color.Kodein.Salmon),
                    modifier = Modifier.height(45.5.dp)
                )
                Column(
                    Modifier
                        .offset(y = (-4).dp)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = title,
                        color = Color.Kodein.Salmon,
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp
                    )
                }
            }
            Text(
                text = subtitle,
                color = Color.Kodein.Salmon,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Picon.Extended,
                fontSize = 12.5.sp,
                modifier = Modifier
                    .offset(y = (-2).dp)
            )
        }
    }

    AnimatedVisibility(
        visible = step >= 1,
        enter = fadeIn(tween(1000)) + expandVertically(tween(1000), clip = false) + scaleIn(tween(1000)),
        exit = fadeOut(tween(1000)) + shrinkVertically(tween(1000), clip = false) + scaleOut(tween(1000)),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Activity("Kotlin\nTraining", "Certified by JetBrains")
            Activity("Multiplatform\nArchitecture", "Consultancy & Development")
        }
    }
}

val KodeinOpenSource by Slide(
    stepCount = 2,
    user = mapOf(KodeinPresentationBackground to KodeinPresentationBackground(Color(0xFF_46_AF_6D)))
) { step ->
    KodeinLogo(
        department = "OpenSource",
        color = Color.Kodein.Light,
        description = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("painless ")
                Image(
                    painter = painterResource("kotlin.svg"),
                    contentDescription = "Kotlin",
                    colorFilter = ColorFilter.tint(Color.Kodein.Light),
                    modifier = Modifier.height(10.dp)
                )
                Text(" multiplatform")
            }
        }
    )

    AnimatedVisibility(
        visible = step >= 1,
    ) {
        Row {
            @Composable
            fun RowScope.Platform(text: String) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.Black
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Platform("Android")
                Platform("iOS")
                Platform("Web")
                Platform("Server")
                Platform("Desktop")
            }
        }
    }
}

val KodeinContact by Slide {
    Image(
        painter = painterResource("handshake.webp"),
        contentDescription = "Handshake",
        modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(4.dp, Color.Kodein.Glycine, RoundedCornerShape(8.dp))
    )
    Title {
        Text(
            text = "contact@kodein.net",
            color = Color.Kodein.Glycine,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

val KodeinSlides = VerticalSlides(
    Slides(
        KodeinKoders,
        KodeinOpenSource
    ) {
        copyWithInsideTransitions(it, y3DRotation(it.layoutDirection), y3DRotation(it.layoutDirection))
    },
    KodeinContact
)
