package com.example.androiddevchallenge.ui.puppiesdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.puppiesdetail.other.PuppiesDetailUiData
import com.example.androiddevchallenge.ui.puppiesdetail.other.PuppiesDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PuppiesDetailViewModel: ViewModel() {

    private var puppiesDetailUiData = PuppiesDetailUiData(
        name = "",
        imageResourceId = 0,
        description = ""
    )
    private val _uiState: MutableStateFlow<PuppiesDetailUiState> = MutableStateFlow(PuppiesDetailUiState.Loading)
    val uiState: StateFlow<PuppiesDetailUiState> = _uiState

    fun reloadData(name: String, imageResourceId: Int, description: String) {
        viewModelScope.launch {
            puppiesDetailUiData = puppiesDetailUiData.copy(
                name = name,
                imageResourceId = imageResourceId,
                description = description
            )
            _uiState.value = PuppiesDetailUiState.Success(puppiesDetailUiData)
        }
    }
}