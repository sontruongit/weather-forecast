package com.nab.weatherforecast.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nab.weatherforecast.R
import com.nab.weatherforecast.data.models.Forecast
import com.nab.weatherforecast.databinding.ItemForecastBinding
import com.nab.weatherforecast.utils.ForecastUtils

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    private val forecastList = mutableListOf<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemForecastBinding.inflate(inflater, parent, false)
        return ForecastViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[holder.adapterPosition])
    }

    override fun getItemCount() = forecastList.size

    fun setForecastList(list: List<Forecast>) {
        forecastList.clear()
        forecastList.addAll(list)
        notifyDataSetChanged()
    }

    class ForecastViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(forecast: Forecast) {
            (itemView as TextView).text = buildStringFromForecast(forecast)
        }

        private fun buildStringFromForecast(forecast: Forecast): String {
            val context = itemView.context
            return buildString {
                append(
                    context.getString(
                        R.string.main_date_format,
                        ForecastUtils.formatDate(forecast.date)
                    )
                )
                    .append("\n")
                    .append(
                        context.getString(
                            R.string.main_average_temperature_format,
                            forecast.averageTemp
                        )
                    )
                    .append("\n")
                    .append(context.getString(R.string.main_pressure_format, forecast.pressure))
                    .append("\n")
                    .append(context.getString(R.string.main_humidity_format, forecast.humidity))
                    .append("\n")
                    .append(
                        context.getString(
                            R.string.main_description_format,
                            forecast.description
                        )
                    )
            }
        }
    }
}