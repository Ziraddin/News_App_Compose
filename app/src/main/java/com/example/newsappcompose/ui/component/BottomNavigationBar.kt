package com.example.newsappcompose.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsappcompose.ui.screen.Screen


@Composable
fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(Screen.Search, Screen.Bookmark)
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ) {

        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = screen.icon!!),
                        contentDescription = stringResource(id = screen.label!!),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        stringResource(
                            id = screen.label!!
                        )
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}
