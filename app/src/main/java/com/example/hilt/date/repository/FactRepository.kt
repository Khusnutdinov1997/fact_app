package com.example.hilt.date.repository

import com.example.hilt.model.Category
import com.example.hilt.model.Fact

interface FactRepository {
    fun getRandomFact(category: Category): Fact
    fun getFactById(id: Int): Fact?

}