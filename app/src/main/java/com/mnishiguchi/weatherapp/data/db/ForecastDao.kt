package com.mnishiguchi.weatherapp.data.db

import com.mnishiguchi.weatherapp.domain.Forecast
import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.domain.providers.ForecastProvider
import com.mnishiguchi.weatherapp.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * A data source that handles database CRUD operations.
 */
class ForecastDao(val dbHelper: DbHelper = DbHelper.instance,
                  val forecastDataMapper: ForecastDataMapper = ForecastDataMapper())
    : ForecastProvider.DataSource {

    override fun forecastList(zipCode: Long, date: Long) = dbHelper.use {

        val dailyForecast =
                select(ForecastEntity.TABLE_NAME)
                .whereSimple(
                        "${ForecastEntity.CITY_ID} = ? AND ${ForecastEntity.DATE} >= ?",
                        zipCode.toString(),date.toString())
                .parseList { ForecastEntity(HashMap(it)) }

        val city =
                select(CityEntity.TABLE_NAME)
                .whereSimple("${CityEntity.ID} = ?", zipCode.toString())
                .parseOpt { CityEntity(HashMap(it), dailyForecast) }

        // if (city != null) forecastDataMapper.cityEntityToDomain(city) else null
        city?.let { forecastDataMapper.cityEntityToDomain(it) }
    }

    override fun forecast(id: Long): Forecast? = dbHelper.use {

        val forecast =
                select(ForecastEntity.TABLE_NAME).byId(id)
                .parseOpt { ForecastEntity(HashMap(it)) }

        // if (forecast != null) forecastDataMapper.forecastEntityToDomain(forecast) else null
        forecast?.let { forecastDataMapper.forecastEntityToDomain(it) }
    }

    fun saveForecastList(forecast: ForecastList) = dbHelper.use {

        clear(CityEntity.TABLE_NAME)
        clear(ForecastEntity.TABLE_NAME)

        with(forecastDataMapper.cityEntityFromDomain(forecast)) {
            insert(CityEntity.TABLE_NAME, *map.toVarargArray())

            dailyForecast.forEach {
                insert(ForecastEntity.TABLE_NAME, *it.map.toVarargArray())
            }
        }
    }
}