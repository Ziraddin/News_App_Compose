package com.example.newsappcompose.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsappcompose.R

sealed class Screen(
    val route: String,
    @StringRes val label: Int? = null,
    @DrawableRes val icon: Int? = null,
    val testTag: String? = null
) {
    data object Search : Screen(
        route = "search",
        label = R.string.search,
        icon = R.drawable.ic_search,
        testTag = "search_bottom_icon"
    )

    data object Bookmark : Screen(
        route = "bookmark",
        label = R.string.bookmark,
        icon = R.drawable.ic_bookmark,
        testTag = "bookmark_bottom_icon"
    )

    data object ArticleDetail : Screen(
        route = "article_detail",
    )
}
