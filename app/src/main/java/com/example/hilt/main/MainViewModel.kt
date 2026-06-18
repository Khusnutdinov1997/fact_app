package com.example.hilt.main

import androidx.lifecycle.ViewModel
import com.example.hilt.date.manager.FavoritesManager
import com.example.hilt.date.manager.HistoryManager
import com.example.hilt.date.repository.FactRepository
import com.example.hilt.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FactRepository,
    private val favoritesManager: FavoritesManager,
    private val historyManager: HistoryManager
): ViewModel(){
    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    fun onEvent(mainEvent: MainEvent){
        when(mainEvent){
            is MainEvent.LoadNewFact -> {}
            is MainEvent.ChangeCategory -> {changeCategory(mainEvent.category)}
            is MainEvent.ToggleFavorite -> {toggleFavorite(mainEvent.id)}
        }
    }

    fun toggleFavorite(id: Int){
        if(favoritesManager.isFavorite(id))
            favoritesManager.removeFromFavorites(id)
        else
            favoritesManager.addFactToFavorites(id)
    }

    fun changeCategory(category: Category){
        _mainUiState.value = _mainUiState.value.copy(selectedCategory = category)
    }

}