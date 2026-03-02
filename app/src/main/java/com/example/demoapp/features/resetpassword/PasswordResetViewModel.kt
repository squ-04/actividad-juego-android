package com.example.demoapp.features.resetpassword

import androidx.lifecycle.ViewModel
import com.example.demoapp.core.utils.RequestResult
import com.example.demoapp.core.utils.ValidatedField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordResetViewModel : ViewModel() {

    private val _resetResult = MutableStateFlow<RequestResult?>(null)
    val resetResult: StateFlow<RequestResult?> = _resetResult.asStateFlow()

    val codeDigit1 = ValidatedField("") { v -> if (v.length == 1) null else " " }
    val codeDigit2 = ValidatedField("") { v -> if (v.length == 1) null else " " }
    val codeDigit3 = ValidatedField("") { v -> if (v.length == 1) null else " " }
    val codeDigit4 = ValidatedField("") { v -> if (v.length == 1) null else " " }
    val codeDigit5 = ValidatedField("") { v -> if (v.length == 1) null else " " }

    val password = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "La contraseña es obligatoria"
            value.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
            else -> null
        }
    }

    val confirmPassword = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "Confirma la contraseña"
            value != password.value -> "Las contraseñas no coinciden"
            else -> null
        }
    }

    val isFormValid: Boolean
        get() = codeDigit1.isValid && codeDigit2.isValid && codeDigit3.isValid &&
                codeDigit4.isValid && codeDigit5.isValid && password.isValid && confirmPassword.isValid

    fun resetPassword() {
        if (isFormValid) {
            // Simulate password reset process
            _resetResult.value = RequestResult.Success("Contraseña restablecida con éxito")
        } else {
            _resetResult.value = RequestResult.Failure("Por favor, completa el formulario correctamente")
        }
    }

    fun resetResetResult() {
        _resetResult.value = null
    }
}