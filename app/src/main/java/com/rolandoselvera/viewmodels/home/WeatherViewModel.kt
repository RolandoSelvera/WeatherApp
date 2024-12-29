package com.rolandoselvera.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.data.remote.repositories.WeatherRepository
import com.rolandoselvera.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<WeatherResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<WeatherResponse>> = _uiState

    fun getWeather(location: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val weather = repository.fetchWeather(location)
                _uiState.value = UiState.Success(weather)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }
}