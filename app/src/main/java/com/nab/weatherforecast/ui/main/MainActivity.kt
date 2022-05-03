package com.nab.weatherforecast.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import com.nab.weatherforecast.R
import com.nab.weatherforecast.databinding.ActivityMainBinding
import com.nab.weatherforecast.utils.Constants
import com.nab.weatherforecast.utils.Status
import com.nab.weatherforecast.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val forecastAdapter: ForecastAdapter by lazy { ForecastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also {
            binding = it
            setContentView(it.root)
        }
        setupUI()
        setupViewModel()
    }

    private fun setupUI() {
        binding.btnGetWeather.setOnClickListener {
            hideKeyboard(binding.edtCity)
            resetForecastUI()
            val city = binding.edtCity.text.trim().toString()
            viewModel.validateCity(city)
        }
        binding.rvForecast.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvForecast.adapter = forecastAdapter
    }

    private fun setupViewModel() {
        viewModel.getCityValidationState().observe(this, {
            val (isValid, city) = it
            binding.tvError.isGone = isValid
            if (isValid) {
                viewModel.getDailyForecast(city)
            } else {
                val resId = R.string.main_error_invalid_city_name
                binding.tvError.text = getString(resId, Constants.MIN_CITY_NAME_LENGTH)
            }
        })
        viewModel.dailyForecastState.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.isVisible = true
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    forecastAdapter.setForecastList(it.data!!.list)
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    binding.tvEmpty.isVisible = true
                    binding.tvEmpty.text = it.message
                }
            }
        })
    }

    private fun resetForecastUI() {
        forecastAdapter.setForecastList(emptyList())
        binding.tvEmpty.isGone = true
    }
}