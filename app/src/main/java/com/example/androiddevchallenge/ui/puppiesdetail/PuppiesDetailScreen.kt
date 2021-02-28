package com.example.androiddevchallenge.ui.puppiesdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.other.check
import com.example.androiddevchallenge.ui.puppiesdetail.other.PuppiesDetailUiState

class PuppiesDetailScreen(
    val name: String,
    private val imageResourceId: Int,
    private val description: String,
    private val onBack: () -> Unit
) {
    private val puppiesDetailViewModel =
        PuppiesDetailViewModelFactory().create(PuppiesDetailViewModel::class.java)

    @Composable
    fun Build(modifier: Modifier = Modifier) {
        puppiesDetailViewModel.reloadData(name, imageResourceId, description)
        val puppiesUiState = puppiesDetailViewModel.uiState.collectAsState()
        puppiesUiState.value.let { uiState ->
            when (uiState) {
                is PuppiesDetailUiState.Success -> {
                    Surface(color = MaterialTheme.colors.background) {
                        Box(modifier = modifier) {
                            LazyColumn(modifier = modifier.fillMaxWidth()) {
                                item {
                                    Row {
                                        IconButton(onClick = onBack) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                        Text(
                                            text = uiState.uiData.name,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Cursive,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 16.dp, bottom = 16.dp)
                                        )
                                    }
                                }
                                item {
                                    Image(
                                        painter = painterResource(id = uiState.uiData.imageResourceId),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxWidth()
                                            .heightIn(max = 360.dp)
                                            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                                    )
                                }
                                item {
                                    Text(
                                        text = uiState.uiData.description,
                                        style = TextStyle(
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        ),
                                        modifier = modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                is PuppiesDetailUiState.Loading -> {
                    Surface(color = MaterialTheme.colors.background) {
                        CircularProgressIndicator()
                    }
                }
            }.check
        }
    }
}