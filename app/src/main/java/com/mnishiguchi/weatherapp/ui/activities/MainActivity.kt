package com.mnishiguchi.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.commands.RequestForecastCommand
import com.mnishiguchi.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)

        // Execute code in another thread
        doAsync {
            val result = RequestForecastCommand(20001).execute()

            // Return to the main thread
            uiThread {
                forecastList.adapter = ForecastListAdapter(result, { toast(it.description) })
            }
        }
    }

}


