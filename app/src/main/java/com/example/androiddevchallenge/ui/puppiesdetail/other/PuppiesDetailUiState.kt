package com.example.androiddevchallenge.ui.puppiesdetail.other

sealed class PuppiesDetailUiState {
    class Success(val uiData: PuppiesDetailUiData): PuppiesDetailUiState()
    object Loading: PuppiesDetailUiState()
}