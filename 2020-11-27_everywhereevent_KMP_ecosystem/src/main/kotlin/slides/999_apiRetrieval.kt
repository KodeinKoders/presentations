package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.*
import org.kodein.kpres.PresentationBuilder
import org.kodein.kpres.sourceCode
import ws.utils.*


fun PresentationBuilder.ktorClient() = slide(stateCount = 6) { props ->
    titledSlide("Consume a REST API") {
        bulletList(props) {
            val (state,_) = props
            animatedBulletPoint(state, 1, "Getting data from a REST API.")
            animatedBulletPoint(state, 2, "Serializing into a model.")
            animatedBulletPoint(state, 3, "Saving the data for offline use.")
            animatedBulletPoint(state, 4, "Test our common code once!")
            animatedBulletPoint(state, 5, "Use it on Android AND iOS.")
        }
    }
}

fun PresentationBuilder.ktorClientSetup() = slide(
    stateCount = 4,
    inTransitionDuration = 0) { (state, _) ->
    titledSlide("Consume a REST API") {
        sourceCode("kotlin", """
            // build.gradle.kts
            ...
            kotlin {
                android { ... }
                ios { ... }«src«
                sourceSets { 
                    val commonMain by getting {«dot-common« ... }»«common«
                        dependencies {
                            api("io.ktor:ktor-client-core:1.4.1")
                        }
                    }
                    »val androidMain by getting {«dot-platform« ... }»«platform«
                        dependencies { 
                            implementation("io.ktor:ktor-client-android:1.4.1")
                        }
                    }
                    »val iosMain by getting {«dot-platform« ... }»«platform«
                        dependencies { 
                            implementation("io.ktor:ktor-client-ios:1.4.1")
                        }
                    }
                »}
           »}
        """.trimIndent()) {
            fontSize = 0.9.em
            "span.c-marker" {
                opacity = 1.0
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)

                +"c-src" { blockEffectFrom(state, 1) }
                +"c-common" { blockEffect(state, 2..2) }
                +"c-dot-common" {
                    when (state) {
                        2 -> {
                            opacity = 0
                            fontSize = 0.em
                        }
                        else -> {
                            opacity = 1
                            fontSize = 1.em
                        }
                    }
                }
                +"c-platform" { blockEffectFrom(state, 3) }
                +"c-dot-platform" {
                    when (state) {
                        3 -> {
                            opacity = 0
                            fontSize = 0.em
                        }
                        else -> {
                            opacity = 1
                            fontSize = 1.em
                        }
                    }
                }
            }

        }
    }
}

fun PresentationBuilder.ktorClientUse() = slide(
    stateCount = 4,
    inTransitionDuration = 0) { (state, _) ->
    titledSlide("Consume a REST API") {
        sourceCode("kotlin", """
            // UserService.kt
            class UserService {
                
            }
        """.trimIndent()) {
            fontSize = 0.9.em
            "span.c-marker" {
                opacity = 1.0
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)


            }

        }
    }
}
