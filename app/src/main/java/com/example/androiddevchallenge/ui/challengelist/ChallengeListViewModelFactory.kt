package com.example.androiddevchallenge.ui.challengelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChallengeListViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChallengeListViewModel() as T
    }
}