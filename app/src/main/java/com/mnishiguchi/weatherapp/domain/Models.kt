package com.mnishiguchi.weatherapp.domain

// The domain models that correspond to Forecast Result data.

data class ForecastList(val id: Long,
                        val city: String,
                        val country: String,
                        val dailyForecast: List<Forecast>) {

    // Implementing size and overloading get help simplify the adapter's onBindViewHolder and
    // getItemCount functions.

    val size: Int
        get() = dailyForecast.size

    // a[i]	=> a.get(i)
    // https://kotlinlang.org/docs/reference/operator-overloading.html#indexed
    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast(val id: Long,
                    val date: Long,
                    val description: String,
                    val high: Int,
                    val low: Int,
                    val iconUrl: String)