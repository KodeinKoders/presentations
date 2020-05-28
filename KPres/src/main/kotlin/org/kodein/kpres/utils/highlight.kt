package org.kodein.kpres.utils

import org.w3c.dom.HTMLElement

external interface HighlightJs {
    interface BaseHighlightResult {
        val language: String
        val relevance: Int
        val value: String
    }

    interface HighlightResult : BaseHighlightResult {
        val top: dynamic
    }

    fun highlight(name: String, value: String, ignoreIllegals: Boolean = definedExternally): HighlightResult

    interface HighlightAutoResult : BaseHighlightResult {
        @JsName("second_best")
        val secondBest: BaseHighlightResult?
    }

    fun highlightAuto(value: String, languageSubset: Array<String> = definedExternally)

    fun fixMarkup(value: String)

    fun highlightBlock(value: HTMLElement)

    interface Options {
        var tabReplace: String
        var useBR: Boolean
        var classPrefix: String
        val languages: Boolean
    }

    fun configure(options: Options)

    fun initHighlighting()

    fun initHighlightingOnLoad()

    fun registerLanguage(name: String, language: (dynamic) -> dynamic)

    fun listLanguages(): Array<String>

    fun getLanguage(name: String): dynamic

    fun debugMode()
}

@JsModule("highlight.js")
external val hljs: HighlightJs
