package com.example.demoapp.features.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.core.components.ButtonDemo

@Composable
fun ResultsScreen(
    viewModel: ResultsViewModel = viewModel(),
    username: String,
    score: Int,
    onPlayAgain: () -> Unit
) {
    viewModel.score = score
    viewModel.userName = username

    val isWinner = viewModel.isWinner()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color.White)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icono de Resultado
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isWinner) Icons.Default.EmojiEvents else Icons.Default.SentimentVeryDissatisfied,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = if (isWinner) Color(0xFFFFC107) else Color(0xFFD32F2F)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = if (isWinner) "¡VICTORIA!" else "¡Sigue intentando!",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = if (isWinner) Color(0xFF1565C0) else Color(0xFFD32F2F),
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = username,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Tarjeta de Puntaje
            Card(
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Puntaje Final",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray
                    )
                    Text(
                        text = "$score",
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = Color(0xFF1976D2),
                            fontWeight = FontWeight.Black
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Botón para volver a jugar
            ButtonDemo(
                icon = Icons.Default.Refresh,
                contentDescription = "Volver a jugar",
                onClick = onPlayAgain,
                text = "VOLVER A JUGAR"
            )
        }
    }
}
