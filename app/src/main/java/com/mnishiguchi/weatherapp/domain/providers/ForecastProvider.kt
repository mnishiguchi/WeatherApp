package com.mnishiguchi.weatherapp.domain.providers

import com.mnishiguchi.weatherapp.data.db.ForecastDao
import com.mnishiguchi.weatherapp.data.server.ForecastServer
import com.mnishiguchi.weatherapp.domain.Forecast
import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.extensions.firstResult

/**
 * Workflow:
 * - Request the data from the database
 * - Check if there is data for the corresponding week
 *   + If the data is found
 *     * return it
 *   + Otherwise
 *     * send a request to the server
 *     * save it to database
 *     * return it
 *
 */
class ForecastProvider(val sources: List<DataSource> = ForecastProvider.SOURCES) {

    interface DataSource {
        /**
         * @param zipCode
         * @param date a unix timestamp
         */
        fun forecastList(zipCode: Long, date: Long): ForecastList?

        /**
         * @param id
         */
        fun forecast(id: Long): Forecast?
    }

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24

        // Specify data sources in the order of priority.
        val SOURCES = listOf(ForecastDao(), ForecastServer())
    }

    fun findByZipCode(zipCode: Long, numberOfDays: Int): ForecastList {
        return requestToSources {
            val response = it.forecastList(zipCode, todayTimeSpan())
            if (response != null && response.size >= numberOfDays) response else null
        }
    }


    fun findById(id: Long): Forecast {
        return requestToSources {
            it.forecast(id)
        }
    }

    /**
     * Calculates the time in milliseconds for the current day,
     * The database need it. The server does not need it because today is the default date.
     */
    private fun todayTimeSpan(): Long {

        // Eliminate the time offset, and keep only the day.
        return System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
    }

    /**
     * Returns the first non-null result.
     */
    private fun <T : Any> requestToSources(f: (ForecastProvider.DataSource) -> T?): T {
        return sources.firstResult { f(it) }
    }

}
