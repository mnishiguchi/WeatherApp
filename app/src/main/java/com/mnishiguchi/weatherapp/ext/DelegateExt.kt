package com.mnishiguchi.weatherapp.ext

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

object DelegateExt {
//    fun <T> nonNullableSingleValue() = NonNullableSingleValueVar<T>()

    fun <T> preference(context: Context, name: String, default: T): Preference<T> {
        return Preference(context, name, default)
    }
}

//class NonNullableSingleValueVar<T> {
//
//    private var value: T? = null
//
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
//        return value ?: throw IllegalStateException("${property.name} not initialized")
//    }
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//        this.value =
//                if (this.value == null) value
//                else throw IllegalStateException("${property.name} already initialized")
//    }
//}

/**
 * Represents a delegate that performs queries and saves data to the SharedPreferences.
 */
class Preference<T>(val context: Context, val name: String, val default: T) {

    // Loaded when we use it for the first time.
    val sharedPref: SharedPreferences by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }

    // Implement the custom getter for delegation.
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getPreference(name, default)
    }

    // Implement the custom setter for delegation.
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getPreference(name: String, default: T): T {
        // Get a preference editor instance.
        return with(sharedPref) {
            // Detect the type from the specified default value.
            // Look up a value of that type.
            val result: Any = when (default) {
                is Long    -> getLong(name, default)
                is String  -> getString(name, default)
                is Int     -> getInt(name, default)
                is Boolean -> getBoolean(name, default)
                is Float   -> getFloat(name, default)
                else -> throw IllegalArgumentException("This type cannot be saved to SharedPreferences")
            }

            result as T // Cast the value and return it.
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun setPreference(name: String, value: T) {
        // Get a preference editor instance.
        with(sharedPref.edit()) {
            when (value) {
                // Put a value with the correct type to the preference editor.
                is Long    -> putLong(name, value)
                is String  -> putString(name, value)
                is Int     -> putInt(name, value)
                is Boolean -> putBoolean(name, value)
                is Float   -> putFloat(name, value)
                else -> throw IllegalArgumentException("This type cannot be saved to SharedPreferences")
            }.apply() // Apply the change to the preference editor.
        }
    }
}