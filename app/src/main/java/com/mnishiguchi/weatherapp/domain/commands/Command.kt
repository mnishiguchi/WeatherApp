package com.mnishiguchi.weatherapp.domain.commands

/**
 * Commands will execute an operation and return an object of the class specified in its generic type.
 */
interface Command<out T> {
    fun execute(): T
}