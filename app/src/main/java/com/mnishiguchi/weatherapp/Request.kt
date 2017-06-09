package com.mnishiguchi.weatherapp

import android.util.Log
import java.net.URL

class Request(val url: String) {

    fun run() {
        // Use readText, an extension function from the Kotlin standard library.
        // This method is not recommended for huge responses.
        // the App must use the Internet permission in the app/src/main/AndroidManifest.xml.
        val forecastJsonString = URL(url).readText()

        // Output the JSON in the Logcat.
        Log.d(javaClass.simpleName, forecastJsonString)
    }
}