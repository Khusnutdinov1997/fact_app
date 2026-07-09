package com.example.hilt.date.repository

import com.example.hilt.model.Category
import com.example.hilt.model.Fact
import javax.inject.Inject
import kotlin.collections.listOf
import kotlin.to

class FactRepositoryImpl @Inject constructor(): FactRepository {
    private val factsByCategory = mapOf(
        Category.ANIMALS to listOf(
            Fact(1, "факт животного 1", Category.ANIMALS),
            Fact(2, "факт животного 2", Category.ANIMALS),
            Fact(3, "факт животного 3", Category.ANIMALS),
        ),
                Category.HISTORY to listOf(
                    Fact(1, "факт истории 1", Category.HISTORY),
            Fact(2, "факт истории 2", Category.HISTORY),
             Fact(3, "факт истории 3", Category.HISTORY),
        )
    )

// запомнить .flatten()

    override fun getRandomFact(category: Category): Fact {
        val facts = when(category){
            Category.RANDOM -> factsByCategory.values.flatten()
            else -> factsByCategory[category] ?: emptyList()
        }
        return facts.random()
    }

    override fun getFactById(id: Int): Fact? {
        return factsByCategory.values.flatten().find { it.id == id }
    }
}