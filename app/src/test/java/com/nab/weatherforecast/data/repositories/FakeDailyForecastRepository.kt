package com.nab.weatherforecast.data.repositories

import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.utils.Resource

class FakeDailyForecastRepository : DailyForecastRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(shouldReturnNetworkError: Boolean) {
        this.shouldReturnNetworkError = shouldReturnNetworkError
    }

    override suspend fun getDailyForecast(city: String): Resource<DailyForecast> {
        return if (shouldReturnNetworkError) {
            Resource.error("Network error occurred.")
        } else {
            Resource.success(DailyForecast(listOf()))
        }
    }
}