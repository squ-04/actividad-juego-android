package com.example.demoapp.features.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.core.components.MemoryCard

@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel(),
    username: String,
    onNavigateToResults: (String, Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isGameOver, uiState.isVictory) {
        if (uiState.isGameOver || uiState.isVictory) {
            onNavigateToResults(username, uiState.score)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Light blue background
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section: Score
            ScoreSection(score = uiState.score)

            // Center Section: Game Grid
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(uiState.cards) { card ->
                        MemoryCard(
                            isFlipped = card.isFlipped || card.isMatched,
                            onCardClick = { viewModel.onCardClicked(card.id) },
                            frontSide = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = card.content, fontSize = 32.sp)
                                }
                            },
                            backSide = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color(0xFF2196F3)), // Blue background for back side
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "?", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        )
                    }
                }
            }

            // Bottom Section: Attempts
            AttemptsSection(attempts = uiState.attempts)
        }
    }
}

@Composable
fun ScoreSection(score: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Puntaje", color = Color(0xFF1976D2), fontSize = 18.sp)
            Text(text = "$score", color = Color(0xFF1976D2), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AttemptsSection(attempts: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Intentos restantes: ", color = Color(0xFF1976D2), fontSize = 18.sp)
            Text(
                text = "$attempts",
                color = if (attempts <= 3) Color.Red else Color(0xFF1976D2),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
