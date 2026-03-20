package com.example.demoapp.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.core.components.ButtonDemo

@Composable
fun HomeScreen(
    onNavigateToGame: (String) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    // Diálogo de Ayuda
    if (viewModel.showHelpDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onDismissHelpDialog() },
            shape = RoundedCornerShape(24.dp),
            containerColor = Color.White,
            title = {
                Text(
                    text = "¿Cómo jugar?",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = "El objetivo es encontrar las 8 parejas de animales escondidas en las 16 tarjetas.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "• Tienes un total de 16 intentos.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF1565C0)
                            )
                            Text(
                                text = "• Por cada fallo, perderás un intento.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF1565C0)
                            )
                            Text(
                                text = "• ¡Gana puntos por cada acierto!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF1565C0)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.onDismissHelpDialog() }) {
                    Text("¡Entendido!", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    // Fondo con un degradado sutil
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
            // "Logo" de Emoji en lugar de Imagen
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(4.dp, Color(0xFFBBDEFB), CircleShape)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🦊", // Emoji de zorro del juego
                    fontSize = 70.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Animal Match",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = Color(0xFF1565C0),
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 2.sp
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Entrena tu memoria jugando",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF64B5F6),
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Tarjeta contenedora para el formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = viewModel.userName.value,
                        onValueChange = { viewModel.userName.onChange(it) },
                        label = { Text("¿Cómo te llamas?") },
                        placeholder = { Text("Escribe tu nombre aquí...") },
                        isError = viewModel.userName.error != null,
                        supportingText = {
                            viewModel.userName.error?.let {
                                Text(text = it, color = MaterialTheme.colorScheme.error)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "¿Cómo jugar?",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = Color(0xFF1976D2),
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable {
                            viewModel.onShowHelpDialog()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Botón estilizado
            ButtonDemo(
                icon = Icons.Default.PlayArrow,
                contentDescription = "Jugar",
                onClick = {
                    viewModel.onStartGame(onNavigateToGame)
                },
                text = "¡COMENZAR!"
            )
        }
    }
}
