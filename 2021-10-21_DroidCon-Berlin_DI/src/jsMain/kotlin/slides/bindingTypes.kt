package slides

import net.kodein.pres.Slide
import net.kodein.pres.sourcecode.*
import net.kodein.theme.compose.web.css
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


val bindingTypes = Slide(
    "binding-types",
    stateCount = 9
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
                «bSi:bind«bBind:Singleton» { AppDB() }»
            »«lIn:
                «bIn:bind«bBind:Instance» { applicationParameters }»
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
        "bBind" { zoomed(state == 8) }
    }
}
