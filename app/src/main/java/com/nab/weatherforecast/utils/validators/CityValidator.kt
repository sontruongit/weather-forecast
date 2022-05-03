package com.nab.weatherforecast.utils.validators

import com.nab.weatherforecast.utils.Constants

object CityValidator {

    fun isCityValid(city: String): Boolean {
        return city.trim().length >= Constants.MIN_CITY_NAME_LENGTH
    }
}