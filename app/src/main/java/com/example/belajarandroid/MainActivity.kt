package com.example.belajarandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.belajarandroid.helper.DashboardScreen
import com.example.belajarandroid.helper.LoginScreen
import com.example.belajarandroid.helper.RegisterScreen
import com.example.belajarandroid.layouts.DashboardScreen
import com.example.belajarandroid.layouts.LoginScreen
import com.example.belajarandroid.layouts.RegisterScreen
import com.example.belajarandroid.ui.theme.BelajarAndroidTheme
import com.example.belajarandroid.viewmodels.LoginViewModel
import com.example.belajarandroid.viewmodels.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BelajarAndroidTheme {
                val navController = rememberNavController()
                val loginViewModel = viewModel<LoginViewModel>()
                val loginState by loginViewModel.state.collectAsStateWithLifecycle()
                val registerViewModel = viewModel<RegisterViewModel>()
                val registerState by registerViewModel.state.collectAsStateWithLifecycle()
                NavHost(
                    startDestination = LoginScreen,
                    navController = navController,
                    builder = {
                        composable<LoginScreen> {
                            LoginScreen(
                                state = loginState,
                                onEvent = loginViewModel::onEvent,
                                onNavigate = { destination ->
                                    navController.navigate(destination)
                                }
                            )
                        }
                        composable<RegisterScreen> {
                            RegisterScreen(
                                state = registerState,
                                onEvent = registerViewModel::onEvent,
                                onNavigate = { destination ->
                                    navController.navigate(destination)
                                }
                            )
                        }
                        composable<DashboardScreen> {
                            DashboardScreen()
                        }
                    }
                )
            }
        }
    }
}