package com.example.androiddevchallenge.ui.puppieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PuppiesListViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PuppiesListViewModel() as T
    }
}