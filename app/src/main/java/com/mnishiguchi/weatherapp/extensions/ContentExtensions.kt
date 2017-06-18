package com.mnishiguchi.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by masa on 6/18/17.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)
