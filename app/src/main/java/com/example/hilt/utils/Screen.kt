package com.example.hilt.utils

sealed class Screen(val route: String) {
    object MainScreen: Screen( route = "main_screen")
    object FavoriteScreen: Screen(route = "favorite_screen")
    object HistoryScreen: Screen(route = "history_screen")
}