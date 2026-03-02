package com.example.demoapp.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.core.utils.RequestResult
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val registerResult by viewModel.registerResult.collectAsState()

    LaunchedEffect(registerResult) {
        registerResult?.let { result ->
            val message = when (result) {
                is RequestResult.Success -> result.message
                is RequestResult.Failure -> result.errorMessage
            }
            snackbarHostState.showSnackbar(message)

            if (result is RequestResult.Success) {
                delay(1000)
                viewModel.resetForm()
            }
            viewModel.resetRegisterResult()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                val isError = registerResult is RequestResult.Failure
                Snackbar(
                    containerColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ) {
                    Text(data.visuals.message)
                }
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.CenterVertically)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.name.value,
                onValueChange = { viewModel.name.onChange(it) },
                label = { Text(text = "Nombre") },
                isError = viewModel.name.error != null,
                supportingText = viewModel.name.error?.let { error ->
                    { Text(text = error) }
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.city.value,
                onValueChange = { viewModel.city.onChange(it) },
                label = { Text(text = "Ciudad") },
                isError = viewModel.city.error != null,
                supportingText = viewModel.city.error?.let { error ->
                    { Text(text = error) }
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.address.value,
                onValueChange = { viewModel.address.onChange(it) },
                label = { Text(text = "Dirección") },
                isError = viewModel.address.error != null,
                supportingText = viewModel.address.error?.let { error ->
                    { Text(text = error) }
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email.value,
                onValueChange = { viewModel.email.onChange(it) },
                label = { Text(text = "Email") },
                isError = viewModel.email.error != null,
                supportingText = viewModel.email.error?.let { error ->
                    { Text(text = error) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.password.value,
                onValueChange = { viewModel.password.onChange(it) },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Contraseña") },
                isError = viewModel.password.error != null,
                supportingText = viewModel.password.error?.let { error ->
                    { Text(text = error) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.confirmPassword.value,
                onValueChange = { viewModel.confirmPassword.onChange(it) },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Confirmar Contraseña") },
                isError = viewModel.confirmPassword.error != null,
                supportingText = viewModel.confirmPassword.error?.let { error ->
                    { Text(text = error) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Button(
                onClick = {
                    viewModel.register()
                },
                enabled = viewModel.isFormValid,
                content = {
                    Text(text = "Registrarse")
                }
            )
        }
    }
}