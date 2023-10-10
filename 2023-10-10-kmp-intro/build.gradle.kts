plugins {
    id("net.kodein.gradle.resources.resource-files") version "1.0.0"
    kotlin("multiplatform") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.2"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

val kodeinThemeVersion = "1.10.0"

kotlin {
    js(IR) {
        resourceFiles.addToProcessResources(compilations["main"].processResourcesTaskName)
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)

                implementation("net.kodein.themes:compose-pres:1.10.0")
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
    resourceFiles("net.kodein.themes:compose-pres-resources:1.9.0")
    resourceFiles("net.kodein.themes:illustrations:1.9.0")
}

task<Sync>("publish") {
    group = "publication"
    dependsOn("jsBrowserDistribution")
    from("$buildDir/dist/js/productionExecutable")
    into("$rootDir/../docs/${project.name}")
}
