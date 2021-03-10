package com.example.androiddevchallenge.ui.countdown

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ui.countdown.other.CountDownUiData
import com.example.androiddevchallenge.ui.countdown.other.CountDownUiState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountDownViewModel : ViewModel() {
    private var countDownUiData = CountDownUiData(
        timerSeconds = DEFAULT_TIME,
        displayTimerData = DEFAULT_DISPLAY_TIME,
        progressAmountAll = DEFAULT_TIME,
        progress = DEFAULT_PROGRESS,
        isPlaying = false,
        shouldShowTimerSettingView = false
    )

    private val _uiState: MutableStateFlow<CountDownUiState> = MutableStateFlow(
        CountDownUiState.Loading
    )
    val uiState: StateFlow<CountDownUiState> = _uiState
    private var countDownJob: Job? = null

    fun reloadData() {
        viewModelScope.launch {
            _uiState.value = CountDownUiState.Success(countDownUiData)
        }
    }

    fun start() {
        countDownUiData = countDownUiData.copy(
            isPlaying = true
        )
        _uiState.value = CountDownUiState.Success(countDownUiData)
        if (countDownJob != null) {
            return
        }
        viewModelScope.launch {
            countDownJob = launch {
                while (isActive) {
                    if (countDownUiData.timerSeconds <= 0) {
                        cancel()
                    }
                    delay(1000)
                    countDownUiData =
                        countDownUiData.copy(
                            timerSeconds = countDownUiData.timerSeconds - 1,
                            progress = countDownUiData.progress + (1.0f / countDownUiData.progressAmountAll.toFloat())
                        )
                    countDownUiData = countDownUiData.copy(displayTimerData = timerFormatted())
                }
            }
        }
        countDownJob?.start()
    }

    fun pause() {
        countDownUiData = countDownUiData.copy(
            isPlaying = false
        )
        _uiState.value = CountDownUiState.Success(countDownUiData)
        countDownJob?.cancel()
        countDownJob = null
    }

    fun reset() {
        countDownUiData = countDownUiData.copy(
            timerSeconds = DEFAULT_TIME,
            progressAmountAll = DEFAULT_TIME,
            progress = DEFAULT_PROGRESS
        )
        countDownUiData = countDownUiData.copy(
            displayTimerData = timerFormatted()
        )
        _uiState.value = CountDownUiState.Success(countDownUiData)
    }

    fun addTimers() {
        countDownUiData = countDownUiData.copy(
            shouldShowTimerSettingView = true
        )
        _uiState.value = CountDownUiState.Success(countDownUiData)
    }

    fun deleteTimers() {
        countDownUiData = countDownUiData.copy(
            shouldShowTimerSettingView = true
        )
        _uiState.value = CountDownUiState.Success(countDownUiData)
    }

    fun cancelTimer() {
        countDownUiData = countDownUiData.copy(
            shouldShowTimerSettingView = false
        )
        _uiState.value = CountDownUiState.Success(countDownUiData)
    }

    private fun timerFormatted(): String {
        val hour = countDownUiData.timerSeconds / 3600
        val minutes = (countDownUiData.timerSeconds % 3600) / 60
        val seconds = countDownUiData.timerSeconds % 60
        return when {
            hour <= 0 -> String.format("%02d:%02d", minutes, seconds)
            minutes <= 0 -> String.format("00:%02d", seconds)
            else -> String.format("%02d:%02d:%02d", hour, minutes, seconds)
        }
    }

    companion object {
        private const val DEFAULT_TIME = 120
        private const val DEFAULT_DISPLAY_TIME = "02:00"
        private const val DEFAULT_PROGRESS = 0f
    }
}