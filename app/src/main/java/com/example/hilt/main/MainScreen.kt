package com.example.hilt.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hilt.presentaition.companent.CategorySelector
import com.example.hilt.presentaition.companent.FactCard


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {

    val mainUiState by viewModel.mainUiState.collectAsState()
    val favorites by viewModel.favoritesManager.favorites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CategorySelector(
                selectedCategory = mainUiState.selectedCategory,
                onSelect = { category ->
                    viewModel.onEvent(mainEvent = MainEvent.ChangeCategory(category))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            when {
                mainUiState.isLoadingFact -> CircularProgressIndicator()
                mainUiState.error != null -> Text(
                    text = "Ошибка: ${mainUiState.error}",
                    color = MaterialTheme.colorScheme.error
                )

                mainUiState.currentFact != null -> FactCard(
                    fact = mainUiState.currentFact!!,
                    isFavorite = favorites.contains(mainUiState.currentFact!!.id),
                    onFavoriteClick = {
                        viewModel.onEvent(
                            mainEvent = MainEvent.ToggleFavorite(mainUiState.currentFact!!.id)
                        )
                    }
                )

                else -> Text(
                    text = "Выберите категорию",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onClick = { viewModel.onEvent(MainEvent.LoadNewFact) }
        ) {
            Text(
                text = "Узнать что-то новое"
            )
        }
    }
}