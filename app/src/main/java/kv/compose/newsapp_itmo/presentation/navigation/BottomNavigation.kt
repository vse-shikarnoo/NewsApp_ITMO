package kv.compose.newsapp.presentation.navigation

import kv.compose.newsapp.R

enum class BottomNavigation(
    val label: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route: Any,
) {
    News(
        label = R.string.news,
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
        route = Destinations.NewsList
    ),
    Favorites(
        label = R.string.favorites,
        selectedIcon = R.drawable.ic_favorite_filled,
        unselectedIcon = R.drawable.ic_favorite_outlined,
        route = Destinations.Favorites
    ),
    Settings(
        label = R.string.settings,
        selectedIcon = R.drawable.ic_settings_filled,
        unselectedIcon = R.drawable.ic_settings_outlined,
        route = Destinations.Settings
    )
}