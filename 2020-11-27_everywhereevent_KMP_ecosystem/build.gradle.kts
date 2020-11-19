plugins {
    kotlin("js") version "1.4.10"
}

version = "1.0"

repositories {
    jcenter()
    maven( url = "https://kotlin.bintray.com/kotlin-js-wrappers")
    mavenLocal()
}

kotlin {
    target {
        browser {
            webpackTask {
                outputFileName = "pres.js"
            }
            runTask {
                outputFileName = "pres.js"
            }
        }
        useCommonJs()

        sourceSets["main"].dependencies {
            api("net.kodein.kpres:kpres:1.1.0")

            val reactVersion = "17.0.0"
            val reactRouterVersion = "5.2.0"
            val styledVersion = "5.2.0"
            val kotlinWrapperVersion = "pre.129-kotlin-1.4.10"

            api("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-styled:$styledVersion-$kotlinWrapperVersion")

            implementation(npm("react", "^$reactVersion"))
            implementation(npm("react-dom", "^$reactVersion"))
            implementation(npm("react-router", "^$reactRouterVersion"))
            implementation(npm("react-router-dom", "^$reactRouterVersion"))

            implementation(npm("css-in-js-utils", "3.0.2"))
            implementation(npm("inline-style-prefixer", "5.1.0"))
            implementation(npm("styled-components", "4.3.2"))
            implementation(npm("core-js", "3.2.0"))

            api(npm("highlight.js", "^10.3.2"))
            api(npm("react-markdown", "^5.0.2"))
        }
    }
}

task<Sync>("publish") {
    dependsOn("browserDistribution")
    from("$buildDir/distributions")
    into("$rootDir/../docs/${project.name}")
}
