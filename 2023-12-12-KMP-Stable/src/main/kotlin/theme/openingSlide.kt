package theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.kodein.pres.Slide


fun openingSlide(
    title: String,
    location: String,
    date: String,
    speaker: String,
    gde: Boolean = false
): Slide = Slide(
    name = "title"
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource("kodein-logo.svg"),
                contentDescription = "Kodein Koders logo",
                colorFilter = ColorFilter.tint(Color.Kodein.Orange),
                modifier = Modifier
                    .height(48.dp)
                    .padding(end = 8.dp)
                    .offset(y = (-2.5).dp)
            )
            Text(
                text = "KODEIN",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Kodein.Orange
            )
            Text(
                text = "KODERS",
                fontWeight = FontWeight.Light,
                fontSize = 28.sp,
                color = Color.Kodein.Orange
            )
        }
        Text(
            text = title,
            fontSize = 1.6.em,
            fontFamily = FontFamily.Picon.Extended,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "$location - $date",
            fontWeight = FontWeight.ExtraLight,
        )
        Text(
            text = speaker,
            fontSize = 1.2.em,
            fontFamily = FontFamily.Picon.Extended,
            fontWeight = FontWeight.Medium,
            color = Color.Kodein.Salmon
        )
        if (gde) {
            Image(
                painter = painterResource("gde.png"),
                contentDescription = "Google Developer Expert",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
        }
    }
}
