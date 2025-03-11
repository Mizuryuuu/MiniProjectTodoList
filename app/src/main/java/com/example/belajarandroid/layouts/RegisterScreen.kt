package com.example.belajarandroid.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.belajarandroid.components.CustomTextField
import com.example.belajarandroid.events.RegisterEvent
import com.example.belajarandroid.helper.DashboardScreen
import com.example.belajarandroid.helper.DialogType
import com.example.belajarandroid.helper.LoginScreen
import com.example.belajarandroid.helper.MessageDialog
import com.example.belajarandroid.states.RegisterState
import com.example.belajarandroid.viewmodels.RegisterViewModel
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    val annotationText = buildAnnotatedString {
        append("Already have an account? ")
        pushStringAnnotation(tag = "Login", annotation = "login")
        withStyle(style = SpanStyle(color = Color.Blue)) { append("Login here") }
        pop()
    }
    LaunchedEffect(state.isDialog) {
        if (state.isDialog) {
            if (!state.isStatus) {
                delay(3000)
            }
            onEvent(RegisterEvent.OnDismissDialog)
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
                onDismiss = {onEvent(RegisterEvent.OnDismissDialog)}
            )
        }
        Surface {
            Column (
                modifier = modifier.fillMaxSize().
                padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Register",
                    fontSize = 24.sp
                )
                Text(
                    text = "Please create an account."
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Username",
                    hint = "Enter your username",
                    value = state.username,
                    onValueChange = { text ->
                        onEvent(RegisterEvent.OnUsernameChange(text))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Username",
                        )
                    }
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Email",
                    hint = "Enter your email",
                    value = state.email,
                    onValueChange = { text ->
                        onEvent(RegisterEvent.OnEmailChange(text))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                        )
                    }
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Password",
                    hint = "Enter your password",
                    value = state.password,
                    onValueChange = { text ->
                        onEvent(RegisterEvent.OnPasswordChange(text))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password",
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (state.passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Password",
                            modifier = modifier.clickable {
                                onEvent(RegisterEvent.OnPasswordVisibilityChange)
                            }
                        )
                    },
                    visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                CustomTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Confirm Password",
                    hint = "Confirm your password",
                    value = state.confirmPassword,
                    onValueChange = { text ->
                        onEvent(RegisterEvent.OnConfirmPasswordChange(text))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password",
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (state.confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Password",
                            modifier = modifier.clickable {
                                onEvent(RegisterEvent.OnConfirmPasswordVisibilityChange)
                            }
                        )
                    },
                    visualTransformation = if (state.confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                Button(
                    modifier = modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(RegisterEvent.OnRegister)
                    }
                ) {
                    Text(text = "Register")
                }
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = annotationText,
                        modifier = Modifier.clickable {
                            annotationText.getStringAnnotations(
                                tag = "Login",
                                start = 0,
                                end = annotationText.length
                            ).firstOrNull()?.let {
                                onNavigate(LoginScreen)
                            }
                        }
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun RegisterScreenPrev() {
    val viewModel = viewModel<RegisterViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    RegisterScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigate = {

        }

    )
}