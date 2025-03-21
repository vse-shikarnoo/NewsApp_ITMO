package kv.compose.newsapp.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kv.compose.newsapp.R
import kv.compose.newsapp.presentation.components.EmptyStateComponent
import kv.compose.newsapp.presentation.components.NewsList
import kv.compose.newsapp.presentation.model.NewsUiModel
import kv.compose.newsapp.presentation.navigation.Destinations
import kv.compose.newsapp.ui.theme.NewsAppTheme

@Composable
fun SearchNewsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SearchNewsScreenContent(
        modifier = modifier,
        uiState = uiState,
        onClickNews = {
            navController.navigate(Destinations.NewsDetails(it))
        },
        onClickBack = {
            navController.navigateUp()
        },
        onSearchValueChange = { searchString ->
            viewModel.updateSearchValue(searchString)
            if (searchString.trim().isNotEmpty()) {
                viewModel.getNews(searchString)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNewsScreenContent(
    uiState: SearchNewsUiState,
    onClickBack: () -> Unit,
    onClickNews: (NewsUiModel) -> Unit,
    onSearchValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .testTag(stringResource(R.string.search_news_screen))
            .fillMaxSize(),
        topBar = {
            val focusRequester = remember { FocusRequester() }

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            TopAppBar(
                title = {
                    SearchTextField(
                        focusRequester = focusRequester,
                        searchValue = uiState.searchValue,
                        onSearchValueChange = onSearchValueChange
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag(stringResource(R.string.back_icon)),
                        onClick = {
                            onClickBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier.testTag(stringResource(R.string.clear_search)),
                        onClick = {
                            onSearchValueChange("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        val newsPaging = uiState.news?.collectAsLazyPagingItems()

        Box(
            modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (newsPaging != null) {
                NewsList(
                    newsPaging = newsPaging,
                    onClickNews = {
                        onClickNews(it)
                    }
                )
            } else {
                EmptyStateComponent(
                    modifier = Modifier.align(Alignment.Center),
                    message = stringResource(R.string.no_news_available)
                )
            }
        }
    }
}

@Composable
fun SearchTextField(
    focusRequester: FocusRequester,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier
            .testTag(stringResource(R.string.search_news_text_field))
            .focusRequester(focusRequester),
        value = searchValue,
        onValueChange = onSearchValueChange,
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                style =
                MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(.5f)
                )
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        colors =
        TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun SearchNewsScreenPreview() {
    NewsAppTheme {
        SearchNewsScreenContent(
            uiState = SearchNewsUiState(),
            onClickBack = {},
            onClickNews = {},
            onSearchValueChange = {}
        )
    }
}