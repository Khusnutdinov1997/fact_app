package com.example.hilt.presentaition.history

import androidx.lifecycle.ViewModel
import com.example.hilt.date.manager.FavoritesManager
import com.example.hilt.date.manager.HistoryManager
import com.example.hilt.model.Fact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyManager : HistoryManager,
    private val favoritesManager: FavoritesManager
): ViewModel() {

    val history: StateFlow<List<Fact>> = historyManager.readFacts
    val favorites: StateFlow<Set<Int>> = favoritesManager.favorites

    fun toggleFavorite(id: Int){
        if(favoritesManager.isFavorite(id))
            favoritesManager.removeFromFavorites(id)
        else
            favoritesManager.addFactToFavorites(id)
    }

}