package com.mnishiguchi.weatherapp.data.server

import com.mnishiguchi.weatherapp.domain.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.mnishiguchi.weatherapp.data.server.Forecast as ServerForecast
import com.mnishiguchi.weatherapp.domain.Forecast as DomainForecast

/**
 * Performs conversions between forecast response and domain model.
 */
class ForecastDataMapper {

    /*
     * response => domain model
     */

    fun toDomain(zipCode: Long, forecast: ForecastResponse) = with(forecast) {
        ForecastList(
                zipCode,
                city.name,
                city.country,
                dailyForecastToDomain(list))
    }

    private fun dailyForecastToDomain(list: List<ServerForecast>): List<DomainForecast> {
        return list.mapIndexed { i, forecast ->
            // Convert the time to Unix time before creating a domain model
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            forecastToDomain(forecast.copy(dt = dt))
        }
    }

    private fun forecastToDomain(forecast: ServerForecast) = with(forecast) {
        DomainForecast(
                dt,
                weather[0].description,
                temp.max.toInt(),
                temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}