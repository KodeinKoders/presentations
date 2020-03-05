package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledImg
import ws.kpres.*
import ws.utils.*

private val Dockerize by functionalComponent<SlideContentProps> { props ->
    titledContent("Dockerize your application") {
        sourceCode("docker", """
        «build«# BUILD
        FROM adoptopenjdk/openjdk11:alpine AS «from«build»

        RUN mkdir -p /app
        COPY . /app
        WORKDIR /app
        RUN ./gradlew build

        »«deploy«# DEPLOY
        FROM adoptopenjdk/openjdk11:alpine-jre

        COPY --from=«from«build /app/build/libs/ktor-todolist.jar» /app.jar

        WORKDIR /app
        EXPOSE 8080

        CMD «exec«exec java -jar /app.jar»»
        """.trimIndent()) {
            "code" {
                overflow = Overflow.hidden
            }
            "span.c-marker" {
                opacity = 1.0
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)
                +"c-build" { blockEffectFrom(props.state, 1) }
                +"c-deploy" { blockEffectFrom(props.state, 2) }
                +"c-from" { highlightOn(props.state, 3) }
                +"c-exec" { highlightOn(props.state, 4) }
            }
        }
    }
}
private val DeployDocker by functionalComponent<SlideContentProps> { props ->
    titledContent("Deploy ... as a Docker wizard!") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletCode(currentState, 1, "Docker image", "bash", """
            # Build the image that will run the app
            /$/ docker build -t romainbsl/ktor-application:1.0.0 . 
            """.trimIndent())
            bulletCode(currentState, 2, "Run the database", "bash", """
            # Run the database that will be need by the app
            /$/ docker run --rm --name postgres-ktor-app 
                -e POSTGRES_PASSWORD=secret -d -p 5432:5432 
                -v /docker/volumes/postgres:/var/lib/postgresql/data
            """.trimIndent())
            bulletCode(currentState, 3, "Run the app", "bash", """
            # Run app
            /$/ docker run -p 8080:8080 romainbsl/ktor-application:1.0.0
            """.trimIndent())
        }

    }
}
private val CloudEnv by functionalComponent<SlideContentProps> { props ->
    titledContent("Manage your environment...") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletPoint(currentState, 1, "(n) Kubernetes cluster?")
            bulletPoint(currentState, 2, "gateway?")
            bulletPoint(currentState, 3, "service mesh?")
            bulletPoint(currentState, 4, "handle environments (dev/qa/prod)")
            bulletPoint(currentState, 5, "resources", level = 2)
            bulletPoint(currentState, 6, "databases", level = 2)
            bulletPoint(currentState, 7, "...", level = 2)
            bulletPoint(currentState, 7, "...")
        }

    }
}

