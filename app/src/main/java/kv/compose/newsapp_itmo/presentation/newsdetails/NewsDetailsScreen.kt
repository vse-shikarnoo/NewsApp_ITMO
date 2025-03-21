package kv.compose.newsapp.presentation.newsdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kv.compose.newsapp.R
import kv.compose.newsapp.presentation.components.NewsImage
import kv.compose.newsapp.presentation.model.NewsUiModel
import kv.compose.newsapp.presentation.utils.shareLink
import kv.compose.newsapp.presentation.utils.toHumanReadableDateTIme
import kv.compose.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsDetailsScreen(
    news: NewsUiModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NewsDetailsViewModel = hiltViewModel()
) {
    val addedToFavorites by viewModel.isFavorite(news).collectAsState(false)
    val context = LocalContext.current

    NewsDetailsScreenContent(
        modifier = modifier,
        news = news,
        addedToFavorites = addedToFavorites,
        onClickBack = {
            navController.navigateUp()
        },
        onClickShare = {
            context.shareLink(news.url)
        },
        onClickFavorite = {
            if (addedToFavorites) {
                viewModel.removeFavorite(news)
            } else {
                viewModel.addFavorite(news)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreenContent(
    news: NewsUiModel,
    addedToFavorites: Boolean,
    onClickBack: () -> Unit,
    onClickShare: () -> Unit,
    onClickFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag(stringResource(id = R.string.back_icon)),
                        onClick = onClickBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon)
                        )
                    }
                },
                title = {},
                actions = {
                    IconButton(
                        modifier = Modifier.testTag(stringResource(id = R.string.share_icon)),
                        onClick = onClickShare
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = stringResource(R.string.share_icon)
                        )
                    }

                    IconButton(
                        modifier = Modifier.testTag(
                            if (addedToFavorites) {
                                stringResource(R.string.added_to_favorite_icon)
                            } else {
                                stringResource(R.string.not_added_to_favorite_icon)
                            }
                        ),
                        onClick = onClickFavorite
                    ) {
                        Icon(
                            imageVector =
                            if (addedToFavorites) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = if (addedToFavorites) {
                                stringResource(R.string.added_to_favorite_icon)
                            } else {
                                stringResource(R.string.not_added_to_favorite_icon)
                            },
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            item {
                Text(
                    text =
                    if (news.author.isEmpty()) {
                        news.source
                    } else {
                        stringResource(R.string.by, news.author, news.source)
                    },
                    style =
                    MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
                Text(
                    text = news.publishedAt.toHumanReadableDateTIme(),
                    style =
                    MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
            }

            item {
                NewsImage(
                    imageUrl = news.imageUrl,
                    contentDescription = news.title,
                    modifier =
                    Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = news.description,
                    style =
                    MaterialTheme.typography.bodySmall.copy(
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
            }

            item {
                Text(
                    text = news.content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewsDetailsScreenPreview() {
    NewsAppTheme {
        NewsDetailsScreenContent(
            addedToFavorites = true,
            news =
            NewsUiModel(
                title = "Title",
                description = "Description",
                content = "Content",
                imageUrl = "https://example.com/image.jpg",
                source = "Source",
                publishedAt = "2021-09-01T12:00:00Z",
                author = "Author",
                url = "https://example.com"
            ),
            onClickBack = {},
            onClickShare = {},
            onClickFavorite = {}
        )
    }
}