package ws.utils

import kotlinx.css.*

// CSS utils
fun CSSBuilder.landscape(block: RuleSet): Rule = media("(orientation: landscape)", block)
fun CSSBuilder.landscapeMobile(maxWidth: Int = 980, block: RuleSet): Rule = media("(orientation: landscape) and (max-width: ${maxWidth}px)", block)
fun CSSBuilder.portrait(block: RuleSet): Rule = media("(orientation: portrait)", block)
fun CSSBuilder.portraitMobile(maxHeight: Int = 980, block: RuleSet): Rule = media("(orientation: portrait) and (max-height: ${maxHeight}px)", block)
fun CSSBuilder.rangeWidth(min: Int, max: Int, block: RuleSet): Rule = media("(min-width: ${min}px) and (max-width: ${max}px)", block)
fun CSSBuilder.rangeHeight(min: Int, max: Int, block: RuleSet): Rule = media("(min-height: ${min}px) and (max-height: ${max}px)", block)
fun CSSBuilder.maxWidth(max: Int, block: RuleSet): Rule = media("(max-width: ${max}px)", block)
fun CSSBuilder.minWidth(min: Int, block: RuleSet): Rule = media("(min-width: ${min}px)", block)
fun CSSBuilder.maxHeight(max: Int, block: RuleSet): Rule = media("(max-height: ${max}px)", block)
fun CSSBuilder.minHeight(min: Int, block: RuleSet): Rule = media("(min-height: ${min}px)", block)
fun CSSBuilder.maxSize(max: Int, block: RuleSet): Rule = media("(max-width: ${max}px), (max-height: ${max}px)", block)
fun CSSBuilder.maxSizeStrict(max: Int, block: RuleSet): Rule = media("(max-width: ${max}px) and (max-height: ${max}px)", block)
fun CSSBuilder.minSize(min: Int, block: RuleSet): Rule = media("(min-height: ${min}px), (min-width: ${min}px)", block)
fun CSSBuilder.minSizeStrict(min: Int, block: RuleSet): Rule = media("(min-height: ${min}px) and (min-width: ${min}px)", block)


val FontWeight.Companion.hairline get() = w100
val FontWeight.Companion.ultraLight get() = w200
val FontWeight.Companion.light get() = w300
val FontWeight.Companion.regular get() = w400
val FontWeight.Companion.medium get() = w500
val FontWeight.Companion.semiBold get() = w600
//val FontWeight.Companion.bold get() = w700
val FontWeight.Companion.extraBold get() = w800
val FontWeight.Companion.black get() = w900
