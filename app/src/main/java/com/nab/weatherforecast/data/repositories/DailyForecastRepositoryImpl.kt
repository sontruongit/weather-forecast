package com.nab.weatherforecast.data.repositories

import com.nab.weatherforecast.data.local.DailyForecastCache
import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.data.remote.OpenWeatherService
import com.nab.weatherforecast.data.remote.dto.DailyForecastDto
import com.nab.weatherforecast.utils.Resource
import com.nab.weatherforecast.utils.mappers.Mapper
import java.util.*
import javax.inject.Inject

class DailyForecastRepositoryImpl @Inject constructor(
    private val openWeatherService: OpenWeatherService,
    private val dailyForecastCache: DailyForecastCache,
    private val dailyForecastMapper: Mapper<DailyForecastDto, DailyForecast>
) : DailyForecastRepository {

    override suspend fun getDailyForecast(city: String): Resource<DailyForecast> {
        return try {
            // Check data in local cache
            val cachedKey = city.trim().toLowerCase(Locale.ROOT)
            val cachedValue = dailyForecastCache.get(cachedKey)
            if (cachedValue != null) {
                return Resource.success(cachedValue)
            }

            // Not found in the local cache, then fetch from network
            val response = openWeatherService.getDailyForecast(city = city)
            if (response.isSuccessful && response.body() != null) {
                val dailyForecastDto = response.body()!!
                val dailyForecast = dailyForecastMapper.map(dailyForecastDto)

                // Save the daily forecast to the local cache
                dailyForecastCache.set(cachedKey, dailyForecast)

                Resource.success(dailyForecast)
            } else {
                Resource.error("An unknown error occurred.")
            }
        } catch (e: Exception) {
            Resource.error("Check your internet connect and try again.")
        }
    }
}