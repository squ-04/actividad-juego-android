package com.example.demoapp.features.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MemoryCardState(
    val id: Int,
    val content: String, // Representa el animal (puede ser un emoji o nombre de recurso)
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)

data class GameUiState(
    val cards: List<MemoryCardState> = emptyList(),
    val score: Int = 0,
    val attempts: Int = 16,
    val isGameOver: Boolean = false,
    val isVictory: Boolean = false
)

class GameViewModel : ViewModel() {

    companion object {
        const val TOTAL_ATTEMPTS = 16
        const val MAX_SCORE_PER_PAIR = 100 // Puntaje máximo por par
    }

    private val _uiState = MutableStateFlow(GameUiState(attempts = TOTAL_ATTEMPTS))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val animals = listOf("🦊", "🐶", "🐱", "🐭", "🐹", "🐰", "🐻", "🐼")
    private var flippedCards = mutableListOf<Int>()

    init {
        resetGame()
    }

    fun resetGame() {
        val gameCards = (animals + animals)
            .shuffled()
            .mapIndexed { index, animal ->
                MemoryCardState(id = index, content = animal)
            }
        _uiState.value = GameUiState(cards = gameCards, attempts = TOTAL_ATTEMPTS)
        flippedCards.clear()
    }

    fun onCardClicked(cardId: Int) {
        val currentState = _uiState.value
        if (currentState.isGameOver || currentState.isVictory) return
        
        val card = currentState.cards.find { it.id == cardId }
        if (card == null || card.isFlipped || card.isMatched || flippedCards.size >= 2) return

        // Voltear la carta
        _uiState.update { state ->
            state.copy(
                cards = state.cards.map {
                    if (it.id == cardId) it.copy(isFlipped = true) else it
                }
            )
        }
        flippedCards.add(cardId)

        if (flippedCards.size == 2) {
            viewModelScope.launch {
                delay(1000) // Esperar un segundo para que el usuario vea la segunda carta
                checkMatch()
            }
        }
    }

    private fun checkMatch() {
        val card1Id = flippedCards[0]
        val card2Id = flippedCards[1]
        
        val card1 = _uiState.value.cards.find { it.id == card1Id }
        val card2 = _uiState.value.cards.find { it.id == card2Id }

        if (card1?.content == card2?.content) {
            // Es un par
            _uiState.update { state ->
                val newCards = state.cards.map {
                    if (it.id == card1Id || it.id == card2Id) it.copy(isMatched = true) else it
                }
                
                // Aplicamos la fórmula: Puntaje = (R / T) * M
                // R = intentos restantes, T = intentos totales, M = puntaje máximo por par
                val pointsForPair = (state.attempts.toDouble() / TOTAL_ATTEMPTS * MAX_SCORE_PER_PAIR).toInt()
                
                state.copy(
                    cards = newCards,
                    score = state.score + pointsForPair,
                    isVictory = newCards.all { it.isMatched }
                )
            }
        } else {
            // No es un par
            _uiState.update { state ->
                val newAttempts = state.attempts - 1
                state.copy(
                    cards = state.cards.map {
                        if (it.id == card1Id || it.id == card2Id) it.copy(isFlipped = false) else it
                    },
                    attempts = newAttempts,
                    isGameOver = newAttempts <= 0
                )
            }
        }
        flippedCards.clear()
    }
}
