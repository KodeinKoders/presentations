package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import react.child
import react.functionalComponent
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

fun PresentationBuilder.thanks() {
    slide { child(ThankYou, it) }
    slide { slideTitle("One more thing!") }
    slide(SlideInfos(2)) { child(OneMoreThing, it) }
    slide(SlideInfos(2)) { child(OneMoreThing2, it) }
}

private val ThankYou by functionalComponent<SlideContentProps> { props ->
    styledH1 {
        css {
            margin(0.5.em)
        }
        +"Thank You!"
    }
    styledH2 {
        css {
            fontWeight = FontWeight.w200
            marginTop = 0.em
        }
        +"Romain Boisselle"

        styledA(href = "https://twitter.com/romainbsl", target = "_blank") {
            css {
                fontSize = 0.6.em
                fontWeight = FontWeight.w400
                color = Color("#007bfa")
                textDecoration = TextDecoration.none
                display = Display.block
                marginBottom = 0.75.em
            }
            +"@romainbsl"
        }
        styledA(href = "https://twitter.com/KodeinKoders", target = "_blank") {
            css {
                fontSize = 0.6.em
                fontWeight = FontWeight.w400
                color = Color("#007bfa")
                textDecoration = TextDecoration.none
                display = Display.block
                marginBottom = 1.75.em
            }
            +"@KodeinKoders"
        }
        styledA(href = "https://romainbsl.github.io/deck-advanced-di-on-mpp/", target = "_blank") {
            css {
                fontSize = 0.6.em
                fontWeight = FontWeight.w400
                color = Color.white
                textDecoration = TextDecoration.none
                display = Display.block
                marginBottom = 0.75.em
            }
            +"https://romainbsl.github.io/deck-advanced-di-on-mpp/"
        }
    }
}
private val OneMoreThing by functionalComponent<SlideContentProps> { props ->
    slideTitle("Win a JetBrains licence!")

    kotlinSourceCode("""
        fun main() {
            val s: String? = null
            if (s?.isEmpty()) println("is empty")«correct-msg«
            Type mismatch: inferred type «correct-msg«is» Boolean? 
            but Boolean was expected...
            »if (s.isNullOrEmpty()) println("is null or empty")
        }
        
        // What will it display? Some possibilities:
        a) is empty is null or empty
        b) is null or empty
        c) prints nothing
        «correct«d) doesn’t compile»
        """.trimIndent()) {
        +"c-correct" {
            if (props.state == 1) {
                color = Color.green
                fontWeight = FontWeight.w500
            }
        }
        +"c-correct-msg" {
            blockEffectFrom(props.state, 1)
            if (props.state == 1) {
                color = Color.darkRed
                fontWeight = FontWeight.w500
            }
        }
    }
}
private val OneMoreThing2 by functionalComponent<SlideContentProps> { props ->
    slideTitle("Win a JetBrains licence (bis)!")

    kotlinSourceCode("""        
    class MyDelegate(var value: String) 
                : ReadOnlyProperty<Any?, String> {
        override fun getValue(thisRef: Any?, property: KProperty< * >)
                        = value
    }
    var delegate = MyDelegate("Salomon")
    val name by delegate «correct-msg«// Reference capture !»
    println(name)
    delegate.value = "Romain"
    println(name)
    delegate = MyDelegate("Aurelien")
    println(name)
    
    // What should be printed?
    a: Salomon, Salomon, Salomon
    «correct«b: Salomon, Romain, Romain»
    c: Salomon, Romain, Aurelien
    d: Ne compile pas
        """.trimIndent()) {
        +"c-correct" {
            if (props.state == 1) {
                color = Color.green
                fontWeight = FontWeight.w500
            }
        }
        +"c-correct-msg" {
            lineEffectFrom(props.state, 1)
        }
    }
}