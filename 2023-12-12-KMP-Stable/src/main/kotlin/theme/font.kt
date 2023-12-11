package theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font


@Suppress("SpellCheckingInspection")
object LCTPicon {
    val Regular = FontFamily(
        Font(resource = "LCTPicon/LCTPicon-Regular-Thin.ttf", weight = FontWeight.Thin),
        Font(resource = "LCTPicon/LCTPicon-Regular-ExtraLight.ttf", weight = FontWeight.ExtraLight),
        Font(resource = "LCTPicon/LCTPicon-Regular-Light.ttf", weight = FontWeight.Light),
        Font(resource = "LCTPicon/LCTPicon-Regular-Normal.ttf", weight = FontWeight.Normal),
        Font(resource = "LCTPicon/LCTPicon-Regular-Medium.ttf", weight = FontWeight.Medium),
        Font(resource = "LCTPicon/LCTPicon-Regular-SemiBold.ttf", weight = FontWeight.SemiBold),
        Font(resource = "LCTPicon/LCTPicon-Regular-Bold.ttf", weight = FontWeight.Bold),
        Font(resource = "LCTPicon/LCTPicon-Regular-ExtraBold.ttf", weight = FontWeight.ExtraBold),
        Font(resource = "LCTPicon/LCTPicon-Regular-Black.ttf", weight = FontWeight.Black),
    )
    val Condensed = FontFamily(
        Font(resource = "LCTPicon/LCTPicon-Condensed-Thin.ttf", weight = FontWeight.Thin),
        Font(resource = "LCTPicon/LCTPicon-Condensed-ExtraLight.ttf", weight = FontWeight.ExtraLight),
        Font(resource = "LCTPicon/LCTPicon-Condensed-Light.ttf", weight = FontWeight.Light),
        Font(resource = "LCTPicon/LCTPicon-Condensed-Normal.ttf", weight = FontWeight.Normal),
        Font(resource = "LCTPicon/LCTPicon-Condensed-Medium.ttf", weight = FontWeight.Medium),
        Font(resource = "LCTPicon/LCTPicon-Condensed-SemiBold.ttf", weight = FontWeight.SemiBold),
        Font(resource = "LCTPicon/LCTPicon-Condensed-Bold.ttf", weight = FontWeight.Bold),
        Font(resource = "LCTPicon/LCTPicon-Condensed-ExtraBold.ttf", weight = FontWeight.ExtraBold),
        Font(resource = "LCTPicon/LCTPicon-Condensed-Black.ttf", weight = FontWeight.Black),
    )
    val Extended = FontFamily(
        Font(resource = "LCTPicon/LCTPicon-Extended-Thin.ttf", weight = FontWeight.Thin),
        Font(resource = "LCTPicon/LCTPicon-Extended-ExtraLight.ttf", weight = FontWeight.ExtraLight),
        Font(resource = "LCTPicon/LCTPicon-Extended-Light.ttf", weight = FontWeight.Light),
        Font(resource = "LCTPicon/LCTPicon-Extended-Normal.ttf", weight = FontWeight.Normal),
        Font(resource = "LCTPicon/LCTPicon-Extended-Medium.ttf", weight = FontWeight.Medium),
        Font(resource = "LCTPicon/LCTPicon-Extended-SemiBold.ttf", weight = FontWeight.SemiBold),
        Font(resource = "LCTPicon/LCTPicon-Extended-Bold.ttf", weight = FontWeight.Bold),
        Font(resource = "LCTPicon/LCTPicon-Extended-ExtraBold.ttf", weight = FontWeight.ExtraBold),
        Font(resource = "LCTPicon/LCTPicon-Extended-Black.ttf", weight = FontWeight.Black),
    )
}

@Suppress("SpellCheckingInspection")
val FontFamily.Companion.Picon: LCTPicon get() = LCTPicon
