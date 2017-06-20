package com.mnishiguchi.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.commands.RequestForecastListCommand
import com.mnishiguchi.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * The main activity that is a list view. A detail activity opens when a list item is clicked.
 */
class MainActivity : AppCompatActivity(), ToolbarManager {

    // Use lazy assignment so tha we can safely access the toolbar.
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToolbarToScroll(forecastList)

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
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
    }

}


