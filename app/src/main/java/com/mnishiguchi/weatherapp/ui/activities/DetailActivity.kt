package com.mnishiguchi.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.Forecast
import com.mnishiguchi.weatherapp.domain.commands.RequestForecastCommand
import com.mnishiguchi.weatherapp.ext.color
import com.mnishiguchi.weatherapp.ext.textColor
import com.mnishiguchi.weatherapp.ext.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.text.DateFormat

/**
 * The detail activity displays the detail of a forecast.
 * Receives a couple of parameters from the main activity.
 */
class DetailActivity : AppCompatActivity(), ToolbarManager {

    companion object {
        // the forecast id - used to retrieve the data from database
        val ID = "DetailActivity:id"

        // the city name - used in the toolbar
        val CITY_NAME = "DetailActivity:cityName"
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        // Execute code in another thread.
        doAsync {
            val result = RequestForecastCommand(intent.getLongExtra(ID, -1)).execute()

            // Return to the main thread.
            uiThread {
                bindForecast(result)
            }
        }
    }

    /**
     * Binds a forecast model to the view.
     */
    private fun bindForecast(forecast: Forecast) = with(forecast) {
        toolbar.subtitle = date.toDateString(DateFormat.FULL)

        Picasso.with(ctx).load(iconUrl).into(icon)
        weatherDescription.text = description
        bindTemperature(high to maxTemperature, low to minTemperature)
    }

    /**
     * Binds min and max temperature values to the corresponding view components.
     */
    private fun bindTemperature(vararg views: Pair<Int, TextView>) {
        views.forEach {
            it.second.text = "${it.first}º"
            it.second.textColor = color(when (it.first) {
                in -50..0 -> android.R.color.holo_red_dark
                in 0..15  -> android.R.color.holo_orange_dark
                else      -> android.R.color.holo_green_dark
            })
        }
    }
}
