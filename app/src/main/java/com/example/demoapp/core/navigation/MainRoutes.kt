package com.example.demoapp.core.navigation

import kotlinx.serialization.Serializable

sealed class MainRoutes {

    @Serializable
    data object Home : MainRoutes()

    @Serializable
    data class Game( val username: String) : MainRoutes()

    @Serializable
    data class Results (val username: String, val score: Int) : MainRoutes ()

}