package com.example.demoapp.features.recoverypassword

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.demoapp.core.utils.RequestResult
import com.example.demoapp.core.utils.ValidatedField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordRecoveryViewModel : ViewModel() {

    private val _recoveryResult = MutableStateFlow<RequestResult?>(null)
    val recoveryResult: StateFlow<RequestResult?> = _recoveryResult.asStateFlow()

    val email = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Ingresa un email válido"
            else -> null
        }
    }

    val isFormValid: Boolean
        get() = email.isValid

    fun sendPasswordResetEmail() {
        if (isFormValid) {
            // Simulate sending a password reset email
            _recoveryResult.value = RequestResult.Success("Se ha enviado un correo de recuperación")
        } else {
            _recoveryResult.value = RequestResult.Failure("Por favor, ingresa un email válido")
        }
    }

    fun resetRecoveryResult() {
        _recoveryResult.value = null
    }
}