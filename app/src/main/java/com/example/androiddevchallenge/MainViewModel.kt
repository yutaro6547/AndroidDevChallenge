package com.example.androiddevchallenge

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.other.getMutableStateOf
import com.example.androiddevchallenge.ui.navigation.Screen
import com.example.androiddevchallenge.ui.navigation.Screen.Companion.toBundle
import com.example.androiddevchallenge.ui.navigation.Screen.Companion.toScreen
import com.example.androiddevchallenge.ui.navigation.Screen.PuppiesList

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SCREEN_NAME,
        default = PuppiesList,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != PuppiesList
        currentScreen = PuppiesList
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

    companion object {
        private const val SCREEN_NAME = "screen_name"
    }
}