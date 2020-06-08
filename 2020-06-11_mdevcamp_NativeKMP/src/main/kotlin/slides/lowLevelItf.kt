package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.h1
import styled.css
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.lowLevelItf() = slide(stateCount = 5) { props ->

    slideCode(props.state, "kotlin", """
        «l:0«interface Cursor {

            fun currentValue(): ByteArray

            fun next()

            fun close()

        }

        »interface Collection«s:1-«Native» {

            fun newCursor«s:1-«Ptr»(): «s:0«Cursor»«s:1-«Long»«l:2-«
            «l:3-«
            fun cursorValueLength(cursorPtr: Long): Int

            »fun cursorValue(cursorPtr: Long«s:3-«, into: ByteArray»)«s:-2«: ByteArray»

            fun cursorNext(cursorPtr: Long)

            fun cursorClose(cursorPtr: Long)

        »}«l:4-«
        
        class Collection(private val native: CollectionNative) {
            //...
        }»
    """.trimIndent()) {
//        "span.txt" {
//            fontSize = 0.8.em
//        }
    }

}
