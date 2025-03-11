package com.example.belajarandroid.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.belajarandroid.helper.DashboardScreen

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize().clickable {  },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home")
    }
}