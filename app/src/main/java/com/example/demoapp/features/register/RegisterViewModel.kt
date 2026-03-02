package com.example.demoapp.features.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.demoapp.core.utils.RequestResult
import com.example.demoapp.core.utils.ValidatedField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {

    private val _registerResult = MutableStateFlow<RequestResult?>(null)
    val registerResult: StateFlow<RequestResult?> = _registerResult.asStateFlow()

    val name = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "El nombre es obligatorio"
            else -> null
        }
    }

    val city = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "La ciudad es obligatoria"
            else -> null
        }
    }

    val address = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "La dirección es obligatoria"
            else -> null
        }
    }

    val email = ValidatedField("") { value ->
        when {
            value.isEmpty() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Ingresa un email válido"
            else -> null
        }
    }

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
        get() = name.isValid &&
                city.isValid &&
                address.isValid &&
                email.isValid &&
                password.isValid &&
                confirmPassword.isValid

    fun resetForm() {
        name.reset()
        city.reset()
        address.reset()
        email.reset()
        password.reset()
        confirmPassword.reset()
    }

    fun register() {
        if (isFormValid) {
            // Simulate registration process
            _registerResult.value = RequestResult.Success("Registro exitoso")
        } else {
            _registerResult.value = RequestResult.Failure("Por favor, corrige los errores en el formulario")
        }
    }

    fun resetRegisterResult() {
        _registerResult.value = null
    }
}