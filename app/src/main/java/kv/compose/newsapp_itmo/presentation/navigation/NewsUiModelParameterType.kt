package kv.compose.newsapp.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kv.compose.newsapp.presentation.model.NewsUiModel

object NewsUiModelParameterType : NavType<NewsUiModel>(
    isNullableAllowed = false
) {
    override fun put(
        bundle: Bundle,
        key: String,
        value: NewsUiModel
    ) {
        bundle.putParcelable(key, value)
    }

    override fun get(
        bundle: Bundle,
        key: String
    ): NewsUiModel? = bundle.getParcelable(key)

    override fun serializeAsValue(value: NewsUiModel): String {
        // Serialized values must always be Uri encoded
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): NewsUiModel {
        // Navigation takes care of decoding the string
        // before passing it to parseValue()
        return Json.decodeFromString<NewsUiModel>(value)
    }
}