package com.example.hilt.model

import com.example.hilt.R

enum class Category(
    val displayName: String,
    val icon: Int
) {
    ANIMALS("Животные", R.drawable.outline_pets_24),
    HISTORY("История", R.drawable.outline_book_24),
    SCIENCE("Наука", R.drawable.outline_science_24),
    RANDOM("Случайное", R.drawable.outline_question_mark_24)
}