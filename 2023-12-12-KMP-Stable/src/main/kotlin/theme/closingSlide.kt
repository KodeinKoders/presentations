package theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import org.kodein.pres.Slide


fun closingSlide(
    location: String,
    date: String,
    speaker: String,
    email: String
): Slide = Slide(
    name = "thank-you"
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Title("Thank you!")

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

        Text(
            text = email,
            fontWeight = FontWeight.Light,
            color = Color.Kodein.Salmon
        )
    }
}
