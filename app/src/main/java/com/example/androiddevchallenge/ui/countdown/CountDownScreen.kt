package com.example.androiddevchallenge.ui.countdown

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.other.check
import com.example.androiddevchallenge.ui.countdown.other.CountDownUiState
import com.example.androiddevchallenge.ui.theme.purple500

class CountDownScreen {
    private val countDownViewModel =
        CountDownViewModelFactory().create(CountDownViewModel::class.java)

    @Composable
    fun Build(modifier: Modifier = Modifier) {
        countDownViewModel.reloadData()
        val challengeListUiState = countDownViewModel.uiState.collectAsState()
        challengeListUiState.value.let { uiState ->
            Surface(color = MaterialTheme.colors.background) {
                Box(modifier = modifier) {
                    LazyColumn(modifier.fillMaxWidth()) {
                        item(null) {
                            Text(
                                text = "Challenge #2 CountDownTimer",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Cursive,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(start = 24.dp, top = 16.dp, bottom = 16.dp)
                            )
                        }
                        when (uiState) {
                            is CountDownUiState.Loading -> {
                                item {
                                    Column(
                                        modifier = modifier
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                            is CountDownUiState.Error -> {
                                item {
                                    val padding = 16.dp
                                    Column(
                                        modifier = Modifier.padding(padding)
                                    ) {
                                        Text(uiState.errorMessage)
                                    }
                                }
                            }
                            is CountDownUiState.Success -> {
                                item {
                                    Box {
                                        Column(
                                            modifier = modifier
                                                .height(300.dp)
                                                .align(Alignment.Center)
                                                .fillMaxWidth(),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("ラベル", style = TextStyle(color = Color.Gray))
                                            Text(
                                                uiState.uiData.displayTimerData,
                                                style = TextStyle(
                                                    color = purple500,
                                                    fontSize = 56.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                            TextButton(onClick = { countDownViewModel.reset() }) {
                                                Text("リセット", style = TextStyle(color = Color.Black))
                                            }
                                        }
//                                        AnimateCircle(
//                                            uiState = uiState,
//                                            modifier = modifier
//                                                .heightIn(min = 420.dp)
//                                                .align(Alignment.Center)
//                                                .fillMaxWidth()
//                                        )
                                    }
                                    Row(
                                        modifier = modifier.fillMaxWidth().height(100.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        FloatingActionButton(
                                            onClick = {
                                                if (uiState.uiData.isPlaying) {
                                                    countDownViewModel.pause()
                                                } else {
                                                    countDownViewModel.start()
                                                }
                                            },
                                            backgroundColor = purple500
                                        ) {
                                            val iconResourceId =
                                                if (uiState.uiData.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                                            Icon(
                                                painter = painterResource(id = iconResourceId),
                                                contentDescription = null
                                            )
                                        }
                                    }
                                }
                            }
                        }.check
                    }
                }
            }
        }
    }

    @Composable
    fun AnimateCircle(
        uiState: CountDownUiState.Success,
        modifier: Modifier = Modifier
    ) {
        val currentState = remember {
            MutableTransitionState(AnimatedCircleProgress.START)
                .apply { targetState = AnimatedCircleProgress.END }
        }
        val stroke = with(LocalDensity.current) { Stroke(5.dp.toPx()) }
        val transition = updateTransition(currentState)
        val angleOffset by transition.animateFloat(
            transitionSpec = {
                tween(
                    delayMillis = 500,
                    durationMillis = 900,
                    easing = LinearOutSlowInEasing
                )
            }
        ) { progress ->
            if (progress == AnimatedCircleProgress.START) {
                0f
            } else {
                360f
            }
        }
        val shift by transition.animateFloat(
            transitionSpec = {
                tween(
                    delayMillis = 500,
                    durationMillis = 900,
                    easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
                )
            }
        ) { progress ->
            if (progress == AnimatedCircleProgress.START) {
                0f
            } else {
                30f
            }
        }

        Canvas(modifier) {
            val innerRadius = (size.minDimension - stroke.width) / 2
            val halfSize = size / 2.0f
            val topLeft = Offset(
                halfSize.width - innerRadius,
                halfSize.height - innerRadius
            )
            val size = Size(400f, 400f)
            var startAngle = shift - 90f
            for (i in 0..uiState.uiData.timerSeconds / 2) {
                val sweep = i * angleOffset
                drawArc(
                    color = purple500,
                    startAngle = startAngle + DividerLengthInDegrees / 2,
                    sweepAngle = sweep - DividerLengthInDegrees,
                    topLeft = topLeft,
                    size = size,
                    useCenter = false,
                    style = stroke
                )
                startAngle += sweep
            }
        }
    }

    companion object {
        private const val DividerLengthInDegrees = 1.8f
    }
}