package com.example.hilt.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hilt.R
import com.example.hilt.main.MainScreen
import com.example.hilt.presentaition.favorites.FavoriteScreen
import com.example.hilt.presentaition.history.HistoryScreen
import com.example.hilt.utils.Screen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val items = listOf(
        BottomItem(
            label = "Главная",
            iconRes = R.drawable.question,
            route = Screen.MainScreen.route
        ),
        BottomItem(
            label = "Избранное",
            iconRes = R.drawable.favorite,
            route = Screen.FavoriteScreen.route
        ),
        BottomItem(
            label = "История",
            iconRes = R.drawable.history,
            route = Screen.HistoryScreen.route
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDistance = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        currentDistance == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                    launchSingleTop = true
                                    restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(item.iconRes),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = item.label
                            )
                        }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.MainScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            composable(Screen.HistoryScreen.route){
                HistoryScreen()
            }
            composable(Screen.FavoriteScreen.route){
                FavoriteScreen()
            }
            composable(Screen.MainScreen.route){
                MainScreen()
            }
        }
    }

}

data class BottomItem(
    val label: String,
    val iconRes: Int,
    val route: String
)