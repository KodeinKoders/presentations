package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import react.dom.br
import react.dom.h1
import styled.css
import ws.utils.opacity
import ws.utils.s
import ws.utils.slideCode
import ws.utils.transform

fun PresentationBuilder.kn2b() = slide(stateCount = 2) { props ->

    h1 {
        s {
            css { fontWeight = FontWeight.w200 }
            +"K/N part 2b: Disable "
        }
        s {
            +"cross-compile"
        }
    }

    slideCode(props.state, "kotlin", """
        // build.gradle.kts

        afterEvaluate {
            val targets = when {
                currentOs.isLinux -> listOf("mingwX64", "macosX64")
                currentOs.isMacOsX -> listOf("mingwX64", "linuxX64")
                currentOs.isWindows -> listOf("linuxX64", "macosX64")
                else -> error("Unsupported os ${'$'}currentOs")
            }.mapNotNull { kotlin.targets.findByName(it) as? KotlinNativeTarget }
        
            configure(targets) {
                compilations.all {
                    cinterops.all { tasks[interopProcessingTaskName].enabled = false }
                    compileKotlinTask.enabled = false
                    tasks[processResourcesTaskName].enabled = false
                }
                binaries.all { linkTask.enabled = false }
        
                mavenPublication {
                    val publicationToDisable = this
                    tasks.withType<AbstractPublishToMaven>().all {
                        onlyIf { publication != publicationToDisable }
                    }
                    tasks.withType<GenerateModuleMetadata>().all {
                        onlyIf { publication.get() != publicationToDisable }
                    }
        
                }
            }
        }
    """.trimIndent()) {
        opacity(props.state >= 1)
        transform(props.state < 1) { translate(0.px, -2.em) }

        "span.txt" {
            fontSize = 0.6.em
        }
    }

}
