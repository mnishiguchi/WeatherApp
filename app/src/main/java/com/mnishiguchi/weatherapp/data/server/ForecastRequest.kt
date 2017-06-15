package com.mnishiguchi.weatherapp.data.server

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class ForecastRequest(val zipCode: Long) {

    companion object {
        // https://openweathermap.org/forecast5#other
        private val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily"
        private val API_KEY = "15646a06818f61f7b8d7823ca833e1ce"

        private fun apiUrl(zipCode: Long): String {
            return "${BASE_URL}?APPID=${API_KEY}&q=$zipCode&mode=json&units=imperial&cnt=7"
        }
    }

    /**
     * Requests the forecasts to the JSON API and returns it as ForecastResponse data model.
     */
    fun execute(): ForecastResponse {
        // Use readText, an extension function from the Kotlin standard library.
        // This method is not recommended for huge responses.
        // the App must use the Internet permission in the app/src/main/AndroidManifest.xml.

        val url = apiUrl(zipCode)
        Log.d(javaClass.simpleName, url)

        val forecastJsonString = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonString)

        return Gson().fromJson(forecastJsonString, ForecastResponse::class.java)
    }
}
