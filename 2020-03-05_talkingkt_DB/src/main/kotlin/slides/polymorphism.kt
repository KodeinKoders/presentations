package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import react.dom.h4
import react.dom.img
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.kpres.sourceCode


private val infos = SlideInfos(
        stateCount = 8
)

fun PresentationBuilder.polymorphism() = slide(infos) { props ->

    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignSelf = Align.stretch
            flexGrow = 1.0
        }

        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.column
                justifyContent = JustifyContent.center
                alignItems = Align.center
            }

            sourceCode(
                    "kotlin",
                    """
                «child-i«open »class Person(
                    @Id val name: String
                )«child«
                
                «poly«@Polymorphic(Person::class)»
                class Child(
                    name: String,
                    val parents: List<Key<Person>>
                ) : Person(name)»«parents«
                
                
                val jane = db.put(Person("Jane"))
                val john = db.put(Person("John"))»«children«

                val parents = listOf(jane, john)
                db.put(Child("Jill", parents))
                db.put(Person("Jack", parents))»«find«
                
                
                db.find<Person>().all()»
            """.trimIndent()
            ) {

                "code" {
                    overflow = Overflow.hidden
                }

                "span" {
                    +"c-marker" {
                        opacity = 1.0
                        verticalAlign = VerticalAlign.middle
                        transition(::opacity, 300.ms)
                        transition(::lineHeight, 300.ms)
                        transition(::fontSize, 300.ms)
                    }

                    +"c-child" {
                        lineHeight = LineHeight(if (props.state < 1) "0" else "1.2")
                        opacity = if (props.state < 1) 0.0 else 1.0
                    }
                    +"c-child-i" {
                        fontSize = if (props.state < 1) 0.em else 1.em
                    }
                    +"c-parents" {
                        lineHeight = LineHeight(if (props.state < 2) "0" else "1.2")
                        opacity = if (props.state < 2) 0.0 else 1.0
                    }
                    +"c-children" {
                        lineHeight = LineHeight(if (props.state < 3) "0" else "1.2")
                        opacity = if (props.state < 3) 0.0 else 1.0
                    }
                    +"c-find" {
                        lineHeight = LineHeight(if (props.state < 4) "0" else "1.2")
                        opacity = if (props.state < 4) 0.0 else 1.0
                    }
                    +"c-poly" {
                        lineHeight = LineHeight(if (props.state < 6) "0" else "1.2")
                        opacity = if (props.state < 6) 0.0 else 1.0
                        universal { color = Color.white }
                    }
                }

            }
        }

        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.column
                alignItems = Align.center
                flexGrow = 1.0
                "img" {
                    width = 3.2.em
                    margin(0.5.em)
                    transition(::opacity, 500.ms)
                    transition("transform", 500.ms)
                }
            }

            styledDiv {
                css {
                    position = Position.relative
                    alignSelf = Align.stretch
                    height = 1.2.em

                    "h4" {
                        margin(0.em)
                        fontWeight = FontWeight.w200
                        position = Position.absolute
                        top = 0.pct
                        left = 0.pct
                        width = 100.pct
                        height = 100.pct
                        transition(::opacity, 500.ms)
                        transition("transform", 500.ms)
                    }
                }

                styledH4 {
                    css {
                        if (props.state < 7) {
                            transform {
                                translate((-3).em, 0.em)
                            }
                        }
                        opacity = if (props.state < 5) 0.0 else 1.0
                    }
                    +"Person"
                }

                styledH4 {
                    css {
                        if (props.state < 7) {
                            transform {
                                translate(3.em, 0.em)
                            }
                        }
                            opacity = if (props.state in 5..6) 1.0 else 0.0
                    }
                    +"Child"
                }
            }

            styledImg(src = "images/p-jack.jpg") {
                css {
                    if (props.state < 7) {
                        transform {
                            translate(3.em, 0.em)
                        }
                    }
                    opacity = if (props.state < 3) 0.0 else 1.0
                }
            }

            styledImg(src = "images/p-jane.jpg") {
                css {
                    if (props.state < 7) {
                        transform {
                            translate((-3).em, (-4.2).em)
                        }
                    }
                    opacity = if (props.state < 2) 0.0 else 1.0
                }
            }

            styledImg(src = "images/p-jill.jpg") {
                css {
                    if (props.state < 7) {
                        transform {
                            translate(3.em, (-4.2).em)
                        }
                    }
                    opacity = if (props.state < 3) 0.0 else 1.0
                }

            }

            styledImg(src = "images/p-john.jpg") {
                css {
                    +"_"
                    if (props.state < 7) {
                        transform {
                            translate((-3).em, (-8.4).em)
                        }
                    }
                    opacity = if (props.state < 2) 0.0 else 1.0
                }

            }

        }
    }


}
