package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.*
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val bindingTypes = listOf(
    Slide(
        "binding-types",
        stateCount = 10
    ) { state ->
        H1 {
            Text("Types of bindings")
        }
        SourceCode(
            lang = "kotlin",
            code = """
                val di = DI {
                «lPr:
                    «bPr:bind«bBind:Provider» { AppHttpClient(«par:instance()») }»
                »«lFa:
                    «bFa:bind«bBind:Factory» { id: String -> UserAPI(id«par:, instance()») }»
                »«lSi:
                    «bSi:bind«bBind:Singleton»«gen:<DB>» { UserDB() }»
                »«lIn:
                    «bIn:bind«bBind:Instance»«gen:<Params>» { applicationParameters }»
                »«lMu:
                    «bMu:bind«bBind:Multiton» { sc: Secret -> CrypDB(sc«par:, instance()») }»
                »
                }
            """.trimIndent()
        ) {
            "lPr" { lineHeight(state >= 1) }
            "bPr" { zoomed(state == 1, 1.2) }
            "lFa" { lineHeight(state >= 2) }
            "bFa" { zoomed(state == 2, 1.2) }
            "lSi" { lineHeight(state >= 3) }
            "bSi" { zoomed(state == 3, 1.2) }
            "lIn" { lineHeight(state >= 4) }
            "bIn" { zoomed(state == 4, 1.2) }
            "lMu" { lineHeight(state >= 5) }
            "bMu" { zoomed(state == 5, 1.2) }
            "par" { fontGrow(state >= 7) }
            "gen" { fontGrow(state >= 8) }
            "bBind" { zoomed(state == 9) }
        }
    },
    Slide(
        "tagged-bindings",
        stateCount = 3
    ) { state ->
        H1 {
            Text("Tags")
        }
        SourceCode(
            lang = "kotlin",
            code = """
                val di = DI {
                    bind«inst:Instance<String>»«cst:Constant»(«tag:tag = "apiUrl"») { "https://my.app/api" }
                    bind«inst:Instance<String>»«cst:Constant»(«tag:tag = "creds"») { "api-user:SJMcHa7" }
                }
            """.trimIndent()
        ) {
            "tag" { fontGrow(state >= 1) }
            "inst" { fontGrow(state < 2) }
            "cst" { fontGrow(state >= 2) }
        }
    }
)