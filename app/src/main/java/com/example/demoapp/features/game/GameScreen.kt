package com.example.demoapp.features.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: Username and Score
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Jugador: $username",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1565C0),
                    fontWeight = FontWeight.Bold
                )
                ScoreSection(score = uiState.score)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Game Grid
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
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
                                    Text(text = card.content, fontSize = 40.sp)
                                }
                            },
                            backSide = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            brush = Brush.linearGradient(
                                                colors = listOf(Color(0xFF64B5F6), Color(0xFF2196F3))
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "?",
                                        color = Color.White,
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom Section: Attempts
            AttemptsSection(attempts = uiState.attempts)
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ScoreSection(score: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Puntos: ", color = Color.Gray, fontSize = 14.sp)
            Text(
                text = "$score",
                color = Color(0xFF1976D2),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun AttemptsSection(attempts: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Intentos Restantes",
                color = Color.Gray,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "$attempts",
                color = if (attempts <= 4) Color(0xFFD32F2F) else Color(0xFF1976D2),
                fontSize = 32.sp,
                fontWeight = FontWeight.Black
            )
            
            // Visual feedback bar
            LinearProgressIndicator(
                progress = { attempts / 16f },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(8.dp),
                color = if (attempts <= 4) Color(0xFFD32F2F) else Color(0xFF64B5F6),
                trackColor = Color(0xFFE3F2FD),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
        }
    }
}
