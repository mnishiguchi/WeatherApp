package com.mnishiguchi.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.mnishiguchi.weatherapp.R
import com.mnishiguchi.weatherapp.domain.Forecast
import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.extensions.ctx
import com.mnishiguchi.weatherapp.extensions.inflate
import com.mnishiguchi.weatherapp.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

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

        /**
         * Binds a forecast item to a new view.
         */
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}