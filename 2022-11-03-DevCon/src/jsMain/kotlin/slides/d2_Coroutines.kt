package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp
import kotlin.time.Duration.Companion.seconds


val d2_Coroutines = listOf(
    Slide(
        name = "Coroutines:Declaration",
        stateCount = 2
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Les coroutines permettent de faire du multi-threading de manière plus légère.")
        }
        Stamp(state >= 1) { Text("Incomplet !") }
    },
    Slide(
        name = "Coroutines:Truth",
        stateCount = 2,
    ) { state ->
        H2({
            css {
                color(KodeinColor.korail.css)
            }
        }) {
            Span({
                css {
                    opacity(if (state >= 1) 0.2 else 1.0)
                    transitions { "opacity" { duration = 0.3.s } }
                }
            }) {
                Text("Les coroutines sont un ")
            }
            Text("mécanisme coopératif")
            Span({
                css {
                    opacity(if (state >= 1) 0.2 else 1.0)
                    transitions { "opacity" { duration = 0.3.s } }
                }
            }) {
                Text(" qui permet à une routine de ")
            }
            Text("suspendre son execution")
            Span({
                css {
                    opacity(if (state >= 1) 0.2 else 1.0)
                    transitions { "opacity" { duration = 0.3.s } }
                }
            }) {
                Text(".")
            }
        }
    },
    Slide(
        name = "Coroutines:Proof"
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            //language=kotlin
            code = """
                val fibonacciSequence = buildSequence {
                    var p2 = 1
                    var p1 = 1
                    yield(p2)
                    yield(p1)
                    while (true) {
                        val v = p1 + p2
                        yield(v)
                        p2 = p1
                        p1 = v
                    }
                }
            """.trimIndent(),
            copyButton = true
        )
    },
    Slide(
        name = "Coroutines:Suspend",
        stateCount = 2,
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            code = """
                override suspend fun yield(value: T) {
                    nextValue = value
                    state = State_Ready
                    return «s:suspendCoroutineUninterceptedOrReturn» { c ->
                        nextStep = c
                        COROUTINE_SUSPENDED
                    }
                }
            """.trimIndent()
        ) {
            "s" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "Coroutines:Resume",
        stateCount = 2,
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            code = """
                override fun hasNext(): Boolean {
                    while (true) {
                        when (state) {
                            /*...*/
                        }
            
                        state = State_Failed
                        val step = nextStep!!
                        nextStep = null
                        «s:step.resume(Unit)»
                    }
                }
            """.trimIndent()
        ) {
            "s" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "Coroutines:Schema",
        stateCount = 5,
    ) { state ->
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
            }
        }) {
            SourceCode(
                lang  = "kotlin",
                code = """
                    val s = sequence { /*...*/ }
                    val n1 = next()
                    «s1:


                    »«s2:println(n1)

                    val n2 = next()
                    »«s3:


                    »«s4:println(n2)
                    »
                """.trimIndent()
            ) {
                "s1" { this.lineHeight(state >= 1) }
                "s2" { this.lineHeight(state >= 2) }
                "s3" { this.lineHeight(state >= 3) }
                "s4" { this.lineHeight(state >= 4) }
            }

            SourceCode(
                lang  = "kotlin",
                code = """


                    «s1:startCoroutine()
                    next = computeNextValue()
                    suspendCoroutine()
                    »«s2:


                    »«s3:resumeCoroutine()
                    next = computeNextValue()
                    suspendCoroutine()
                    »«s4:
                    »
                """.trimIndent()
            ) {
                "s1" { this.lineHeight(state >= 1) }
                "s2" { this.lineHeight(state >= 2) }
                "s3" { this.lineHeight(state >= 3) }
                "s4" { this.lineHeight(state >= 4) }
            }

        }
    },
    Slide(
        name = "Coroutines:Async",
        stateCount = 4,
    ) { state ->
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
            }
        }) {
            SourceCode(
                lang  = "kotlin",
                code = """
                    // Coroutine

                    val r = asyncHttp.get(url)
                    «s1:
                    »«s2:
                    
                    
                    
                    »«s3:
                    println(r)
                    »
                """.trimIndent()
            ) {
                "s1" { this.lineHeight(state >= 1) }
                "s2" { this.lineHeight(state >= 2) }
                "s3" { this.lineHeight(state >= 3) }
            }

            SourceCode(
                lang  = "kotlin",
                code = """
                    
                    
                    
                    «s1:suspendCoroutine()
                    »«s2:
                    thread {
                        val r = syncHttp.get(url)
                        resumeCoroutineOnMain(r)
                    }
                    »«s3:
                    »
                """.trimIndent()
            ) {
                "s1" { this.lineHeight(state >= 1) }
                "s2" { this.lineHeight(state >= 2) }
                "s3" { this.lineHeight(state >= 3) }
            }

        }
    },
    Slide(
        name = "Coroutines:Loop",
    ) {
        Img(src = "img/render-loop.png") {
            css {
                maxWidth(100.percent)
                maxHeight(100.percent)
            }
        }
    }
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
