package com.mnishiguchi.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon - 75/55",
            "Tue - 82/60",
            "Wed - 88/66",
            "Thu - 93/69",
            "Fri - 94/72",
            "Sat - 95/71",
            "Sun - 96/73"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        val apiUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

        // Execute code in another thread
        doAsync {
            Request(apiUrl).run()
            // Return to the main thread
            uiThread {
                longToast("Request performed")
            }
        }
    }

}


