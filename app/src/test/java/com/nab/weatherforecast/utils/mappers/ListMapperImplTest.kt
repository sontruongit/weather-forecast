package com.nab.weatherforecast.utils.mappers

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ListMapperImplTest {

    private val input1 = Any()
    private val input2 = Any()
    private val output1 = Any()
    private val output2 = Any()

    private val mapper = object : Mapper<Any, Any> {
        override fun map(input: Any): Any {
            return if (input == input1) {
                output1
            } else {
                output2
            }
        }
    }

    @Test
    fun `map should return empty list when input is empty`() {
        val actual = ListMapperImpl(mapper).map(emptyList())
        assertThat(actual.size).isEqualTo(0)
    }

    @Test
    fun `map should return mapped list when input is 1`() {
        val actual = ListMapperImpl(mapper).map(listOf(input1))
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual[0]).isEqualTo(output1)
    }

    @Test
    fun `map should return mapped list when input is many`() {
        val actual = ListMapperImpl(mapper).map(listOf(input1, input2))
        assertThat(actual.size).isEqualTo(2)
        assertThat(actual[0]).isEqualTo(output1)
        assertThat(actual[1]).isEqualTo(output2)
    }
}