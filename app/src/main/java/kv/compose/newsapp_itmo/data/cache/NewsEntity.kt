package kv.compose.newsapp.data.cache

import androidx.room.Entity
import kv.compose.newsapp.data.utils.Constants.NEWS_TABLE_NAME
import kv.compose.newsapp.domain.model.News

@Entity(tableName = NEWS_TABLE_NAME, primaryKeys = ["title"])
data class NewsEntity(
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String,
    val source: String,
    val publishedAt: String,
    val author: String,
    val url: String
) {
    companion object {
        fun News.toEntity(): NewsEntity = NewsEntity(
            title = title,
            description = description,
            content = content,
            imageUrl = imageUrl,
            source = source,
            publishedAt = publishedAt,
            author = author,
            url = url
        )

        fun NewsEntity.toNews(): News = News(
            title = title,
            description = description,
            content = content,
            imageUrl = imageUrl,
            source = source,
            publishedAt = publishedAt,
            author = author,
            url = url
        )
    }
}