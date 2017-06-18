package com.mnishiguchi.weatherapp.domain.providers

import com.mnishiguchi.weatherapp.data.db.ForecastDao
import com.mnishiguchi.weatherapp.data.server.ForecastServer
import com.mnishiguchi.weatherapp.domain.ForecastList
import com.mnishiguchi.weatherapp.extensions.firstResult

/**
 * TODO - Understand and refactor
 *
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

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24

        // Specify data sources in the order of priority.
        val SOURCES = listOf(ForecastDao(), ForecastServer())
    }

    fun findByZipCodeAndDays(zipCode: Long, numberOfDays: Int): ForecastList {

        // Get the first non-null result from the sources.
        return sources.firstResult { requestSource(it, numberOfDays, zipCode) }
    }

    /**
     * Only returns a value if the result is not null and the number of days matches the parameter.
     * Otherwise, the source does not have enough up-to-date data to return a successful result.
     */
    private fun requestSource(source: DataSource, numberOfDays: Int, zipCode: Long)
            : ForecastList? {

        val response = source.forecastList(zipCode, todayTimeSpan())

        return if (response != null && response.size >= numberOfDays) response else null
    }

    /**
     * TODO - what does this exactly do?
     * Calculates the time in milliseconds for the current day,
     * The database need it. The server does not need it because today is the default date.
     */
    private fun todayTimeSpan(): Long {

        // Eliminate the time offset, and keep only the day.
        return System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
    }

    interface DataSource {

        /**
         * @param zipCode
         * @param date a unix timestamp
         */
        fun forecastList(zipCode: Long, date: Long): ForecastList?
    }
}
