package com.example.demoapp.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.R
import com.example.demoapp.core.components.ButtonDemo

@Composable
fun HomeScreen(
    onNavigateToGame: (String) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {

    // Paleta de colores: Azul y Blanco
    // Usamos el fondo blanco y elementos en azul
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo o Imagen de Bienvenida (Animales)
        Image(
            painter = painterResource(id = R.drawable.logouniquindio),
            contentDescription = "Logo de Animal Match",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Bienvenido a Animal Match!",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color(0xFF1976D2), // Un tono de azul
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Pon a prueba tu memoria con nuestros amigos animales",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Gray
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Campo para ingresar el nombre de usuario
        OutlinedTextField(
            value = viewModel.userName.value,
            onValueChange = { viewModel.userName.onChange(it) },
            label = { Text("Tu Nombre de Usuario") },
            placeholder = { Text("Ej. Juan Perez") },
            isError = viewModel.userName.error != null,
            supportingText = {
                viewModel.userName.error?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para iniciar el juego
        ButtonDemo(
            icon = Icons.Default.PlayArrow,
            contentDescription = "Icono de jugar",
            onClick = {
                onNavigateToGame(viewModel.userName.value)
            },
            text = "¡Empezar Juego!"
        )
    }
}
