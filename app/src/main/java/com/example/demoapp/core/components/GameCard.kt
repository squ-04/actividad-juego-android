package com.example.demoapp.core.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun MemoryCard(
    isFlipped: Boolean,
    onCardClick: () -> Unit,
    frontSide: @Composable () -> Unit, // Imagen del animal
    backSide: @Composable () -> Unit,  // Fondo de la carta
    modifier: Modifier = Modifier
) {
    // Animación de rotación (0 a 180 grados)
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "cardRotation"
    )

    Card(
        modifier = modifier
            .size(100.dp) // Ajusta el tamaño según tu grid
            .padding(4.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (rotation <= 90f) {
                // Lado de atrás (Fondo)
                Box(Modifier.fillMaxSize()) {
                    backSide()
                }
            } else {
                // Lado de adelante (Animal)
                // Invertimos el contenido para que no se vea en espejo al girar 180°
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f }
                ) {
                    frontSide()
                }
            }
        }
    }
}