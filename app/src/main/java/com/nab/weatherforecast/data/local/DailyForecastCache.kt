package com.nab.weatherforecast.data.local

import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.utils.cache.Cache

class DailyForecastCache : Cache<String, DailyForecast> {

    private val dailyForecastMap = mutableMapOf<String, DailyForecast>()

    override fun set(key: String, value: DailyForecast) {
        dailyForecastMap[key] = value
    }

    override fun get(key: String): DailyForecast? {
        return dailyForecastMap[key]
    }
}