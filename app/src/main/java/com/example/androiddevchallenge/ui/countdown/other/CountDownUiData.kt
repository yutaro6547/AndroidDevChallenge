package com.example.androiddevchallenge.ui.countdown.other

data class CountDownUiData(
    val timerSeconds: Int,
    val displayTimerData: String,
    val progressAmountAll: Int,
    val progress: Float,
    val isPlaying: Boolean,
    val shouldShowTimerSettingView: Boolean
)