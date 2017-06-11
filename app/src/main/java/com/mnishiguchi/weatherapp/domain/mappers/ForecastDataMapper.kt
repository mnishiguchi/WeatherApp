package com.mnishiguchi.weatherapp.domain.mappers

import com.mnishiguchi.weatherapp.data.Forecast
import com.mnishiguchi.weatherapp.data.ForecastResult
import com.mnishiguchi.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

// Alias Forecast model because we are using two classes here.
// This way, we don’t need to write the complete package names.
import com.mnishiguchi.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {

    /**
     * Converts ForecastResult data model to ForecastList domain model.
     */
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name,
                            forecast.city.country,
                            convertForecastListToDomain(forecast.list))
    }

    /* private functions */

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        // Loop over the collection and return a new list with the converted items.
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                             forecast.weather[0].description,
                             forecast.temp.max.toInt(),
                             forecast.temp.min.toInt(),
                             generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
}