package com.nab.weatherforecast.data.repositories

import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.utils.Resource

interface DailyForecastRepository {

    suspend fun getDailyForecast(city: String): Resource<DailyForecast>
}