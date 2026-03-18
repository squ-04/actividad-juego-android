package com.example.demoapp.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.demoapp.core.utils.ValidatedField

class HomeViewModel : ViewModel() {

    val userName = ValidatedField("") { value ->
        when {
            value.isBlank() -> "El nombre de usuario es obligatorio"
            value.length < 3 -> "El nombre debe tener al menos 3 caracteres"
            else -> null
        }
    }

    var showHelpDialog by mutableStateOf(false)
        private set

    val isFormValid: Boolean
        get() = userName.isValid

    fun onStartGame(onNavigateToGame: (String) -> Unit) {
        if (isFormValid) {
            onNavigateToGame(userName.value)
        } else {
            userName.onChange(userName.value) // Trigger error display
        }
    }

    fun onShowHelpDialog() {
        showHelpDialog = true
    }

    fun onDismissHelpDialog() {
        showHelpDialog = false
    }
}
