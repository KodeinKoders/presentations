package slides

import androidx.compose.runtime.Composable
import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.util.transition
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import utils.errorUnder


const val squareSize = 4.5

@Composable
private fun Person(name: String, shown: Boolean, left: CSSLengthOrPercentageValue, top: CSSLengthOrPercentageValue) {
    Img("img/p-$name.webp") {
        css {
            position(Position.Absolute)
            width(squareSize.em)
            height(squareSize.em)
            transition {
                "left"(500.ms)
                "left"(500.ms)
                "top"(500.ms)
                "opacity"(500.ms)
            }
            borderRadius(0.2.em)
        }
        style {
            left(left)
            top(top)
            opacity(if (shown) 1 else 0)
        }
    }
}

@Composable
private fun Word(w: String, shown: Boolean, left: CSSLengthOrPercentageValue, top: CSSLengthOrPercentageValue) {
    P({
        css {
            position(Position.Absolute)
            width(squareSize.em)
            margin(0.em)
            transition {
                "left"(500.ms)
                "left"(500.ms)
                "top"(500.ms)
                "opacity"(500.ms)
            }
        }
        style {
            left(left)
            top(top)
            opacity(if (shown) 1 else 0)
        }
    }) {
        Text(w)
    }
}

val polymorphism = listOf(
    Slide(
        name = "polymorphism-sample",
        stateCount = 8
    ) { state ->

        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                height(100.percent)
                "pre" {
                    margin(0.em)
                    fontSize(0.9.em)
                }
            }
        }) {
            SourceCode("kotlin", """
                @Serializable
                sealed interface Person {
                    @Id val id: UUID
                    val name: String
                }
                «adult:
                @Serializable
                «poly:@Polymorphic(Person::class)
                »data class Adult(
                    override val id: UUID,
                    override val name: String
                ) : Person
                »«child:
                @Serializable
                «poly:@Polymorphic(Person::class)
                »class Child(
                    override val id: UUID,
                    override val name: String,
                    val parents: List<UUID>
                ) : Person
                »
            """.trimIndent()) {
                "adult" { lineHeight(state >= 1) }
                "child" { lineHeight(state >= 3) }
                "poly" { lineHeight(state >= 6) }
            }

            Div({
                css {
                    width(12.em)
                    marginLeft(2.em)
                    position(Position.Relative)
                }
            }) {
                Person(
                    "jane",
                    state >= 2,
                    if (state < 7) 0.5.em else (squareSize / 2 + 1.5).em,
                    if (state < 7) 2.em else (squareSize + 2.5).em
                )
                Person(
                    "john",
                    state >= 2,
                    if (state < 7) 0.5.em else (squareSize / 2 + 1.5).em,
                    if (state < 7) (squareSize + 2.5).em else (3 * (squareSize + 0.5) + 2).em
                )

                Person(
                    "jack",
                    state >= 4,
                    if (state < 7) (squareSize + 2.5).em else (squareSize / 2 + 1.5).em,
                    2.em
                )
                Person(
                    "jill",
                    state >= 4,
                    if (state < 7) (squareSize + 2.5).em else (squareSize / 2 + 1.5).em,
                    if (state < 7) (squareSize + 2.5).em else (2 * (squareSize + 0.5) + 2).em
                )

                Word(
                    "Adult",
                    state in 5..6,
                    if (state < 7) 0.5.em else (squareSize / 2 + 1.5).em,
                    0.5.em
                )
                Word(
                    "Child",
                    state in 5..6,
                    if (state < 7) (squareSize + 2.5).em else (squareSize / 2 + 1.5).em,
                    0.5.em
                )
                Word(
                    "Person",
                    state >= 7,
                    (squareSize / 2 + 1.5).em,
                    0.5.em
                )
            }
        }
    },

    Slide(
        name = "polymorphism-gen",
        stateCount = 4
    ) { state ->
        Div({
            css {
                "pre" {
                    margin(0.em)
                    fontSize(0.9.em)
                }
            }
        }) {
            SourceCode("kotlin", """
                object PersonDef : ModelDefinition<Person, UUID>(
                    getId = { uid },
                    serializer = Person.serializer(),
                    type = User::class
                )
    
                object AdultDef : PolymorphicModelDefinition<Adult, Person, UUID>(
                    parent = PersonDef,
                    serializer = Person.serializer(),
                    type = User::class
                )
                «db:
                val «p:person»«a:adult»DB = db.of(«p:PersonDef»«ad:AdultDef»)
                »
            """.trimIndent()) {
                "db" { lineHeight(state >= 1) }
                "ad" {
                    errorUnder(state == 2)
                    fontGrow(state < 3)
                }
                "a" { fontGrow(state < 3) }
                "p" { fontGrow(state >= 3) }
            }
        }
    }
)