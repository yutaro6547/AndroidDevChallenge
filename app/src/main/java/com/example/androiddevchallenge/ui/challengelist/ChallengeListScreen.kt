package com.example.androiddevchallenge.ui.challengelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.other.check
import com.example.androiddevchallenge.ui.challengelist.other.ChallengeListUiState
import com.example.androiddevchallenge.ui.challengelist.other.ChallengeType
import com.example.androiddevchallenge.ui.navigation.Screen

class ChallengeListScreen(val navigateTo: (Screen) -> Unit) {
    private val challengeListViewModel =
        ChallengeListViewModelFactory().create(ChallengeListViewModel::class.java)

    @Composable
    fun Build(modifier: Modifier = Modifier) {
        challengeListViewModel.reloadData()
        val challengeListUiState = challengeListViewModel.uiState.collectAsState()
        challengeListUiState.value.let { uiState ->
            Surface(color = MaterialTheme.colors.background) {
                Box(modifier = modifier) {
                    LazyColumn(modifier.fillMaxWidth()) {
                        item(null) {
                            Text(
                                text = "AndroidDevChallenge",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Cursive,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, top = 16.dp, bottom = 16.dp)
                            )
                        }
                        when (uiState) {
                            is ChallengeListUiState.Loading -> {
                                item {
                                    Column(
                                        modifier = modifier
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                            is ChallengeListUiState.Error -> {
                                item {
                                    val padding = 16.dp
                                    Column(
                                        modifier = Modifier.padding(padding)
                                    ) {
                                        Text(uiState.errorMessage)
                                    }
                                }
                            }
                            is ChallengeListUiState.Success -> {
                                items(uiState.uiData.types.toList()) { type ->
                                    val padding = 16.dp
                                    Column(
                                        modifier = Modifier.padding(padding)
                                            .clickable(onClick = { transitionNextScreen(type) })
                                    ) {
                                        Text(type.label)
                                    }
                                }
                            }
                        }.check
                    }
                }
            }
        }
    }

    private fun transitionNextScreen(type: ChallengeType) {
        when(type) {
            ChallengeType.ONE -> navigateTo(Screen.PuppiesList)
            ChallengeType.TWO -> navigateTo(Screen.CountDownTimer)
        }.check
    }
}