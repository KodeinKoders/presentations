package slides

import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fade
import net.kodein.pres.emojis.Emoji.top
import net.kodein.pres.shownIf
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

val tooling = Slide(
    name = "tooling",
    stateCount = 7
) { state ->
    H2 { Text("What about the tooling?") }

    Img(src = "img/logos/android-studio.png") {
        shownIf(state > 1, fade)
        css {
            position(Position.Absolute)
            top(5.em)
            left(5.em)
            height(5.em)
        }
    }

    Img(src = "img/logos/kmm.png") {
        shownIf(state > 0, fade)
        css {
            position(Position.Absolute)
            top(5.em)
            property("margin-left", "auto")
            property("margin-right", "auto")
            height(5.em)
        }
    }

    Img(src = "img/logos/appcode.png") {
        shownIf(state > 2, fade)
        css {
            position(Position.Absolute)
            top(5.em)
            right(5.em)
            height(5.em)
        }
    }

    Img(src = "img/logos/xcode.png") {
        shownIf(state > 3, fade)
        css {
            position(Position.Absolute)
            bottom(5.em)
            left(5.em)
            height(5.em)
        }
    }

    Img(src = "img/logos/gradle.png") {
        shownIf(state > 5, fade)
        css {
            position(Position.Absolute)
            bottom(5.em)
            right(5.em)
            height(5.em)
        }
    }

    Img(src = "img/logos/idea.png") {
        shownIf(state > 4, fade)
        css {
            position(Position.Absolute)
            bottom(5.em)
            property("margin-left", "auto")
            property("margin-right", "auto")
            height(5.em)
        }
    }
}