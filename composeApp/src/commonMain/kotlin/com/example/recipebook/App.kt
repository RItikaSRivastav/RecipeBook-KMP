package com.example.recipebook

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recipebook.details.DetailScreen
import com.example.recipebook.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class Screen{
    Home, Detail
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var currentScreen by remember { mutableStateOf(Screen.Home) }
            var selectedId by remember { mutableStateOf("") }
          when(currentScreen){
               Screen.Home -> {
                   HomeScreen {
                       currentScreen = Screen.Detail
                       selectedId = it.idMeal
                   }
               }
              Screen.Detail -> {
                  DetailScreen(
                      id = selectedId,
                      navigateBack = {
                          currentScreen = Screen.Home
                          selectedId = ""
                      }
                  )
              }
          }
        }
    }
}