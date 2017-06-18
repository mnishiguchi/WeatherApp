package com.mnishiguchi.weatherapp.domain.commands

import com.mnishiguchi.weatherapp.domain.Forecast
import com.mnishiguchi.weatherapp.domain.providers.ForecastProvider

class RequestForecastCommand(val id: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<Forecast> {

    override fun execute() = forecastProvider.findById(id)
}
