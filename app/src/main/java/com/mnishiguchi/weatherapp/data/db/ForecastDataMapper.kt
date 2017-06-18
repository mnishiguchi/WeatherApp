package com.mnishiguchi.weatherapp.data.db

import com.mnishiguchi.weatherapp.domain.Forecast
import com.mnishiguchi.weatherapp.domain.ForecastList

/**
 * Performs conversions between domain models and database entities.
 */
class ForecastDataMapper {

    /*
     * domain model => db entity
     */

    fun fromDomain(forecastList: ForecastList) = with(forecastList) {
        CityEntity(
                id,
                city,
                country,
                dailyForecast.map { forecastEntityFromDomain(id, it) })
    }

    private fun forecastEntityFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        ForecastEntity(
                date,
                description,
                high,
                low,
                iconUrl,
                cityId)
    }

    /*
     * db entity => domain model
     */

    fun toDomain(cityEntity: CityEntity) = with(cityEntity) {
        ForecastList(
                _id,
                city,
                country,
                dailyForecast.map { forecastEntityToDomain(it) })
    }

    private fun forecastEntityToDomain(forecastEntity: ForecastEntity) = with(forecastEntity) {
        Forecast(
                date,
                description,
                high,
                low,
                iconUrl)
    }
}
