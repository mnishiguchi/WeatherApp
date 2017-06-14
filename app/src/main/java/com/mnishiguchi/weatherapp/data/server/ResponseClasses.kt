package com.mnishiguchi.weatherapp.data.server

// The data models that represent json result from OpenWeatherMap API. https://openweathermap.org/forecast5#JSON
// The properties must have the same name as the ones in the json.

data class ForecastResult(val city: City, val list: List<Forecast>)

data class City(val id: Long,
                val name: String,
                val coord: Coordinates,
                val country: String,
                val population: Int)

data class Coordinates(val lon: Float,
                       val lat: Float)

data class Forecast(val dt: Long, // Time of data forecasted, unix, UTC
                    val temp: Temperature,
                    val pressure: Float,
                    val humidity: Int,
                    val weather: List<Weather>,
                    val speed: Float,
                    val deg: Int,
                    val clouds: Int,
                    val rain: Float)

data class Temperature(val day: Float,
                       val min: Float,
                       val max: Float,
                       val night: Float,
                       val eve: Float,
                       val morn: Float)

data class Weather(val id: Long,
                   val main: String,
                   val description: String,
                   val icon: String)