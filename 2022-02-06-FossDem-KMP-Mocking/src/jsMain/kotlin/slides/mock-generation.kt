package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.SourceCode
import net.kodein.pres.sourcecode.fontGrow
import net.kodein.pres.sourcecode.lineHeight
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div


val generation = listOf(
    Slide(
        name = "mock-generation",
        stateCount = 9
    ) { state ->
        SourceCode(
            lang = "kotlin",
            code = """
                interface Database {
                «pi:    val filePath: String
                »    fun store(model: Model)
                «li:    fun load(id: String): Model
                »}
    
                «mock:class DatabaseMock«proxy:(val mocker: Mocker)» : Database {
    
                «pc:    val filePath: String get() =
                        mocker.register(this, "get:filePath") as String
    
                »    fun store(model: Model) =
                «sc:        mocker.register(this, "store(Model)", model) «cast:as Unit»
                »«lc:
                    fun load(id: String): Model =
                        mocker.register(this, "load(String)", id) «cast:as Model»
                »
                }
                »
            """.trimIndent()
        ) {
            "mock" { lineHeight(state >= 1) }
            "proxy" { fontGrow(state >= 2) }

            "sc" { lineHeight(state >= 3) }
            "li" { lineHeight(state >= 4) }
            "lc" { lineHeight(state >= 5) }

            "cast" { fontGrow(state >= 6) }

            "pi" { lineHeight(state >= 7) }
            "pc" { lineHeight(state >= 8) }
        }
    },

    Slide(
        name = "fake-generation",
        stateCount = 7
    ) { state ->
        Div({
            css {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Row)
                justifyContent(JustifyContent.SpaceBetween)
                width(100.percent)
            }
        }) {
            SourceCode(
                lang = "kotlin",
                code = """
                data class Model(
                    val id: String,
                «t1:    val timestamp: Long,
                »«t2:    val content: Content,
                »«t3:    val state: State,
                »«t4:    val sync: Boolean,
                »«t5:    val comment: String?,
                »)
            """.trimIndent()
            ) {
                "t1" { lineHeight(state >= 2) }
                "t2" { lineHeight(state >= 3) }
                "t3" { lineHeight(state >= 4) }
                "t4" { lineHeight(state >= 5) }
                "t5" { lineHeight(state >= 6) }
            }
            SourceCode(
                lang = "kotlin",
                code = """
                «f:internal fun fakeModel() =
                    Model(
                        id = "",
                «t1:        timestamp = 0,
                »«t2:        content = fakeContent(),
                »«t3:        state = State.AWAIT,
                »«t4:        sync = false,
                »«t5:        comment = null,
                »    )
                »
            """.trimIndent()
            ) {
                "f" { lineHeight(state >= 1) }
                "t1" { lineHeight(state >= 2) }
                "t2" { lineHeight(state >= 3) }
                "t3" { lineHeight(state >= 4) }
                "t4" { lineHeight(state >= 5) }
                "t5" { lineHeight(state >= 6) }
            }
        }
    }
)
