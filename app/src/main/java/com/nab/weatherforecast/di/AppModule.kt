package com.nab.weatherforecast.di

import com.nab.weatherforecast.data.local.DailyForecastCache
import com.nab.weatherforecast.data.models.DailyForecast
import com.nab.weatherforecast.data.models.Forecast
import com.nab.weatherforecast.data.remote.OpenWeatherService
import com.nab.weatherforecast.data.remote.dto.DailyForecastDto
import com.nab.weatherforecast.data.remote.dto.ForecastDto
import com.nab.weatherforecast.data.repositories.DailyForecastRepository
import com.nab.weatherforecast.data.repositories.DailyForecastRepositoryImpl
import com.nab.weatherforecast.utils.Constants
import com.nab.weatherforecast.utils.mappers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOpenWeatherService(): OpenWeatherService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(OpenWeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideDailyForecastRepository(
        openWeatherService: OpenWeatherService,
        forecastCache: DailyForecastCache,
        forecastMapper: Mapper<DailyForecastDto, DailyForecast>
    ): DailyForecastRepository {
        return DailyForecastRepositoryImpl(
            openWeatherService,
            forecastCache,
            forecastMapper
        )
    }

    @Singleton
    @Provides
    fun provideDailyForecastMemoryCache(): DailyForecastCache {
        return DailyForecastCache()
    }

    @Singleton
    @Provides
    fun provideDailyForecastMapper(mapper: ListMapper<ForecastDto, Forecast>): Mapper<DailyForecastDto, DailyForecast> {
        return DailyForecastMapper(mapper)
    }

    @Singleton
    @Provides
    fun provideForecastListMapper(mapper: Mapper<ForecastDto, Forecast>): ListMapper<ForecastDto, Forecast> {
        return ListMapperImpl(mapper)
    }

    @Singleton
    @Provides
    fun provideForecastMapper(): Mapper<ForecastDto, Forecast> {
        return ForecastMapper()
    }
}