package com.nab.weatherforecast.utils.mappers

import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.data.models.Forecast
import com.nab.weatherforecast.data.remote.dto.DailyForecastDto
import com.nab.weatherforecast.data.remote.dto.ForecastDto

class DailyForecastMapper(
    private val mapper: ListMapper<ForecastDto, Forecast>
) : Mapper<DailyForecastDto, DailyForecast> {

    override fun map(input: DailyForecastDto): DailyForecast {
        return DailyForecast(mapper.map(input.list))
    }
}