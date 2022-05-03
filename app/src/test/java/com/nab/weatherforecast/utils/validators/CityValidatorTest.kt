package com.nab.weatherforecast.utils.validators

import com.google.common.truth.Truth.assertThat
import com.nab.weatherforecast.utils.Constants
import org.junit.Test

class CityValidatorTest {

    @Test
    fun `given city is empty, return false`() {
        assertThat(CityValidator.isCityValid("")).isFalse()
    }

    @Test
    fun `given city is less than minimum characters, return false`() {
        var city = ""
        for (i in 1 until Constants.MIN_CITY_NAME_LENGTH) {
            city += "A"
        }
        assertThat(CityValidator.isCityValid(city)).isFalse()
    }

    @Test
    fun `given city is valid, return true`() {
        assertThat(CityValidator.isCityValid("Saigon")).isTrue()
    }
}