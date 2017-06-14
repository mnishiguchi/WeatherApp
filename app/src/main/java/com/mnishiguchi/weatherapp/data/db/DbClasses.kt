package com.mnishiguchi.weatherapp.data.db

/*
 * The model classes for the database.
 * These classes will help us map the data between objects and SQLite tables in both directions.
 * We use map delegation to map the fields to the database and vice versa.
 * The property names must be the same as the column names in the database.
 *
 * - The default constructor
 *   + For converting database to domain
 *   + The map in the first arg must be filled with the values of the properties.
 * - The second constructor
 *   + For converting domain to database
 *   + An empty map will be filled with passed-in properties automatically by delegation.
 */

/**
 *
 */
class ForecastCity(val map: MutableMap<String, Any?>,
                   val dailyForecast: List<ForecastDay>) {

    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long,
                city: String,
                country: String,
                dailyForecast: List<ForecastDay>)
            : this(HashMap(), dailyForecast) {

        this._id = id
        this.city = city
        this.country = country
    }
}

/**
 *
 */
class ForecastDay(var map: MutableMap<String, Any?>) {

    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long,
                description: String,
                high: Int,
                low: Int,
                iconUrl: String,
                cityId: Long)
            : this(HashMap()) {

        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }
}