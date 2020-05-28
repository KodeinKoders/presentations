package ws.utils

import kotlinext.js.jsObject
import kotlinx.html.TD
import kotlinx.html.TR
import react.*
import react.dom.RDOMBuilder
import react.dom.td
import react.dom.tr
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// https://github.com/JetBrains/kotlin-wrappers/issues/125
operator fun <P> FunctionalComponent<P>.getValue(thisRef: Any?, property: KProperty<*>): FunctionalComponent<P> {
    this.asDynamic().displayName = property.name.capitalize()
    return this
}

class StateDelegate<T>(state: Pair<T, RSetState<T>>) : ReadWriteProperty<Any?, T> {

    private var value = state.first
    private val set = state.second

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (value == this.value) return
        this.value = value
        set(value)
    }

}

operator fun <T> Pair<T, RSetState<T>>.provideDelegate(thisRef: Any?, property: KProperty<*>) = StateDelegate(this)
