package utils

import net.kodein.pres.Slide
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Img
import org.kodein.cic.css

public enum class Illustration(private val filename: String) {
    OSS("open-source"),
    Services("services"),
    Team("team"),
    Training("training");

    public operator fun invoke(): Slide = Slide(
        name = "illus-$filename"
    ) {
        Img(
            src = "img/illus/${filename}_1920.webp"
        ) {
            css {
                maxWidth(125.percent)
                maxHeight(125.percent)
            }
        }
    }
}