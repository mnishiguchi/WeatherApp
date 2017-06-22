package com.mnishiguchi.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.commands.RequestForecastListCommand
import com.mnishiguchi.weatherapp.ext.DelegateExt
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

    // Data bound to SharedPreferences
    val zipCode: Long by DelegateExt.preference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP_CODE)

    // Use lazy assignment so tha we can safely access the toolbar.
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToolbarToScroll(forecastList)
    }

    override fun onResume() {
        // Every time the activity is resumed, it refreshes the data, just in case the zip code changed.
        // There are more efficient ways to do this, by checking whether the zip code really changed
        // before requesting the forecast again, for instance.
        // But the requested info is already saved in a local database, so this solution is not that bad.
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() {
        // Execute code in another thread.
        doAsync {
            // Request data for the zip code from the user's preferences.
            val result = RequestForecastListCommand(zipCode).execute()

            // Return to the main thread.
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(
                            DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }

                // Set a title in the tool bar.
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
    }
}


