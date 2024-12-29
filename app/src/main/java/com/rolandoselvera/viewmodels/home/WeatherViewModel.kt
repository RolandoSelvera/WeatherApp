package com.rolandoselvera.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rolandoselvera.data.local.models.toWeatherEntity
import com.rolandoselvera.data.local.models.toWeatherResponse
import com.rolandoselvera.data.local.repositories.WeatherDbRepository
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.data.remote.repositories.WeatherRepository
import com.rolandoselvera.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val repositoryDb: WeatherDbRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<WeatherResponse>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<WeatherResponse>>> = _uiState

    fun getWeather(location: String, isUpdate: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val weather = repository.fetchWeather(location)
                val weatherEntity = weather.toWeatherEntity()
                if (isUpdate) {
                    repositoryDb.updateWeather(weatherEntity)
                } else {
                    repositoryDb.saveWeatherIfNew(weatherEntity)
                }
                loadLocalWeather()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

    fun loadLocalWeather() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val localWeather = repositoryDb.getAllWeather()
                _uiState.value = UiState.Success(localWeather.map { it.toWeatherResponse() })
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }

    fun deleteWeatherItem(locationName: String, locationRegion: String) {
        viewModelScope.launch {
            try {
                val weatherToDelete =
                    repositoryDb.getWeatherByLocation(locationName, locationRegion)

                if (weatherToDelete != null) {
                    repositoryDb.deleteWeather(weatherToDelete)
                    val currentWeatherList =
                        (_uiState.value as? UiState.Success<List<WeatherResponse>>)?.data.orEmpty()
                    val updatedWeatherList = currentWeatherList.filter {
                        it.location.name != locationName || it.location.region != locationRegion
                    }
                    _uiState.value = UiState.Success(updatedWeatherList)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }
}