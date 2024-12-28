package com.rolandoselvera.utils

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Error(val throwable: Throwable) : UiState<Nothing>
    data class Success<out T>(val data: T) : UiState<T>
}