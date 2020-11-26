package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.s
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform
import kotlinx.css.properties.transition
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.sourceCode
import styled.css
import styled.styledImg
import ws.utils.kotlinSourceCode
import ws.utils.titledSlide

fun PresentationBuilder.bootstrapKMM() = slide(stateCount = 4) { (state, _) ->
    titledSlide("How to bootstrap a KMM project ?") {
        styledImg(src = "images/bootstrap-kmm-idea.png") {
            css {
                height = 100.pct
                transition(::opacity, duration = 0.5.s)
                when {
                    state < 1 -> opacity = 0.0
                    state > 1 -> {
                        height = 0.pct
                        opacity = 0.0
                    }
                }
            }
        }
        styledImg(src = "images/bootstrap-kmm-as.png") {
            css {
                height = 100.pct
                transition(::opacity, duration = 0.5.s)
                when {
                    state < 2 -> opacity = 0.0
                    state > 2 -> {
                        height = 0.pct
                        opacity = 0.0
                    }
                }
            }
        }
        sourceCode("kotlin", """
            // build.gradle.kts
            plugins {
                id("com.android.library")
                kotlin("multiplatform")
                kotlin("plugin.serialization") version "1.4.20"
            }
            
            kotlin {
                android {
                    compilations.all {
                        kotlinOptions.jvmTarget = "1.8"
                    }
                }
                ios {
                    binaries {
                        framework {
                            baseName = "shared"
                        }
                    }
                }
                sourceSets {
                    ...
                }
            }
        """.trimIndent()) {
            fontSize = .8.em
            transition(::opacity, duration = 0.5.s)
            when {
                state < 3 -> opacity = 0.0
            }
        }
    }
}
