package com.nnt.navigator

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nnt.core.R
import com.nnt.jetpackcomposewithcleanarch.ui.theme.PrimaryColor

sealed class BottomNavItem(@StringRes var title: Int, var icon: Int, var screen_route: String){
    object Home : BottomNavItem(R.string.home, R.drawable.ic_home_white, Destinations.Home.route)
    object Favourite: BottomNavItem(R.string.search, R.drawable.ic_baseline_search_24, Destinations.Search.route)
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favourite,
    )
    BottomNavigation(
        backgroundColor = PrimaryColor,
        contentColor = White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            val title = stringResource(id = item.title)
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = title) },
                label = { Text(text = title,
                    fontSize = 9.sp) },
                selectedContentColor = White,
                unselectedContentColor = White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}