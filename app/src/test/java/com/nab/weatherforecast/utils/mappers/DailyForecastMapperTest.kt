package com.nab.weatherforecast.utils.mappers

import com.google.common.truth.Truth.assertThat
import com.nab.weatherforecast.data.models.Forecast
import com.nab.weatherforecast.data.remote.dto.DailyForecastDto
import org.junit.Test

class DailyForecastMapperTest {

    private val forecastMapper = ForecastMapper()
    private val dailyForecastMapper = ListMapperImpl(forecastMapper)
    private val forecastList = emptyList<Forecast>()

    @Test
    fun `map should return correct value`() {
        val input = DailyForecastDto(emptyList())
        val actual = DailyForecastMapper(dailyForecastMapper).map(input)
        assertThat(actual.list).isEqualTo(forecastList)
    }
}