package com.example.hilt.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    ) {

    val mainUiState by viewModel.mainUiState.collectAsState()
    val favorites by viewModel.favoritesManager.favorites.collectAsState()
}