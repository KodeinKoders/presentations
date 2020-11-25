package ws

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.boxShadow
import react.dom.render
import styled.injectGlobal
import org.kodein.kpres.Move
import org.kodein.kpres.presentation
import ws.slides.*
import kotlinx.browser.document
import ws.charter.kodein


fun CSSBuilder.globalCSS() {
    body {
        backgroundColor = Color.silver
        fontFamily = "Picon"
        color = Color.white
        margin(0.em)
        padding(0.em)

        div {
            +"pres-container" {
                backgroundColor = Color.kodein.dark
                backgroundImage = Image("url(images/logo-bg.svg)")
                backgroundRepeat = BackgroundRepeat.noRepeat
                backgroundPosition = "bottom -15rem right 0rem"
                backgroundSize = "contain"
            }

            +"inner-container" {}
        }
    }

    pre {
        +"code" {
            textAlign = TextAlign.left
            backgroundColor = Color("#2b2b2b")
            alignSelf = Align.stretch
            margin(0.em, 2.em)
            padding(0.5.em)
            borderRadius = 0.2.em
            boxShadow(Color.black, blurRadius = 0.5.em)
            code {
                fontFamily = "jetbrains mono"
                fontSize = 0.65.em
                lineHeight = LineHeight("1.2")
            }
        }
    }

    ul {
        listStyleType = ListStyleType.none
        textAlign = TextAlign.left
    }

    "h1, h2, h3" {
        margin(top = 0.2.em, bottom = 0.5.em, left = 0.em, right = 0.em)
    }
}

fun main() {
    render(document.getElementById("app")) {

        injectGlobal { globalCSS() }

        presentation(
                defaultTransition = Move
        ) {
            intro()
            phoenix()

            kodeinKoders()

            secp256k1_c()
            secp256k1_orga()
            warning_cInterop()

            targets()
            separation()

            actors()
            serialization()
            database()
            msg_ecosystem()
            kodeinFramework()

            mvi()
            msg_mvi()

            thanks()
        }
    }
}
