package com.example.hilt.presentaition.favorites

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
fun FavoriteScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites by viewModel.facts.collectAsState()

    if(favorites.isEmpty()){
        Text(
            text = "Избранное пусто",
            modifier = Modifier
                .padding(16.dp)
        )
    }else{
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favorites){ fact ->
                FactCard(
                    fact,
                    isFavorite = true,
                    onFavoriteClick = {viewModel.toggleFavorite(fact.id)}
                )
            }
        }
    }
}

