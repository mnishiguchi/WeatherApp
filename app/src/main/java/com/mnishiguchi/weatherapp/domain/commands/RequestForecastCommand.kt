package com.mnishiguchi.weatherapp.domain.commands

import com.mnishiguchi.weatherapp.data.server.ForecastRequest
import com.mnishiguchi.weatherapp.data.server.ForecastDataMapper
import com.mnishiguchi.weatherapp.domain.ForecastList

/**
 * A command that requests the forecasts to the API and convert it to domain classes.
 */
class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().toDomain(zipCode, forecastRequest.execute())
    }
}
