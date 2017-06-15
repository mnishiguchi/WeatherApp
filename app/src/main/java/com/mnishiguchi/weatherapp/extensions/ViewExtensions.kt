package com.mnishiguchi.weatherapp.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Anko provides a ctx property for activities and fragments etc, which returns the context.
 * But it does not for views. So we defines an extension function for views on our own so that we
 * can access contexts in a consistent way across the application.
 */
val View.ctx: Context
    get() = context

/**
 * The line that inflates the view is the same on any adapters most of the time.
 * We might as well give ViewGroup the ability to inflate views.
 * https://antonioleiva.com/extension-functions-kotlin/
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}