package ws

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.dom.render
import styled.injectGlobal
import org.kodein.kpres.Move
import org.kodein.kpres.presentation
import ws.slides.*
import kotlin.browser.document


fun CSSBuilder.globalCSS() {
    body {
//        backgroundImage = Image("linear-gradient(to bottom right, #E8441F, #921F81)")
        backgroundColor = Color.silver
        fontFamily = "Picon"
        color = Color.white
        margin(0.em)
        padding(0.em)

        div {
            +"pres-container" {
                backgroundImage = Image("linear-gradient(to bottom right, #E8441F, #921F81)")
            }

            +"inner-container" {
                backgroundColor = Color("rgba(0, 0, 0, 0.8)")
                transition(::background, 500.ms)
            }
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
                fontFamily = "fira code"
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

        injectGlobal { globalCSS() }

        presentation(
                defaultTransition = Move
        ) {

            intro()
            kodeinKoders()
            kodeinFramework()
            noSQL()
            SQLite()
            opinion()
            simpleUsage()
            kodeinDB()
            layers()
            contractImmut()
            immutability()
            open()
            path()
            mppModel()
            query()
            composite()
            contractId()
            batchAndSnapshot()
            reactive()
            reactiveLocal()
            reactiveGlobal()
            reactiveContext()
            people()
            polymorphism()
            mppPolymorphism()
            migration()
            release()
            next()
            thanks()
            perfs()
            thanks()
        }
    }
}
