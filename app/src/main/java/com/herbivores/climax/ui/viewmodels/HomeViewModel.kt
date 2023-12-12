package com.herbivores.climax.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamthelegend.enchantmentorder.extensions.mapToStateFlow
import com.herbivores.climax.apiclient.ApiState
import com.herbivores.climax.models.domain.current.CurrentWeather
import com.herbivores.climax.models.domain.forecast.DayWeather
import com.herbivores.climax.models.domain.forecast.ForecastWeather
import com.herbivores.climax.models.domain.Location
import com.herbivores.climax.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _location = MutableStateFlow(Location.Samples.first())
    val location = _location.asStateFlow()

    val locations = location.mapToStateFlow(scope = viewModelScope) { location ->
        Location.Samples - location
    }

    private val _selectingLocation = MutableStateFlow(false)
    val selectingLocation = _selectingLocation.asStateFlow()

    private val _state = MutableStateFlow<ApiState<CurrentWeather>>(ApiState.Loading())
    val state = _state.asStateFlow()

    private val _currentWeatherExpanded = MutableStateFlow(false)
    val currentWeatherExpanded = _currentWeatherExpanded.asStateFlow()

    private val _forecastState = MutableStateFlow<ApiState<ForecastWeather>>(ApiState.Loading())
    val forecastState = _forecastState.asStateFlow()

    private val _selectedDayWeather = MutableStateFlow<DayWeather?>(null)
    val selectedDayWeather = _selectedDayWeather.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            location.collectLatest { (_, latitude, longitude) ->
                getCurrentWeather(latitude, longitude)
                getForecastWeather(latitude, longitude)
            }
        }
    }

    fun reload() {
        val (_, latitude, longitude) = location.value
        getCurrentWeather(latitude, longitude)
        getForecastWeather(latitude, longitude)
        _selectedDayWeather.value = null
    }

    fun toggleSelectingLocation() {
        _selectingLocation.update { oldSelectingLocation ->
            !oldSelectingLocation
        }
        _currentWeatherExpanded.value = false
        _selectedDayWeather.value = null
    }

    fun toggleCurrentWeatherExpanded() {
        _currentWeatherExpanded.update { oldCurrentWeatherExpanded ->
            !oldCurrentWeatherExpanded
        }
        _selectingLocation.value = false
        _selectedDayWeather.value = null
    }

    fun updateSelectedDay(dayWeather: DayWeather) {
        _selectedDayWeather.update { oldSelectedDayWeather ->
            if (oldSelectedDayWeather != dayWeather) dayWeather else null
        }
        _selectingLocation.value = false
        _currentWeatherExpanded.value = false
    }

    fun updateLocation(location: Location) {
        _location.value = location
        _selectingLocation.value = false
    }

    private fun getCurrentWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _state.value = ApiState.Loading()
            weatherRepository.getCurrentWeather(latitude, longitude)
                .catch { value ->
                    _state.value = ApiState.Failure(value)
                }.collect { data ->
                    _state.value = ApiState.Success(data.toCurrentWeather())
                }
        }
    }

    private fun getForecastWeather(latitude: Double, longitude: Double) {
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