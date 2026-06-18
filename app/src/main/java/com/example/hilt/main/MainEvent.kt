package com.example.hilt.main

import com.example.hilt.model.Category

// sealed - для того чтобы закрыть доступ к интерфейсу. Доступ будут иметь только его наследники,
// описанные в теле
sealed interface MainEvent {
    data object LoadNewFact: MainEvent
    data class ChangeCategory(
        val category: Category
    ): MainEvent
    data class ToggleFavorite(
        val id: Int
    ): MainEvent
}