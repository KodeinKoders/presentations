package slides

import net.kodein.pres.*
import net.kodein.pres.Transitions
import net.kodein.pres.Transitions.fade
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.zoomed
import net.kodein.theme.KodeinColor
import net.kodein.theme.compose.web.Logo
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.kodein.cic.css
import utils.Dimmed
import utils.Stamp
import utils.dimmed


val d6_puzzle = listOf(
    Slide(
        name = "puzzle",
        stateCount = 4
    ) { state ->
        H4 { Text("Pieces of the Puzzle") }
        Ul({
            css {
                "b" {
                    color(KodeinColor.orange.css)
                }
                "li" {
                    padding(1.em)
                }
            }
        }) {
            Li({ shownIf(state >= 1, fade) }) {
                Text("Use KSP to ")
                B { Text("instrument") }
                Text(" code at ")
                B { Text("Compile-Time") }
                Text(".")
            }
            Li({ shownIf(state >= 2, fade) }) {
                Text("Use KotlinPoet to ")
                B { Text("generate") }
                Text(" code at ")
                B { Text("Compile-Time") }
                Text(".")
            }
            Li({ shownIf(state >= 3, fade) }) {
                Text("Use Kotlin/Multiplatform to ")
                B { Text("compile") }
                Text(" everything for ")
                B { Text("all targets") }
                Text(".")
            }
        }
    },
    Slide(
        name = "KSP-processor",
        stateCount = 4
    ) { state ->
        SourceCode("kotlin", """
            «c:class MirrorGenerator(
              private val codeGenerator: CodeGenerator,
              private val logger: KSPLogger,
            ) : SymbolProcessor {»
              override fun process(resolver: Resolver): List<KSAnnotated> {
                «r:resolver.getSymbolsWithAnnotation(annotationName)
                  .forEach {
                    when (symbol) {
                      is KSPropertyDeclaration -> {}
                      is KSPropertySetter -> {}
                      is KSFunctionDeclaration -> {}
                      is KSClassDeclaration -> {}
                    }
                }»
              }
            }
        """.trimIndent()
        ) {
            "c" { zoomed(state == 1) }
            "r" { zoomed(state == 2) }
        }
    },
    Slide(
        name = "KSP-generator",
        stateCount = 3
    ) { state ->
        SourceCode("kotlin", """
            val file = FileSpec.builder("org.mirror", "FooMirror")
            val cls = TypeSpec.classBuilder("FooMirror")
            /*...*/
            file.addType(cls.build())
            «w:file.build()
              .writeTo(codeGenerator, Dependencies(false))»
        """.trimIndent()
        ) {
            "w" { zoomed(state == 1) }
        }
    },
    Slide(
        name = "KSP-gradle",
        stateCount = 5
    ) { state ->
        SourceCode("kotlin", """
            «p:plugins {
              kotlin("multiplatform")
              id("com.google.devtools.ksp")
                .version("1.8.10-1.0.9")
            }»
            «k:kotlin {
              jvm()
              ios()
              sourceSets.commonMain.dependencies {
                implementation("org.mirror:runtime:1.0")
              }
            }»
            «d:dependencies {
              kspCommonMainMetadata("org.mirror:processor:1.0")
            }»
        """.trimIndent()
        ) {
            "p" { zoomed(state == 1) }
            "k" { zoomed(state == 2) }
            "d" { zoomed(state == 3) }
        }
    },
).animatedWith(Animations.Move(towards = Animations.Move.Towards.Bottom))
