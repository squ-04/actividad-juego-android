package com.example.demoapp.features.results

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ResultsViewModel : ViewModel() {

    var userName: String = "Juan Pérez"
    var score: Int = 100 // Cambia a 0 para probar la pantalla de pérdida

    fun isWinner(): Boolean {
        return score>0
    }
}
