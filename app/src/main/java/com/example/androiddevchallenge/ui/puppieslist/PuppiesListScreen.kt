package com.example.androiddevchallenge.ui.puppieslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.other.check
import com.example.androiddevchallenge.ui.navigation.Screen
import com.example.androiddevchallenge.ui.puppieslist.other.PuppiesListUiState
import com.example.androiddevchallenge.ui.puppieslist.other.PuppyData

class PuppiesListScreen(val navigateTo: (Screen) -> Unit) {
    @Composable
    fun Build(modifier: Modifier = Modifier) {
        val puppiesListViewModel =
            PuppiesListViewModelFactory().create(PuppiesListViewModel::class.java)
        val puppiesListUiState = puppiesListViewModel.uiState.collectAsState()
        puppiesListUiState.value.let { uiState ->
            when (uiState) {
                is PuppiesListUiState.Success -> {
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
                                items(uiState.uiData.puppiesDate.toList()) { puppyData ->
                                    PuppyItem(
                                        puppyData = puppyData,
                                        onClick = {
                                            navigateTo(
                                                Screen.PuppiesDetail(
                                                    puppyData.name,
                                                    puppyData.imageId,
                                                    puppyData.description
                                                )
                                            )
                                        })
                                }
                            }
                        }
                    }
                }
                is PuppiesListUiState.Loading -> {
                    Surface(color = MaterialTheme.colors.background) {
                        CircularProgressIndicator(modifier.fillMaxWidth())
                    }
                }
                is PuppiesListUiState.Error -> {
                    Surface(color = MaterialTheme.colors.background) {
                        Text(text = "No Contents! For ${uiState.errorMessage}")
                    }
                }
            }.check
        }
    }

    @Composable
    fun PuppyItem(puppyData: PuppyData, onClick: () -> Unit) {
        val padding = 16.dp
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(padding)
                .fillMaxWidth()
        ) {
            val gradient =
                Brush.horizontalGradient(listOf(Color(0xFF833AB4), Color(0xFFC13584)))
            Image(
                painter = painterResource(id = puppyData.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(2.dp, gradient, CircleShape)
            )
            Column(
                modifier = Modifier.padding(padding)
            ) {
                Text(puppyData.name)
            }
        }
    }
}