package kv.compose.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kv.compose.newsapp.presentation.navigation.AppNavHost
import kv.compose.newsapp.presentation.navigation.BottomNavigation
import kv.compose.newsapp.presentation.navigation.Destinations
import kv.compose.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme by viewModel.theme.collectAsState()

            NewsAppTheme(theme) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                    ?: Destinations.NewsList::class.qualifiedName.orEmpty()
                val showBottomNavigation =
                    currentRoute in BottomNavigation.entries.map { it.route::class.qualifiedName }

                Scaffold(
                    modifier = Modifier
                        .testTag("main_scaffold")
                        .fillMaxSize(),
                    bottomBar = {
                        if (showBottomNavigation) {
                            Column {
                                HorizontalDivider(thickness = .2.dp)
                                BottomAppBar(
                                    modifier = Modifier.testTag("bottom_navigation"),
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    tonalElevation = 0.dp,
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    BottomNavigation.entries
                                        .forEach { navigationItem ->
                                            val isSelected by remember(currentRoute) {
                                                derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                                            }

                                            NavigationBarItem(
                                                modifier = Modifier
                                                    .testTag(navigationItem.name),
                                                selected = isSelected,
                                                label = {
                                                    Text(
                                                        text = stringResource(id = navigationItem.label),
                                                        style = MaterialTheme.typography.labelSmall
                                                    )
                                                },
                                                icon = {
                                                    Icon(
                                                        painter = painterResource(id = if (isSelected) navigationItem.selectedIcon else navigationItem.unselectedIcon),
                                                        contentDescription = stringResource(id = navigationItem.label),
                                                    )
                                                },
                                                onClick = {
                                                    navController.navigate(navigationItem.route)
                                                },
                                                colors = NavigationBarItemDefaults.colors(
                                                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                        elevation = NavigationBarDefaults.Elevation,
                                                    ),
                                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                                                    unselectedTextColor = MaterialTheme.colorScheme.onSurface
                                                )
                                            )
                                        }
                                }
                            }
                        }
                    }
                ) {
                    AppNavHost(
                        modifier = Modifier.padding(it),
                        navController = navController
                    )
                }
            }
        }
    }
}