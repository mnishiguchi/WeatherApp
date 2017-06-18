package com.mnishiguchi.weatherapp.data.db

import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.domain.providers.ForecastProvider
import com.mnishiguchi.weatherapp.extensions.clear
import com.mnishiguchi.weatherapp.extensions.parseList
import com.mnishiguchi.weatherapp.extensions.parseOpt
import com.mnishiguchi.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * A data source that handles database CRUD operations.
 */
class ForecastDao(val dbHelper: DbHelper = DbHelper.instance,
                  val forecastDataMapper: ForecastDataMapper = ForecastDataMapper())
    : ForecastProvider.DataSource {

    override fun forecastList(locationId: Long, date: Long) = dbHelper.use {

        val dailyForecast =
                select(ForecastEntity.TABLE_NAME)
                .whereArgs(
                        "${ForecastEntity.CITY_ID} = {cityId} AND ${ForecastEntity.DATE} >= {date}",
                        "cityId" to locationId,
                        "date"       to date)
                .parseList { ForecastEntity(HashMap(it)) }

        val location =
                select(CityEntity.TABLE_NAME)
                .whereArgs(
                        "${CityEntity.ID} = {cityId}",
                        "cityId" to locationId)
                .parseOpt { CityEntity(HashMap(it), dailyForecast) }

        if (location != null) forecastDataMapper.toDomain(location) else null
    }

    fun saveForecastList(forecast: ForecastList) = dbHelper.use {

        clear(CityEntity.TABLE_NAME)
        clear(ForecastEntity.TABLE_NAME)

        with(forecastDataMapper.fromDomain(forecast)) {
            insert(CityEntity.TABLE_NAME, *map.toVarargArray())

            dailyForecast.forEach {
                insert(ForecastEntity.TABLE_NAME, *it.map.toVarargArray())
            }
        }
    }
}