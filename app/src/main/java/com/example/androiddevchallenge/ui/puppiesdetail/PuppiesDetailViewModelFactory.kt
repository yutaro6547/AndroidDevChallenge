package com.example.androiddevchallenge.ui.puppiesdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PuppiesDetailViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PuppiesDetailViewModel() as T
    }
}