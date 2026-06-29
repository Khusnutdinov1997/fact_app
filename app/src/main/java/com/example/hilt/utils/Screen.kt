package com.example.hilt.utils

sealed class Screen(private val route: String) {
    object MainScreen: Screen("main_screen")
    object FavoriteScreen: Screen(route = "favorite_screen")
    object HistoryScreen: Screen(route = "history_screen")
}