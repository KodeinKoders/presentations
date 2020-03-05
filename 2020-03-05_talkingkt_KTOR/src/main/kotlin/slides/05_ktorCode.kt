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
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*


private val FrameworkAndDependencies by functionalComponent<SlideContentProps> { props ->
    titledContent("Project configuration") {
        bulletList(props) {
            val currentState = props.state
            bulletCode(currentState, 1, "Gradle Plugins", "kotlin",
            """
            plugins {
                kotlin("jvm") version "1.3.70"
                application
            }
            
            application {
                mainClassName = "io.ktor.server.netty.EngineMain"
            }
            """.trimIndent())
            bulletCode(currentState, 2, "application.conf", "kotlin",
                    """
            ktor {
                deployment {
                    port = 8001
                    port = /$/{?PORT}
                }
                application {
                    modules = [
                        org.kodein.api.demo.ApplicationKt.load
                     ]
                }
            }
            """.trimIndent())
            bulletCode(currentState, 3, "Ktor deps", "kotlin",
            """
            // Ktor v1.3.0
            implementation(ktor("server-netty"))
            implementation(ktor("jackson")) 
            implementation(ktor("auth"))
            implementation(ktor("html-builder"))
            """.trimIndent())
            bulletCode(currentState, 4, "Database deps", "kotlin",
            """
            // Exposed v0.20.2
            implementation(exposed("core"))
            implementation(exposed("dao"))
            implementation(exposed("jdbc"))
            implementation(exposed("java-time"))
            // Database HikariCP + PostgreSQL
            implementation("com.zaxxer:HikariCP:3.4.1")
            implementation("org.postgresql:postgresql:42.2.9")
            """.trimIndent())
            bulletCode(currentState, 5, "Third-party deps", "kotlin",
            """
            // Kodein-DI v7.0.0
            implementation(kodein())
            implementation(kodein("framework-ktor-server-controller-jvm"))
            // Qovery client v0.2.1
            implementation("com.qovery:client:0.2.1")
            """.trimIndent())
        }
    }
}
private val DataModel by functionalComponent<SlideContentProps> { props ->
    titledContent("Defining the data model") {
        bulletList(props) {
            val currentState = props.state
            bulletCode(currentState, 1, "Domain objects", "kotlin",
            """
            data class User(
                val id: Int = -1,
                val username: String,
                @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
                val password: String
            ): Principal
            
            data class Item(
                val id: Int = -1,
                val label: String,
                val dueDate: LocalDate,
                val user: User?
            )
            """.trimIndent())
            bulletCode(currentState, 2, "Database objects", "kotlin",
            """
            object Users : IntIdTable() {
                val username = varchar("username", 50)
                val password = varchar("password", 50)
            }
            
            object Items : IntIdTable() {
                val label = varchar("label", 50)
                val dueDate = date("dueDate")
                val userId = reference("user_id", Users, 
                                ReferenceOption.SET_NULL).nullable()
            }
            """.trimIndent())
            bulletCode(currentState, 3, "Data access objects", "kotlin",
            """
            class UserEntity(id: EntityID<Int>) : IntEntity(id) {
                companion object : IntEntityClass<UserEntity>(Users)
                var username by Users.username
                var password by Users.password
            }
            
            class ItemEntity(id: EntityID<Int>) : IntEntity(id) {
                companion object : IntEntityClass<ItemEntity>(Items)
                var label by Items.label
                var dueDate by Items.dueDate
                var user by UserEntity optionalReferencedOn Items.userId
            }
            """.trimIndent())
            bulletCode(currentState, 4, "Database configuration", "kotlin",
            """
            object DatabaseConfig {
                fun init() {
                    Database.connect(hikari())
                    transaction {
                        SchemaUtils.drop(Users, Items)
                        SchemaUtils.create(Users, Items)
                        // Insert data...
                    }
                }
                private fun hikari(): HikariDataSource { ... }
            }
            """.trimIndent())
            bulletCode(currentState, 5, "Transaction helper", "kotlin",
            """
            suspend fun <T> dbQuery(block: () -> T): T =
                        withContext(Dispatchers.IO) {
                            transaction { block() }
                        }
            """.trimIndent())
        }
    }
}
private val BusinessServices by functionalComponent<SlideContentProps> { props ->
    titledContent("Business services") {
        bulletList(props) {
            val currentState = props.state
            bulletCode(currentState, 1, "User service", "kotlin",
            """
            class UserService {
                suspend fun createOrUpdate(user: User): User? { ... }
                suspend fun check(user: User): User? = dbQuery { ... }
                suspend fun delete(id: Int) = dbQuery { ... }
            }
            
            internal fun UserEntity.asDTO() = User(
                id = id.value,
                username = username,
                password = password
            )
            """.trimIndent())
            bulletCode(currentState, 2, "Item service", "kotlin",
            """
            class ItemService {
                suspend fun findAll(userId: Int? = null) = dbQuery { ... }
                suspend fun createOrUpdate(item: Item): Item? { ... }
                suspend fun delete(id: Int) = dbQuery {
                    ItemEntity.findById(id)?.delete()
                }
            }
            
            internal fun ItemEntity.asDTO() = Item( ... )
            """.trimIndent())
        }
    }
}

fun PresentationBuilder.ktorCode() {
    slide(SlideInfos(6)) { child(FrameworkAndDependencies, it) }
    slide(SlideInfos(6)) { child(DataModel, it) }
    slide(SlideInfos(3)) { child(BusinessServices, it) }
    slide { showCode() }
}

