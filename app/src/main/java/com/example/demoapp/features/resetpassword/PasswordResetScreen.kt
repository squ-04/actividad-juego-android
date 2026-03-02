package com.example.demoapp.features.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demoapp.core.utils.RequestResult

@Composable
fun PasswordResetScreen(
    viewModel: PasswordResetViewModel = viewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val resetResult by viewModel.resetResult.collectAsState()

    LaunchedEffect(resetResult) {
        resetResult?.let { result ->
            val message = when (result) {
                is RequestResult.Success -> result.message
                is RequestResult.Failure -> result.errorMessage
            }
            snackbarHostState.showSnackbar(message)
            viewModel.resetResetResult()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                val isError = resetResult is RequestResult.Failure
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

            val focusRequesters = remember { List(5) { FocusRequester() } }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (i in 0..4) {
                    OutlinedTextField(
                        value = when (i) {
                            0 -> viewModel.codeDigit1.value
                            1 -> viewModel.codeDigit2.value
                            2 -> viewModel.codeDigit3.value
                            3 -> viewModel.codeDigit4.value
                            else -> viewModel.codeDigit5.value
                        },
                        onValueChange = {
                            if (it.length <= 1) {
                                when (i) {
                                    0 -> viewModel.codeDigit1.onChange(it)
                                    1 -> viewModel.codeDigit2.onChange(it)
                                    2 -> viewModel.codeDigit3.onChange(it)
                                    3 -> viewModel.codeDigit4.onChange(it)
                                    else -> viewModel.codeDigit5.onChange(it)
                                }
                                if (it.length == 1 && i < 4) {
                                    focusRequesters[i + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .width(50.dp)
                            .focusRequester(focusRequesters[i]),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.password.value,
                onValueChange = { viewModel.password.onChange(it) },
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Nueva Contraseña") },
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
                    viewModel.resetPassword()
                },
                enabled = viewModel.isFormValid,
                content = {
                    Text(text = "Restablecer Contraseña")
                }
            )
        }
    }
}