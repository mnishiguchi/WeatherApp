package com.mnishiguchi.weatherapp.data.db

import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.extensions.clear
import com.mnishiguchi.weatherapp.extensions.parseList
import com.mnishiguchi.weatherapp.extensions.parseOpt
import com.mnishiguchi.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Handles database CRUD operations.
 */
class ForecastDao(val dbHelper: DbHelper = DbHelper.instance,
                  val forecastDataMapper: ForecastDataMapper = ForecastDataMapper()) {

    fun findForecast(locationId: Long, date: Long) = dbHelper.use {

        val dailyForecast =
                select(ForecastEntity.TABLE_NAME)
                .whereArgs(
                        "${ForecastEntity.LOCATION_ID} = {locationId} AND ${ForecastEntity.DATE} >= {date}",
                        "locationId" to locationId,
                        "date"       to date)
                .parseList { ForecastEntity(HashMap(it)) }

        val location =
                select(LocationEntity.TABLE_NAME)
                .whereArgs(
                        "${LocationEntity.ID} = {locationId}",
                        "locationId" to locationId)
                .parseOpt { LocationEntity(HashMap(it), dailyForecast) }

        if (location != null) forecastDataMapper.toDomain(location) else null
    }

    fun saveForecast(forecast: ForecastList) = dbHelper.use {

        clear(LocationEntity.TABLE_NAME)
        clear(ForecastEntity.TABLE_NAME)

        with(forecastDataMapper.fromDomain(forecast)) {
            insert(LocationEntity.TABLE_NAME, *map.toVarargArray())

            dailyForecast.forEach {
                insert(ForecastEntity.TABLE_NAME, *it.map.toVarargArray())
            }
        }
    }
}