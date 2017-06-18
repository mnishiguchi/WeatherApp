package com.mnishiguchi.weatherapp.data.server

import com.mnishiguchi.weatherapp.data.db.ForecastDao
import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.domain.providers.ForecastProvider

/**
 * A forecast data source that fetches forecast data from the API server and cache it to database.
 */
class ForecastServer(val forecastDataMapper: ForecastDataMapper = ForecastDataMapper(),
                     val forecastDao: ForecastDao = ForecastDao())
    : ForecastProvider.DataSource {

    override fun forecastList(zipCode: Long, date: Long): ForecastList? {

        // Fetch forecast data from the API server.
        val response = ForecastRequest(zipCode).execute()

        // Convert the response to domain model.
        val domainForecast = forecastDataMapper.toDomain(zipCode, response)

        // Save the domain model to database.
        forecastDao.saveForecastList(domainForecast)

        // Returns the values from the database because we need the row ids.
        return forecastDao.forecastList(zipCode, date)
    }

    /**
     * ForecastServer will never use this function because the info will be always cached
     * in the database. So if this function is invoked, something must be wrong.
     */
    override fun forecast(id: Long): Nothing {
        throw UnsupportedOperationException()
    }

}