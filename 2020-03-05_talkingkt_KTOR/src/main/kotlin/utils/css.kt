package ws.utils

import kotlinx.css.Color
import kotlinx.css.Image
import kotlinx.css.TagSelector

val li get() = TagSelector("li")

enum class Palette(val color: Color) {
    cute(Color("#F7E1DE")),
    dark(Color("#240821")),
    orange(Color("#E8451F")),
    orangeDark(Color("#A6301F")),
    orangeDarker(Color("#661C21")),
    orangeCute(Color("#EE785F")),
    orangeCuter(Color("#F3AD9E")),
    purple(Color("#911F83")),
    purpleDark(Color("#6E1761")),
    purpleDarker(Color("#460F3E")),
    purpleCute(Color("#B561A1")),
    purpleCuter(Color("#D6A1BF")),
}

class Background {
    companion object {
        val kodein = Image("linear-gradient(to bottom right, ${Palette.orange.color.value}, ${Palette.purple.color.value})")
        val kodeinDark = Image("linear-gradient(to bottom right, ${Palette.orangeDark.color.value}, ${Palette.purpleDark.color.value})")
        val kodeinDarker = Image("linear-gradient(to bottom right, ${Palette.orangeDarker.color.value}, ${Palette.purpleDarker.color.value})")
    }
}

class LCTPicon {
    private constructor()
    companion object {
        const val Picon = "Picon"
        const val Extended = "Picon-Extended"
        const val Condensed = "Picon-Condensed"
    }
}