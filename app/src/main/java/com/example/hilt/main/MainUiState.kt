package com.example.hilt.main

import com.example.hilt.model.Category
import com.example.hilt.model.Fact

data class MainUiState(
    val currentFact: Fact? = null,
    val selectedCategory: Category = Category.ANIMALS,
    val isLoadingFact: Boolean = false,
    val error: String? = null
)
