plugins {
    kotlin("js") version "1.3.72"
    `maven-publish`
}

group = "org.kodein.kpres"
version = "1.0"

repositories {
    jcenter()
    maven( url = "https://kotlin.bintray.com/kotlin-js-wrappers")
}

kotlin {
    target {
        browser {}
        useCommonJs()

        sourceSets["main"].dependencies {
            implementation(kotlin("stdlib-js"))

            val reactVersion = "16.13.1"
            val reactRouterVersion = "5.1.2"
            val kotlinWrapperVersion = "pre.105-kotlin-1.3.72"

            api("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-styled:1.0.0-$kotlinWrapperVersion")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("kpres") {
            from(components["kotlin"])
        }
    }
}
