package com.example.demoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.demoapp.core.navigation.AppNavigation
import com.example.demoapp.features.home.HomeScreen
import com.example.demoapp.features.recoverypassword.PasswordRecoveryScreen
import com.example.demoapp.features.resetpassword.PasswordResetScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            /*LoginScreen(onNavigateToUsers = {
                Log.d("MainActivity", "Navegando a la pantalla de usuarios")
            })*/
            //RegisterScreen()
            //PasswordRecoveryScreen()
            AppNavigation()
            //PasswordResetScreen()
        }
    }
}