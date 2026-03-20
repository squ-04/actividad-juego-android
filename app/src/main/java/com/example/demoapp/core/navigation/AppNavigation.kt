package com.example.demoapp.core.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.demoapp.features.game.GameScreen
import com.example.demoapp.features.home.HomeScreen
import com.example.demoapp.features.results.ResultsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

   Surface() {
       NavHost(
           navController = navController,
           startDestination = MainRoutes.Home
       ) {

           composable<MainRoutes.Home> {

               HomeScreen(
                   onNavigateToGame = { username ->
                       navController.navigate(MainRoutes.Game(username))
                   }
               )

           }

           composable<MainRoutes.Game> {
               val args = it.toRoute<MainRoutes.Game>()
               GameScreen(
                   username = args.username,
                   onNavigateToResults = { user, score ->
                       navController.navigate(MainRoutes.Results(user, score)) {
                           popUpTo(MainRoutes.Game(args.username)) { inclusive = true }
                       }
                   }
               )
           }

           composable<MainRoutes.Results> {
               val args = it.toRoute<MainRoutes.Results>()
               ResultsScreen(
                   username = args.username,
                   score = args.score,
                   onPlayAgain = {
                       navController.navigate(MainRoutes.Home) {                // Limpia el historial para que no pueda volver atrás a los resultados
                           popUpTo(MainRoutes.Home) { inclusive = true }
                       }
                   }
               )
           }

       }
   }
}
