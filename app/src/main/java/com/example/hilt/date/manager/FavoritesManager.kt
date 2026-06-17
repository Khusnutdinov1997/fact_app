package com.example.hilt.date.manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesManager @Inject constructor(){

    private val _favorites = MutableStateFlow<Set<Int>>(emptySet())
    val favorites = _favorites.asStateFlow()

    fun addFactToFavorites(id: Int){
        _favorites.value = _favorites.value + id
    }

    fun removeFromFavorites(id: Int){
        _favorites.value = _favorites.value - id
    }

    fun isFavorite(id: Int): Boolean{
        return _favorites.value.contains(id)
    }
}