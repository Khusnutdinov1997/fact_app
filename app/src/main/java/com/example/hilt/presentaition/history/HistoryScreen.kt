package com.example.hilt.presentaition.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hilt.presentaition.companent.FactCard


@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val history by viewModel.history.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    if (history.isEmpty()) {
        Text(
            text = "История пуста",
            modifier = Modifier
                .systemBarsPadding()
                .padding(16.dp)
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(history){ fact ->
                val isFactFavorite = favorites.contains(fact.id)
                FactCard(
                    fact,
                    isFavorite = isFactFavorite,
                    onFavoriteClick = {viewModel.toggleFavorite(fact.id)}
                )
            }
        }
    }
}