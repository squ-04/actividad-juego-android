package com.example.demoapp.features.results

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ResultsScreen(
    viewModel: ResultsViewModel = viewModel(),
    userName: String,
    score: Int
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Hola, ${viewModel.userName}!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (viewModel.isWinner()) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = "Trofeo de victoria",
                modifier = Modifier.size(100.dp),
                tint = Color(0xFFFFD700) // Color dorado
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¡Felicidades, ganaste!",
                fontSize = 20.sp,
                color = Color.Green
            )
            Text(
                text = "Tu puntaje es: ${viewModel.score}",
                fontSize = 18.sp
            )
        } else {
            Icon(
                imageVector = Icons.Default.SentimentVeryDissatisfied,
                contentDescription = "Icono de derrota",
                modifier = Modifier.size(100.dp),
                tint = Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lo sentimos, perdiste el juego.",
                fontSize = 20.sp,
                color = Color.Red
            )
            Text(
                text = "Tu puntaje es: 0",
                fontSize = 18.sp
            )
        }
    }
}
