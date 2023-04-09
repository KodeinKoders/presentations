package slides

import net.kodein.pres.Animations
import net.kodein.pres.Slide
import net.kodein.pres.animatedWith
import net.kodein.pres.emojis.Emoji
import org.jetbrains.compose.web.css.maxHeight
import org.jetbrains.compose.web.css.maxWidth
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css

val faq =
    listOf(
        Slide("faq") { H1 { Text("FAQ") } },
        Slide("jetbrains") { H3 { Text("Should I trust JetBrains?") } },
        Slide("google-jetbrains") { H3 { Text("Would Google bought JetBrains?") } },
        Slide("kotlin-foundation") {
            H3 {
                Img(src = "img/kotlin.svg") {
                    css {
                        maxWidth(40.percent)
                        maxHeight(40.percent)
                    }
                }
                Br {}
                Text("Kotlin Foundation")
            }
        },
    ).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
