package com.example.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hilt.date.manager.FavoritesManager
import com.example.hilt.date.manager.HistoryManager
import com.example.hilt.model.Category
import com.example.hilt.model.Fact
import com.example.hilt.presentaition.favorites.FavoriteScreen
import com.example.hilt.presentaition.favorites.FavoritesViewModel
import com.example.hilt.presentaition.history.HistoryScreen
import com.example.hilt.presentaition.history.HistoryViewModel
import com.example.hilt.ui.theme.HiltTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var historyManager: HistoryManager
    @Inject lateinit var favoritesManager: FavoritesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        seedMockData()

        enableEdgeToEdge()
        setContent {
            HiltTheme {
                //FavoriteScreen()
                HistoryScreen()
            }
        }
    }

    private fun seedMockData() {
        val fact1 = Fact(1, "Кот проводит 70% своей жизни во сне.",
            Category.ANIMALS)
        val fact2 = Fact(
            2, "Первый полет самолета длился всего 12 секунд.",
            Category.HISTORY
        )

        historyManager.addFactToHistory(fact1)
        historyManager.addFactToHistory(fact2)

        favoritesManager.addFactToFavorites(1)
    }
}
