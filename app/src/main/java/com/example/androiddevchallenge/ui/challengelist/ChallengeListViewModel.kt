package com.example.androiddevchallenge.ui.challengelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.challengelist.other.ChallengeListUiData
import com.example.androiddevchallenge.ui.challengelist.other.ChallengeListUiState
import com.example.androiddevchallenge.ui.challengelist.other.ChallengeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChallengeListViewModel : ViewModel() {
    private val challengeListUiData = ChallengeListUiData(
        types = listOf(
            ChallengeType.ONE,
            ChallengeType.TWO
        )
    )

    private val _uiState: MutableStateFlow<ChallengeListUiState> = MutableStateFlow(
        ChallengeListUiState.Loading
    )
    val uiState: StateFlow<ChallengeListUiState> = _uiState

    fun reloadData() {
        viewModelScope.launch {
            _uiState.value = ChallengeListUiState.Success(challengeListUiData)
        }
    }
}