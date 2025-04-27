package com.example.newsappcompose.ui.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsappcompose.R

sealed class Screen(
    val route: String,
    @StringRes val label: Int? = null,
    @DrawableRes val icon: Int? = null
) {
    data object Search : Screen(
        route = "search",
        label = R.string.search,
        icon = R.drawable.ic_search
    )

    data object Bookmark : Screen(
        route = "bookmark",
        label = R.string.bookmark,
        icon = R.drawable.ic_bookmark
    )

    data object ArticleDetail : Screen(
        route = "article_detail"
    )
}
