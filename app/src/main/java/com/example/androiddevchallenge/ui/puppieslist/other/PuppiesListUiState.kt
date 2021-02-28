package com.example.androiddevchallenge.ui.puppieslist.other

sealed class PuppiesListUiState {
    class Success(val uiData: PuppiesListUiData): PuppiesListUiState()
    object Loading: PuppiesListUiState()
    class Error(val errorMessage: String): PuppiesListUiState()
}