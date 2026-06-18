package com.example.hilt.presentaition.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.date.manager.FavoritesManager
import com.example.hilt.date.repository.FactRepository
import com.example.hilt.model.Fact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val factRepository: FactRepository,
    private val favoritesManager: FavoritesManager
): ViewModel() {

    private val _facts = MutableStateFlow<List<Fact>>(emptyList())
    val facts = _facts.asStateFlow()

    /*
    Блок init используется как пусковой механизм для настройки реактивных связей.
    Он будет запущен после того, как Hilt создаст экземпляр viewModel.
     */
    init {
        viewModelScope.launch {
            favoritesManager.favorites.collect { ids ->
                _facts.value = ids.mapNotNull { id ->
                    factRepository.getFactById(id)
                }
            }
        }
    }

    fun toggleFavorite(id: Int){
        if(favoritesManager.isFavorite(id))
            favoritesManager.removeFromFavorites(id)
        else
            favoritesManager.addFactToFavorites(id)
    }



}