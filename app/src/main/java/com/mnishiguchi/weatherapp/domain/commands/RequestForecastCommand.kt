package com.mnishiguchi.weatherapp.domain.commands

import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.domain.providers.ForecastProvider

/**
 * A command that requests the forecasts to the API and convert it to domain classes.
 */
class RequestForecastCommand(val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.findByZipCodeAndDays(zipCode, DAYS)
    }
}
