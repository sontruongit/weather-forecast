package com.nab.weatherforecast.data.local

import com.google.common.truth.Truth.assertThat
import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.data.models.Forecast
import org.junit.Before
import org.junit.Test

class DailyForecastCacheTest {

    private lateinit var dailyForecastCache: DailyForecastCache
    private val forecast = Forecast(
        1651696382L,
        26.5F,
        1067,
        85,
        "sky is clear"
    )
    private val dailyForecast = DailyForecast(listOf(forecast))

    @Before
    fun init() {
        dailyForecastCache = DailyForecastCache()
    }

    @Test
    fun `when the data is not in the cache, return null`() {
        val actual = dailyForecastCache.get("Saigon")
        assertThat(actual).isEqualTo(null)
    }

    @Test
    fun `when the data is in the cache, return value`() {
        dailyForecastCache.set("Saigon", dailyForecast)
        val actual = dailyForecastCache.get("Saigon")
        assertThat(actual).isEqualTo(dailyForecast)
    }
}