private val QoveryInfos = SlideInfos(
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("#333652")
                fontFamily = "SF UI Text Regular"
                transition(::background, 1000.ms)
            }
        }
)
private val WhatIsQovery by functionalComponent<SlideContentProps> { props ->
    titledContent("What is Qovery?") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletPoint(currentState, 1, "Container as a Service")
            bulletPoint(currentState, 2, "For any developer", level = 2)
            bulletPoint(currentState, 3, "Deploy to any provider seamlessly", level = 2)
            bulletPoint(currentState, 4, "Bound to your git repositories")
            bulletPoint(currentState, 5, "1 feature = 1 branch = 1 environment", level = 2)
        }
    }
}
private val QoveryEnv by functionalComponent<SlideContentProps> { props ->
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = Align.center
            alignContent = Align.center
        }
        styledImg(src = "images/qovery-env.png") {
            css {
                height = 15.em
            }
        }
    }
}
private val QoveryConf by functionalComponent<SlideContentProps> { props ->
    titledContent(".qovery.yml") {
        sourceCode("yaml", """
        «app«application:
         name: modern-cloud-with-kotlin
         project: modern-cloud-with-kotlin
         cloud_region: aws/eu-west-3
         publicly_accessible: true
        »«db«# required databases
        databases:
        - type: postgresql
         version: "11.5"
         name: my-postgresql-6132005
        »«routes«# one endpoint can target multiple applications
        # here there is only one
        routers:
        - name: main
         routes:
         - application_name: modern-cloud-with-kotlin
           paths:
           - /*»
        """.trimIndent()) {
            "code" {
                overflow = Overflow.hidden
            }
            "span.c-marker" {
                opacity = 1.0
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)
                val currentState = props.state
                +"c-app" { blockEffectFrom(currentState, 1) }
                +"c-db" { blockEffectFrom(currentState, 2) }
                +"c-routes" { blockEffectFrom(currentState, 3) }
            }
        }
    }
}
private val QoveryCLI by functionalComponent<SlideContentProps> { props ->
    titledContent("Qovery CLI") {
        sourceCode("bash", """
        «auth«# Github, Bitbucket, Gitlab seamless authentication
        /$/ qovery auth
        Opening your browser, waiting for your authentication...
        Authentication successful!
         
        »«init«# Wizard to generate .qovery.yml
        /$/ qovery init
         
        »«git-1«# Git commit and push your code
        /$/ git add .qovery.yml
        »«git-2«/$/ git commit -m "add .qovery.yml file"
        »«git-3«/$/ git push -u origin master
         
        »«deploy«# App deployed!»
        """.trimIndent()) {
            val currentState = props.state

            "code" {
                overflow = Overflow.hidden
            }
            "span.c-marker" {
                opacity = 1.0
                transition(::opacity, 300.ms)
                transition(::fontSize, 300.ms)
                transition(::lineHeight, 300.ms)
                +"c-auth" { blockEffectFrom(currentState, 1) }
                +"c-init" { blockEffectFrom(currentState, 2) }
                +"c-git-1" { blockEffectFrom(currentState, 3) }
                +"c-git-2" { blockEffectFrom(currentState, 4) }
                +"c-git-3" { blockEffectFrom(currentState, 5) }
                +"c-deploy" { blockEffectFrom(currentState, 6) }
            }
        }
    }
}
private val QoveryAPI by functionalComponent<SlideContentProps> { props ->
    titledContent("Qovery API") {
        kotlinSourceCode("""
        private fun hikari(): HikariDataSource {«instance«
            val qovery: Qovery by instance()

            »«conf«val databaseConfiguration =
                qovery.listDatabaseConfiguration().toList()
                    .find {
                        it.name == "my-postgresql-6132005" 
                                && it.type == DatabaseType.POSTGRESQL
                    } ?: getLocalDataSource()

            »«usage«val host = databaseConfiguration.host
            val port = databaseConfiguration.port
            val username = databaseConfiguration.username
            val password = databaseConfiguration.password
            // Build connection string
        »}
        """.trimIndent()) {
            val currentState = props.state
            +"c-instance" { blockEffectFrom(currentState, 1) }
            +"c-conf" { blockEffectFrom(currentState, 2) }
            +"c-usage" { blockEffectFrom(currentState, 3) }
        }
    }
}

fun PresentationBuilder.qovery() {
    slide { slideTitle("How to deploy your Ktor application ?") }
    slide(SlideInfos(5)) { child(Dockerize, it) }
    slide(SlideInfos(4)) { child(DeployDocker, it) }
    slide(SlideInfos(8, outTransitions = Flip)) { child(CloudEnv, it) }
    slide(QoveryInfos.copy(inTransitions = Flip, inTransitionDuration = 1000)) {
        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                alignItems = Align.center
                alignContent = Align.center
            }
            styledImg(src = "images/qovery.png") {
                css {
                    height = 5.em
                }
            }
            styledH1 {
                css {
                    fontWeight = FontWeight.w500
                    fontFamily = "SF UI Text Medium"
                    margin(0.em, 0.em, 0.3.em, 0.em)
                    padding(1.em)
                }
                +"Qovery"
            }
        }
    }
    slide(QoveryInfos.copy(6)) { child(WhatIsQovery, it) }
    slide(QoveryInfos) { child(QoveryEnv, it) }
    slide(QoveryInfos.copy(4)) { child(QoveryConf, it) }
    slide(QoveryInfos.copy(4)) { child(QoveryAPI, it) }
    slide(QoveryInfos.copy(7, outTransitions = Flip)) { child(QoveryCLI, it) }
}
