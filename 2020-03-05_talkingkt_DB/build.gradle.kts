plugins {
    kotlin("js") version "1.3.61"
    id("org.ajoberstar.git-publish") version "2.1.1"
}

version = "1.0"

repositories {
    jcenter()
    maven( url = "https://kotlin.bintray.com/kotlin-js-wrappers")
}

kotlin {
    target {
        browser()
        useCommonJs()

        sourceSets["main"].dependencies {
            implementation(kotlin("stdlib-js"))

            val reactVersion = "16.9.0"
            val reactRouterVersion = "4.3.1"
            val kotlinWrapperVersion = "pre.88-kotlin-1.3.60"

            api("org.jetbrains:kotlin-react-dom:$reactVersion-$kotlinWrapperVersion")
            api("org.jetbrains:kotlin-react-router-dom:$reactRouterVersion-$kotlinWrapperVersion")
            implementation("org.jetbrains:kotlin-styled:1.0.0-$kotlinWrapperVersion")

            implementation(npm("react", "^$reactVersion"))
            implementation(npm("react-dom", "^$reactVersion"))
            implementation(npm("react-router", "^$reactRouterVersion"))
            implementation(npm("react-router-dom", "^$reactRouterVersion"))

            implementation(npm("css-in-js-utils", "3.0.2"))
            implementation(npm("inline-style-prefixer", "5.1.0"))
            implementation(npm("styled-components", "4.3.2"))
            implementation(npm("core-js", "3.2.0"))

            implementation(npm("highlight.js", "9.16.2"))
            implementation(npm("react-markdown", "4.2.2"))
        }
    }
}

gitPublish {
    repoUri.set("git@github.com:SalomonBrys/KC2019-Pres.git")
    branch.set("gh-pages")

    contents {
        val processResources = tasks["processResources"] as ProcessResources
        from(processResources.outputs.files)
        val browserWebpack = tasks["browserWebpack"] as org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
        from(browserWebpack.outputFile)
    }

    preserve {
        include("CNAME")
    }
}
tasks["gitPublishCopy"].dependsOn("assemble")
