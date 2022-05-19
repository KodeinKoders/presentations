plugins {
    id("net.kodein.gradle.resources.resource-files") version "1.0.0"
    kotlin("multiplatform") version "1.6.21"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev686"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

val kodeinThemeVersion = "1.6.0"

kotlin {
    js(IR) {
        resourceFiles.addToProcessResources(compilations["main"].processResourcesTaskName)
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)

                implementation("net.kodein.themes:compose-pres:$kodeinThemeVersion")
            }
        }

        all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
                optIn("org.jetbrains.compose.web.ExperimentalComposeWebApi")
                optIn("kotlin.time.ExperimentalTime")
            }
        }
    }
}

dependencies {
    resourceFiles("net.kodein.themes:compose-pres-resources:$kodeinThemeVersion")
    resourceFiles("net.kodein.themes:illustrations:$kodeinThemeVersion")
}

task<Sync>("publish") {
    dependsOn("jsBrowserDistribution")
    from("$buildDir/distributions")
    into("$rootDir/../docs/${project.name}")
}
