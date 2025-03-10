package com.example.belajarandroid.layouts

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarandroid.components.CustomTextField

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    
    val username by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }

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
                value = username,
                onValueChange = { text ->
                    text
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
                value = password,
                onValueChange = { text ->
                    text
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
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
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = {

                }
            ) {
                Text(text = "Login")
            }
        }
    }

}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}