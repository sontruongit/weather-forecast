package com.nab.weatherforecast.utils.mappers

import com.google.common.truth.Truth.assertThat
import com.nab.weatherforecast.data.remote.dto.ForecastDto
import com.nab.weatherforecast.data.remote.dto.TemperatureDto
import com.nab.weatherforecast.data.remote.dto.WeatherDto
import org.junit.Test

class ForecastMapperTest {

    private val forecastDto = ForecastDto(
        1651696382L,
        TemperatureDto(26.5F),
        1067,
        85,
        listOf(WeatherDto(1, "sky is clear"))
    )

    @Test
    fun `map should return correct value`() {
        val forecast = ForecastMapper().map(forecastDto)
        assertThat(forecast.date).isEqualTo(1651696382L)
        assertThat(forecast.averageTemp).isEqualTo(26.5F)
        assertThat(forecast.pressure).isEqualTo(1067)
        assertThat(forecast.humidity).isEqualTo(85)
        assertThat(forecast.description).isEqualTo("sky is clear")
    }
}