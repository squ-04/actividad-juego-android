package com.example.demoapp.features.recoverypassword

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.core.utils.RequestResult

@Composable
fun PasswordRecoveryScreen(
    viewModel: PasswordRecoveryViewModel = viewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val recoveryResult by viewModel.recoveryResult.collectAsState()

    LaunchedEffect(recoveryResult) {
        recoveryResult?.let { result ->
            val message = when (result) {
                is RequestResult.Success -> result.message
                is RequestResult.Failure -> result.errorMessage
            }
            snackbarHostState.showSnackbar(message)
            viewModel.resetRecoveryResult()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                val isError = recoveryResult is RequestResult.Failure
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
                value = viewModel.email.value,
                onValueChange = { viewModel.email.onChange(it) },
                label = { Text(text = "Email") },
                isError = viewModel.email.error != null,
                supportingText = viewModel.email.error?.let { error ->
                    { Text(text = error) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Button(
                onClick = {
                    viewModel.sendPasswordResetEmail()
                },
                enabled = viewModel.isFormValid,
                content = {
                    Text(text = "Recuperar Contraseña")
                }
            )
        }
    }
}