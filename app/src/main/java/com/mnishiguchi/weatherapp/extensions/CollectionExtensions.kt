package com.mnishiguchi.weatherapp.extensions

/**
 * Converts a map with a nullable values (a requirement of the map delegate)
 * to an array of pairs with non-nullable values.
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> {
    return map({ Pair(it.key, it.value!!) }).toTypedArray()
}