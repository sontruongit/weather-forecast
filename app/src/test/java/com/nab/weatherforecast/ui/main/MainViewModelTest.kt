package com.nab.weatherforecast.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nab.weatherforecast.MainCoroutineRule
import com.nab.weatherforecast.getOrAwaitValueTest
import com.nab.weatherforecast.data.repositories.FakeDailyForecastRepository
import com.nab.weatherforecast.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakeDailyForecastRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun init() {
        repository = FakeDailyForecastRepository()
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `when city is invalid, the live data return false`() {
        viewModel.validateCity("")
        val actual = viewModel.getCityValidationState().value
        assertThat(actual?.first).isEqualTo(false)
    }

    @Test
    fun `when city is valid, the live data return correct value`() {
        viewModel.validateCity("Saigon")
        val actual = viewModel.getCityValidationState().value
        assertThat(actual?.first).isEqualTo(true)
        assertThat(actual?.second).isEqualTo("Saigon")
    }

    @Test
    fun `there is a network error when searching, return error`() {
        repository.setShouldReturnNetworkError(true)
        viewModel.getDailyForecast("Saigon")
        val value = viewModel.dailyForecastState.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `there is no network error when searching, return success`() {
        repository.setShouldReturnNetworkError(false)
        viewModel.getDailyForecast("Saigon")
        val value = viewModel.dailyForecastState.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}