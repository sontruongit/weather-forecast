package com.nab.weatherforecast.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ForecastUtilsTest {

    @Test
    fun `given time equals to less than zero, return Unix epoch time`() {
        assertThat(ForecastUtils.formatDate(0)).isEqualTo("Thu, 1 Jan 1970")
    }

    @Test
    fun `given times is valid, return valid date format`() {
        assertThat(ForecastUtils.formatDate(1651696382L)).isEqualTo("Thu, 5 May 2022")
    }
}