package com.mnishiguchi.weatherapp.domain.model

// The domain models that correspond to Forecast Result data

data class ForecastList(val city: String,
                        val country: String,
                        val dailyForecast: List<Forecast>)

data class Forecast(val date: String,
                    val description: String,
                    val high: Int,
                    val low: Int)
