package com.mnishiguchi.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.commands.RequestForecastListCommand
import com.mnishiguchi.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * The main activity that is a list view. A detail activity opens when a list item is clicked.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)

        // Execute code in another thread.
        doAsync {
            val result = RequestForecastListCommand(20001).execute()

            // Return to the main thread.
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID        to it.id,
                                                  DetailActivity.CITY_NAME to result.city)
                }

                // Set a title in the tool bar.
                title = "${result.city} (${result.country})"
            }
        }
    }

}


