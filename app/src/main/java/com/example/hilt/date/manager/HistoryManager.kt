package com.example.hilt.date.manager

import com.example.hilt.model.Fact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryManager @Inject constructor(){
    private val _readFacts = MutableStateFlow<List<Fact>>(emptyList())
    val readFacts = _readFacts.asStateFlow()

     fun addFactToHistory(fact: Fact){
        _readFacts.value = listOf(fact) + _readFacts.value.take(19)
    }
}