package com.mnishiguchi.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.find

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
    }
}


