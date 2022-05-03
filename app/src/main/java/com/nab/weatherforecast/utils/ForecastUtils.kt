package com.nab.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.*

object ForecastUtils {

    fun formatDate(timeInSeconds: Long): String {
        val date = Date(timeInSeconds * 1000)
        return SimpleDateFormat(Constants.FORECAST_DATE_FORMAT, Locale.US).format(date.time)
    }
}