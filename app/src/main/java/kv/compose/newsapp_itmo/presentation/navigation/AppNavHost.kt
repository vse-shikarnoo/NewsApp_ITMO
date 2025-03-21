package kv.compose.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kv.compose.newsapp.presentation.favorites.FavoritesScreen
import kv.compose.newsapp.presentation.language.LanguageScreen
import kv.compose.newsapp.presentation.model.NewsUiModel
import kv.compose.newsapp.presentation.newsdetails.NewsDetailsScreen
import kv.compose.newsapp.presentation.newslist.NewsListScreen
import kv.compose.newsapp.presentation.search.SearchNewsScreen
import kv.compose.newsapp.presentation.settings.SettingsScreen
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.NewsList
    ) {
        composable<Destinations.NewsList> {
            NewsListScreen(
                navController = navController
            )
        }

        composable<Destinations.NewsDetails>(
            typeMap = mapOf(typeOf<NewsUiModel>() to NewsUiModelParameterType)
        ) { backStackEntry ->
            val news = backStackEntry.toRoute<Destinations.NewsDetails>().news
            NewsDetailsScreen(
                news = news,
                navController = navController
            )
        }

        composable<Destinations.SearchNews> {
            SearchNewsScreen(
                navController = navController
            )
        }

        composable<Destinations.Favorites> {
            FavoritesScreen(
                navController = navController
            )
        }

        composable<Destinations.Settings> {
            SettingsScreen(
                navController = navController
            )
        }

        composable<Destinations.Language> {
            LanguageScreen(
                navController = navController
            )
        }
    }
}