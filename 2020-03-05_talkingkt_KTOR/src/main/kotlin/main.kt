package ws

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import react.dom.render
import styled.StyledComponents
import styled.injectGlobal
import ws.kpres.Move
import ws.kpres.presentation
import ws.slides.*
import ws.utils.Background
import ws.utils.LCTPicon
import kotlin.browser.document


fun CSSBuilder.globalCSS() {
    body {
//        backgroundImage = Image("linear-gradient(to bottom right, #E8441F, #921F81)")
        backgroundColor = Color.silver
        fontFamily = LCTPicon.Picon
        color = Color.white
        margin(0.em)
        padding(0.em)

        div {
            +"pres-container" {
                backgroundImage = Background.kodeinDarker
            }
        }
    }

    pre {
        +"code" {
            textAlign = TextAlign.left
            backgroundColor = Color("#FFFFFF")
            alignSelf = Align.stretch
            margin(0.em, 2.em)
            padding(0.5.em)
            borderRadius = 0.2.em
            code {
                fontFamily = "JetBrains Mono"
                fontSize = 0.65.em
                lineHeight = LineHeight("1.2")
            }
        }
    }

    ul {
        listStyleType = ListStyleType.none
        textAlign = TextAlign.left
    }
}

fun main() {
    render(document.getElementById("app")) {

        StyledComponents.injectGlobal { globalCSS() }

        presentation(
                defaultTransition = Move
        ) {
            intro()
            kodeinKoders()
            kodeinFramework()
            agenda()
            ktor()
            ktorCode()
            di()
            qovery()
            thanks()
        }
    }
}
