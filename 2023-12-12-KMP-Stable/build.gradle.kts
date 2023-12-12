plugins {
    kotlin("jvm") version "1.9.20"
    id("org.jetbrains.compose") version "1.5.10"
}

repositories {
    mavenCentral()
    mavenLocal()
}

compose.desktop {
    application.mainClass = "MainKt"
}

dependencies {
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.materialIconsExtended)
    implementation(compose.desktop.currentOs)

    implementation("org.kodein.pres:cup:0.1.0")
}
