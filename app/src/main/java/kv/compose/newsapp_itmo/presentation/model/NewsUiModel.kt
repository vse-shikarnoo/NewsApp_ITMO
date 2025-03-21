package kv.compose.newsapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kv.compose.newsapp.domain.model.News

@Serializable
@Parcelize
data class NewsUiModel(
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String,
    val source: String,
    val publishedAt: String,
    val author: String,
    val url: String
) : Parcelable {
    companion object {
        fun News.toUiModel(): NewsUiModel = NewsUiModel(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source,
            title = title,
            url = url,
            imageUrl = imageUrl
        )

        fun NewsUiModel.toNews(): News = News(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source,
            title = title,
            url = url,
            imageUrl = imageUrl
        )
    }
}