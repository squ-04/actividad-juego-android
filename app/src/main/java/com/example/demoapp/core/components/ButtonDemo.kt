package com.example.demoapp.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonDemo(
    primario: Boolean = true,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    text: String
) {
    val content = @Composable {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.25.sp,
                fontSize = 16.sp
            )
        )
    }

    if (primario) {
        Button(
            modifier = Modifier
                .size(width = 240.dp, height = 56.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1976D2), // Azul principal de la paleta
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp
            ),
            onClick = onClick
        ) {
            content()
        }
    } else {
        FilledTonalButton(
            modifier = Modifier
                .size(width = 240.dp, height = 56.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(0xFFE3F2FD), // Azul muy claro
                contentColor = Color(0xFF1565C0)    // Azul oscuro para el texto
            ),
            onClick = onClick
        ) {
            content()
        }
    }
}
