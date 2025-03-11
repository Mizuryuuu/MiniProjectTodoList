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
import com.example.belajarandroid.layouts.DashboardScreen
import com.example.belajarandroid.layouts.LoginScreen
import com.example.belajarandroid.ui.theme.BelajarAndroidTheme
import com.example.belajarandroid.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BelajarAndroidTheme {
                val navController = rememberNavController()
                val viewModel = viewModel<LoginViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                NavHost(
                    startDestination = LoginScreen,
                    navController = navController,
                    builder = {
                        composable<LoginScreen> {
                            LoginScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
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