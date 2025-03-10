package com.example.belajarandroid.layouts

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarandroid.components.CustomTextField

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    val username by remember { mutableStateOf("") }
    val email by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }
    val confirmPassword by remember { mutableStateOf("") }
    val confirmPasswordVisible by remember { mutableStateOf(false) }

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
                text = "Silahkan anda untuk membuat akun"
            )
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Username",
                hint = "Masukan username anda",
                value = username,
                onValueChange = {

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
                hint = "Masukan email anda",
                value = email,
                onValueChange = {

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
                hint = "Masukan password anda",
                value = password,
                onValueChange = {

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password"
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Konfirmasi Password",
                hint = "Masukan ulang password anda",
                value = confirmPassword,
                onValueChange = {

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password",
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password"
                    )
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
            )
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = {

                }
            ) {
                Text(text = "Register")
            }
        }
    }

}

@Preview
@Composable
private fun RegisterScreenPrev() {
    RegisterScreen()
}