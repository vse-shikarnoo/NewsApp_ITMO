package kv.compose.newsapp.presentation.newslist

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kv.compose.newsapp.presentation.model.NewsUiModel

data class NewsListUiState(
    val news: Flow<PagingData<NewsUiModel>>? = null,
    val showCountryDialog: Boolean = false,
    val newsCategories: List<String> = categories,
    val newsCountries: List<String> = countries,
    val selectedCountry: String? = newsCountries.first(),
    val selectedCategory: String? = newsCategories.first(),
)

val categories =
    listOf(
        "All News",
        "Business",
        "Entertainment",
        "General",
        "Health",
        "Science",
        "Sports",
        "Technology"
    )

val countries =
    listOf(
        "United States",
        "United Kingdom",
        "Australia",
        "Canada",
        "Kenya",
        "India",
        "Germany",
        "France",
        "Italy",
        "Netherlands",
        "Norway",
        "Sweden",
        "China",
        "Japan",
        "South Korea",
        "Russia",
        "Brazil",
        "Argentina",
        "Mexico",
        "South Africa",
        "Nigeria",
        "Egypt",
        "Saudi Arabia",
        "United Arab Emirates",
        "Kuwait"
    )