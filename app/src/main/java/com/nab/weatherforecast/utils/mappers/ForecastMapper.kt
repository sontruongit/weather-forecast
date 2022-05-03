package com.nab.weatherforecast.utils.mappers

import com.nab.weatherforecast.data.models.Forecast
import com.nab.weatherforecast.data.remote.dto.ForecastDto

class ForecastMapper : Mapper<ForecastDto, Forecast> {

    override fun map(input: ForecastDto): Forecast {
        return Forecast(
            date = input.date,
            averageTemp = input.temp.day,
            pressure = input.pressure,
            humidity = input.humidity,
            description = input.weather[0].description
        )
    }
}