package com.example.androiddevchallenge.ui.countdown.other

sealed class CountDownUiState {
    class Success(val uiData: CountDownUiData): CountDownUiState()
    object Loading: CountDownUiState()
    class Error(val errorMessage: String): CountDownUiState()
}