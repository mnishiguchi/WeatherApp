package com.mnishiguchi.weatherapp.data.db

/*
 * Define database table schemas.
 */

object ForecastCityTable {
    val NAME = "ForecastCity"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

object ForecastDayTable {
    val NAME = "ForecastDay"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}