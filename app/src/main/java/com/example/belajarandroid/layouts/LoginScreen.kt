package com.example.belajarandroid.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.belajarandroid.components.CustomTextField
import com.example.belajarandroid.events.LoginEvent
import com.example.belajarandroid.helper.DashboardScreen
import com.example.belajarandroid.helper.DialogType
import com.example.belajarandroid.helper.MessageDialog
import com.example.belajarandroid.states.LoginState
import com.example.belajarandroid.viewmodels.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    LaunchedEffect(state.isDialog) {
        if (state.isDialog) {
            delay(3000)
            onEvent(LoginEvent.OnDismissDialog)
        }
    }
    LaunchedEffect(state.isStatus) {
        if (state.isStatus) {
            onNavigate(DashboardScreen)
        }
    }
    Box {
        if (state.isDialog) {
            MessageDialog(
                message = state.message,
                type = if (state.isStatus) DialogType.SUCCESS else DialogType.ERROR,
                onDismiss = { onEvent(LoginEvent.OnDismissDialog) }
            )
        }
        Surface {
            Column (
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp
                )
                Text(
                    text = "Login menggunakan username dan password anda."
                )
                CustomTextField(
                    modifier = modifier.fillMaxWidth(),
                    label = "Username",
                    hint = "Masukan username anda",
                    value = state.username,
                    onValueChange = { text ->
                        onEvent(LoginEvent.OnUsernameChange(text))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Username"
                        )
                    },
                )
                CustomTextField(
                    modifier = modifier.fillMaxWidth(),
                    label = "Password",
                    hint = "Masukan password anda",
                    value = state.password,
                    onValueChange = { text ->
                        onEvent(LoginEvent.OnPasswordChange(text))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (state.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Password",
                            modifier = Modifier.clickable {
                                onEvent(LoginEvent.OnPasswordVisibilityChanged)
                            }
                        )
                    },
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(LoginEvent.OnLogin)
                    }
                ) {
                    Text(text = "Login")
                }
            }
        }
    }

}

@Preview
@Composable
private fun LoginScreenPrev() {
    val viewModel = viewModel<LoginViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigate = {
        }
    )
}