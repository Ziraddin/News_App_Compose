package com.example.newsappcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.newsappcompose.R
import com.example.newsappcompose.data.model.NewsItem

@Composable
fun NewsItemCard(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onClick: () -> Unit,
    addBookmark: () -> Unit,
    removeBookmark: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = newsItem.urlToImage,
                contentDescription = "News Cover Image",
                modifier = Modifier
                    .weight(0.4f)
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                color = MaterialTheme.colorScheme.secondary,
                text = newsItem.title,
                maxLines = 1,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(horizontal = 12.dp),
            )

            Icon(
                tint = MaterialTheme.colorScheme.secondary,
                painter = painterResource(
                    if (newsItem.isBookmarked) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark
                ),
                contentDescription = "Bookmark Icon",
                modifier = Modifier
                    .weight(0.1f)
                    .testTag("bookmark_icon")
                    .size(24.dp)
                    .clickable {
                        if (newsItem.isBookmarked) {
                            removeBookmark()
                        } else {
                            addBookmark()
                        }
                    },
            )
        }
    }
}
