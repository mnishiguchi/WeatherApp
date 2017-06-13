package com.mnishiguchi.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.model.Forecast
import com.mnishiguchi.weatherapp.domain.model.ForecastList
import com.mnishiguchi.weatherapp.ui.utils.ctx
import com.mnishiguchi.weatherapp.ui.utils.inflate
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

// When a function accept an interface with a single function, it can be substituted by a lambda.
class ForecastListAdapter(val forecastList: ForecastList, val itemClick: (Forecast) -> Unit)
        : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_forecast)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size


    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        // Views
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)

        /**
         * Binds a forecast item to a new view.
         */
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}