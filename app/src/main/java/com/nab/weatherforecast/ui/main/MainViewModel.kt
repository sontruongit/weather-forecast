package com.nab.weatherforecast.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.data.repositories.DailyForecastRepository
import com.nab.weatherforecast.utils.Resource
import com.nab.weatherforecast.utils.Status
import com.nab.weatherforecast.utils.livedata.SingleLiveEvent
import com.nab.weatherforecast.utils.validators.CityValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dailyForecastRepository: DailyForecastRepository
) : ViewModel() {

    private val _cityValidationState = SingleLiveEvent<Pair<Boolean, String>>()

    private val _dailyForecastState = MutableLiveData<Resource<DailyForecast>>()
    val dailyForecastState: LiveData<Resource<DailyForecast>> = _dailyForecastState

    fun validateCity(city: String) {
        val isValid = CityValidator.isCityValid(city)
        _cityValidationState.value = Pair(isValid, city)
    }

    fun getDailyForecast(city: String) {
        viewModelScope.launch {
            _dailyForecastState.value = Resource.loading()
            val result = dailyForecastRepository.getDailyForecast(city)
            when (result.status) {
                Status.SUCCESS -> {
                    _dailyForecastState.value = Resource.success(result.data)
                }
                else -> {
                    _dailyForecastState.value = Resource.error(result.message)
                }
            }
        }
    }

    fun getCityValidationState(): LiveData<Pair<Boolean, String>> {
        return _cityValidationState
    }
}