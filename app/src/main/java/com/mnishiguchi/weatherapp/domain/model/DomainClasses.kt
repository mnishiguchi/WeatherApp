package com.mnishiguchi.weatherapp.domain.model

// The domain models that correspond to Forecast Result data

data class ForecastList(val city: String,
                        val country: String,
                        val dailyForecast: List<Forecast>) {

    // Implementing size and overloading get help simplify the adapter's onBindViewHolder and
    // getItemCount functions.

    val size: Int
        get() = dailyForecast.size

    // a[i]	=> a.get(i)
    // https://kotlinlang.org/docs/reference/operator-overloading.html#indexed
    operator fun get(position: Int): Forecast = dailyForecast[position]
}

data class Forecast(val date: String,
                    val description: String,
                    val high: Int,
                    val low: Int)
