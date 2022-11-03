package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.emojis.Emoji
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.pres.sourcecode.struck
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Stamp
import kotlin.time.Duration.Companion.seconds


val d3_Kmp = listOf(
    Slide(
        name = "Kmp:Declaration",
        stateCount = 3
    ) { state ->
        H2({
            css {
                color(KodeinColor.kamethiste.css)
            }
        }) {
            Text("Kotlin/Multiplateforme permet de ")
            Span({ shownIf(state < 2, Transitions.fontGrow) }) { Text("transposer") }
            Span({
                shownIf(state >= 2, Transitions.fontGrow)
                css { color(KodeinColor.korail.css) }
            }) { Text("compiler") }
            Br()
            Text(" du code Kotlin ")
            Span({ shownIf(state < 2, Transitions.fontGrow) }) { Text("JVM") }
            Span({
                shownIf(state >= 2, Transitions.fontGrow)
                css { color(KodeinColor.korail.css) }
            }) { Text("Multiplatform") }
            Br()
            Text(" pour iOS. ")
        }
        Stamp(state == 1) { Text("Illusoire !") }
    },
    Slide(
        name = "Kmp:Truth",
    ) { state ->
        H2 {
            Span({ css { color(KodeinColor.kamethiste.css) } }) {
                Text("JVM StdLib")
            }
            Br()
            Span({ css { fontSize(2.em) } }) {
                Text("≠")
            }
            Br()
            Span({ css { color(KodeinColor.korail.css) } }) {
                Text("Kotlin StdLib")
            }
        }
    },
    Slide(
        name = "Kmp:Jvm",
        stateCount = 9
    ) { state ->
        SourceCode(
            lang  = "kotlin",
            code = """
                «mi:import «mp:java.util».SortedMap
                import «mp:java.util».TreeMap
                »«ti:import «tp:kotlin.concurrent».thread
                »
                
                fun List<Any>.byId(cb: («m:SortedMap»<String, Any>) -> Unit) {
                    «t:thread» {
                        val map = «m:TreeMap»<String, Any>()
                        forEach {
                            val id = it::«c:class.«cm:members»»
                                .firstOrNull { it.name == "id" }
                            if (id != null) {
                                map[id.call(it).toString()] = it
                            }
                        }
                        cb(map)
                    }
                }
            """.trimIndent()
        ) {
            "m" {
                zoomed(state == 1)
                struck(state >= 3, KodeinColor.cute.css)
            }
            "mi" {
                lineHeight(state >= 2)
            }
            "mp" {
                struck(state >= 3, KodeinColor.cute.css)
            }
            "t" {
                zoomed(state == 4)
                struck(state >= 6, KodeinColor.cute.css)
            }
            "ti" {
                lineHeight(state >= 5)
            }
            "tp" {
                struck(state >= 6, KodeinColor.cute.css)
            }
            "c" {
                zoomed(state == 7)
            }
            "cm" {
                struck(state >= 8, KodeinColor.cute.css)
            }
        }
    },

    Slide(
        name = "Kmp:Table",
        stateCount = 9
    ) { state ->
        H3 {
            Text("Comment faire...")
        }
        Table({
            css {
                "td" {
                    fontSize(2.5.cssRem)
                    padding(0.5.em)
                }
                "td.l" {
                    textAlign("right")
                }
                "td.r" {
                    textAlign("left")
                }
            }
        }) {
            Tr {
                Td({
                    classes("l")
                    shownIf(state >= 1, Transitions.fade)
                }) {
                    Text("Multi-Thread")
                }
                Td({
                    shownIf(state >= 2, Transitions.fade)
                }) { Text(Emoji.smile) }
                Td({
                    classes("r")
                    shownIf(state >= 2, Transitions.fade)
                }) { Text("Coroutines") }
            }
            Tr {
                Td({
                    classes("l")
                    shownIf(state >= 3, Transitions.fade)
                }) {
                    Text("Collections")
                }
                Td({
                    shownIf(state >= 4, Transitions.fade)
                }) { Text(Emoji.sweat_smile) }
                Td({
                    classes("r")
                    shownIf(state >= 4, Transitions.fade)
                }) { Text("KOTLIN StdLib") }
            }
            Tr {
                Td({
                    classes("l")
                    shownIf(state >= 5, Transitions.fade)
                }) {
                    Text("Reflexion")
                }
                Td({
                    shownIf(state >= 6, Transitions.fade)
                }) { Text(Emoji.face_with_head_bandage) }
                Td({
                    classes("r")
                    shownIf(state >= 6, Transitions.fade)
                }) {
                    Text("Plugin de Compilateur")
                    Br()
                    Text("Kotlin Symbol Processor")
                }
            }
            Tr {
                Td({
                    classes("l")
                    shownIf(state >= 7, Transitions.fade)
                }) {
                    Text("API Platformes")
                }
                Td({
                    shownIf(state >= 8, Transitions.fade)
                }) { Text(Emoji.exploding_head) }
                Td({
                    classes("r")
                    shownIf(state >= 8, Transitions.fade)
                }) { Text("Expect / Actual") }
            }
        }
    },

    Slide(
        name = "Kmp:Libs"
    ) { state ->
        H2 {
            Text("Librairies Kotlin/Multiplatform")
        }
        Ul {
            Li { Text("BDD : SqlDelight") }
            Li { Text("Fichiers: OkIO") }
            Li { Text("GraphQL : Apollo") }
            Li { Text("Injection de Dépendance: Kodein") }
            Li { Text("Logs: Canard") }
            Li { Text("Mocking: MocKMP") }
            Li { Text("Parallélisme : KotlinX.Coroutines") }
            Li { Text("Réseau : KTor") }
            Li { Text("Sérialisation: KotlinX.Serialization") }
            Li { Text("...") }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
