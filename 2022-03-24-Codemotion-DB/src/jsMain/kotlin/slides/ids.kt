package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.Transitions.fontGrow
import net.kodein.pres.Transitions.grow
import net.kodein.pres.Transitions.stamp
import net.kodein.pres.hiddenIf
import net.kodein.pres.shownIf
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLDivElement
import utils.errorUnder


@Composable
private fun Document(type: String, attrs: AttrBuilderContext<HTMLDivElement>? = null, content: @Composable () -> Unit) {
    Div({
        css {
            backgroundImage("url(img/doc.svg)")
            backgroundSize("contain")
            backgroundRepeat("no-repeat")
            backgroundPosition("center")
            width(5.25.em)
            height(7.5.em)
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            justifyContent(JustifyContent.Start)
            alignItems(AlignItems.Center)
            margin(0.em, 1.em)
        }
        attrs?.invoke(this)
    }) {
        Span({
            css {
                fontWeight(100)
                margin(0.7.em, 0.em, 0.45.em, (-1.4).em)
            }
        }) {
            Text(type)
        }
        content()
    }
}

val ids = listOf(
    Slide(
        name = "id-change",
        stateCount = 9
    ) { state ->
        SourceCode("kotlin", """
            @Serializable
            data class User(
                «id-annotation:@Id »val uid: UUID,
                val name: Name,
                «birth-u:«birth-bad-index:@Index »val birth: Date»
            ) {
            «triple-id:    @Id fun dbId() = Triple(name.last, name.first, uid)
    
            »«birth-good-index:    @Index fun dbBirth() = Triple(birth.year, birth.month, birth.day)
            »«name-index:    @Index fun dbName() = Pair(name.last, name.first)
            »}
            «birth-query:
            val birth«birth-month:Month»«birth-day:Day»Pals = usersDB.query { birth «birth-month:p»eq «birth-bad-type:Birth»«birth-good-type:Triple»«birth-month:Pair»(1986, 12«birth-day:, 15») }
            »«family-query:
            val family = usersDB.query { «family-id:id»«family-name:name» peq "BRYS" }
            »
        """.trimIndent()) {
            "birth-query" { lineHeight(state >= 1) }
            "birth-u" { errorUnder(state == 2) }
            "birth-bad-index" { fontGrow(state < 3) }
            "birth-good-index" { lineHeight(state >= 3) }
            "birth-bad-type" { fontGrow(state < 3) }
            "birth-good-type" { fontGrow(state == 3) }
            "birth-day" { fontGrow(state < 4) }
            "birth-month" { fontGrow(state >= 4) }

            "name-index" { lineHeight(state in 5..6) }
            "family-query" { lineHeight(state >= 5) }

            "id-annotation" { fontGrow(state < 6) }
            "triple-id" { lineHeight(state >= 6) }

            "family-name" { fontGrow(state < 7) }
            "family-id" { fontGrow(state >= 7) }
        }

        Img("img/arrow.webp") {
            shownIf(state >= 8, grow)
            css {
                position(Position.Absolute)
                width(8.em)
                transform { rotate(135.deg) }
                top(28.percent)
                left(48.percent)
            }
        }
        P({
            shownIf(state >= 8, grow)
            css {
                position(Position.Absolute)
                color(Color("#007fff"))
                top(12.percent)
                left(48.percent)
                fontSize(1.6.em)
            }
        }) {
            Text("Beware of ")
            B { Text("weedings") }
            Text("!")
        }
    },

    Slide(
        name = "id-contract",
        stateCount = 7
    ) { state ->
        H3({
            style {
                marginTop(0.em)
            }
        }) {
            Span({ style { fontWeight(200) } }) { Text("Contract: ") }
            Text("ID defines Document")
        }

        SourceCode("kotlin", """
            «put:usersDB.put(User(1, "Doe", "John"), Date(32, 2, 82))
            »«update:
            val u = usersDB.get(1)
            usersDB.put(u.copy(birthday = Date(23, 2, 82)))
            »«copy:
            val u = usersDB.get(1)
            usersDB.put(u.copy(lastName = "Schmitt"))
            »
    """.trimIndent()) {
            "put" { lineHeight(state >= 1) }
            "update" { lineHeight(state >= 3) }
            "copy" { lineHeight(state >= 5) }
        }

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                paddingTop(1.em)
            }
        }) {
            Document("User", { shownIf(state >= 2, stamp) }) {
                B { Text("Doe") }
                B { Text("John") }
                B { Text("1") }
                Span {
                    Span({ hiddenIf(state >= 4, fontGrow) }) { Text("32/02/82") }
                    Span({ shownIf(state >= 4, fontGrow) }) { Text("23/02/82") }
                }
            }
            Document("User", { shownIf(state >= 6, stamp) }) {
                B { Text("Schmitt") }
                B { Text("John") }
                B { Text("1") }
                Span { Text("23/02/82") }
            }
        }
    }

)