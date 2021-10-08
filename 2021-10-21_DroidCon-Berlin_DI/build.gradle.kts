plugins {
    id("net.kodein.gradle.resources.resource-files") version "1.0"
    kotlin("multiplatform") version "1.5.30"
    id("org.jetbrains.compose") version "1.0.0-alpha4-build348"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

val kodeinThemeVersion = "1.0"

kotlin {
    js(IR) {
        resourceFiles.addToProcessResources(compilations["main"].processResourcesTaskName)
        browser()
        binaries.executable()
    }

    targets.all {
        compilations.all {
            kotlinOptions.allWarningsAsErrors = true
        }
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

// https://youtrack.jetbrains.com/issue/KT-49124
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
        resolution("@webpack-cli/serve", "1.5.2")
    }
}

task<Sync>("publish") {
    dependsOn("jsBrowserDistribution")
    from("$buildDir/distributions")
    into("$rootDir/../docs/${project.name}")
}
