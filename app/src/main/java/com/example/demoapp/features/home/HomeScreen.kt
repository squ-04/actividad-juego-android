package com.example.demoapp.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demoapp.R
import com.example.demoapp.core.components.ButtonDemo

@Composable
fun HomeScreen(){
    //Se usa el column para establcer una estructrua vertical
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        //las letras con el recurso de imagen de jetpack deben ser minusculas, sin espacios y
        // livianas
        Image(
            painter = painterResource(id = R.drawable.logouniquindio),
            contentDescription = "Logo de la aplicacion demo"
        )
        Text(
            text = "La aplicacion xxxx para la uniquindio"
        )
        //Para los botones necesitamos la ufuncion onClick que es obligatoria en este Composable

        ButtonDemo(
            icon = Icons.Default.Done,
            contentDescription = "Icono de login",
            onClick = {/* TODO*/},
            text = "Iniciar Sesion"
        )
        ButtonDemo(
            primario = false,
            icon = Icons.Default.Person,
            "Icono de registro",
            onClick = {/* TODO*/},
            text = "Crear una cuenta"
        )


    }
}