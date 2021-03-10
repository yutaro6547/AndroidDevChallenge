package com.example.androiddevchallenge.ui.challengelist.other

sealed class ChallengeListUiState {
    class Success(val uiData: ChallengeListUiData): ChallengeListUiState()
    object Loading: ChallengeListUiState()
    class Error(val errorMessage: String): ChallengeListUiState()
}