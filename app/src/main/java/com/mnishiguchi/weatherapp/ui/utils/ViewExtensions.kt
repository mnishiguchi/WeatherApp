package com.mnishiguchi.weatherapp.ui.utils

import android.content.Context
import android.view.View

/**
 * Anko provides a ctx property for activities and fragments etc, which returns the context.
 * But it does not for views. So we defines an extension function for views on our own so that we
 * can access contexts in a consistent way across the application.
 */
val View.ctx: Context
    get() = context
