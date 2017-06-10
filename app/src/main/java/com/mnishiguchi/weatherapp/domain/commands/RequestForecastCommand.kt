package com.mnishiguchi.weatherapp.domain.commands

import com.mnishiguchi.weatherapp.data.ForecastRequest
import com.mnishiguchi.weatherapp.domain.mappers.ForecastDataMapper
import com.mnishiguchi.weatherapp.domain.model.ForecastList

/**
 * A command that requests the forecasts to the API and convert it to domain classes.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}
