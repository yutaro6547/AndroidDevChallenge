package com.example.androiddevchallenge.ui.countdown

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountDownViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountDownViewModel() as T
    }
}