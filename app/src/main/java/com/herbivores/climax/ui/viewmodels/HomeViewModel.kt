package com.herbivores.climax.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.models.domain.CurrentWeather
import com.herbivores.climax.models.domain.ForecastWeather
import com.herbivores.climax.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _state = MutableStateFlow<ApiState<CurrentWeather>>(ApiState.Empty())
    val state = _state.asStateFlow()

    private val _forecastState = MutableStateFlow<ApiState<ForecastWeather>>(ApiState.Empty())
    val forecastState = _forecastState.asStateFlow()

    init {
        getCurrentWeather()
        getForecastWeather()
    }

    private fun getCurrentWeather() {
        // Temporary hardcoded values for Angeles?
        val latitude = 15.1463554
        val longitude = 120.5245999
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = ApiState.Loading()
            weatherRepository.getCurrentWeather(latitude, longitude)
                .catch { value ->
                    _state.value = ApiState.Failure(value)
                }.collect { data ->
                    _state.value = ApiState.Success(data.toCurrentWeather())
                }
        }
    }

    private fun getForecastWeather() {
        val latitude = 14.9351
        val longitude = 120.4939
        viewModelScope.launch {
            _forecastState.value = ApiState.Loading()
            weatherRepository.getForecastWeather(latitude, longitude)
                .catch { value ->
                    _forecastState.value = ApiState.Failure(value)
                }.collect { data ->
                    _forecastState.value = ApiState.Success(data.toForecastWeather())
                }
        }
    }
}