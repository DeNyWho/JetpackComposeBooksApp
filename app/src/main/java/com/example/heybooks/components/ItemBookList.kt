package com.example.heybooks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.heybooks.ui.theme.primary
import com.example.heybooks.ui.theme.text
import com.example.heybooks.ui.theme.typography
import com.example.heybooks.utils.coloredShadow
import com.google.accompanist.flowlayout.FlowRow


@ExperimentalCoilApi
@Composable
fun ItemBookList(title: String, authors: String, thumbnailUrl: String, categories: List<String>, onItemClick: () -> Unit ) {
    Card(modifier = Modifier
        .clickable(onClick = onItemClick)
        .background(MaterialTheme.colors.onSurface)
        .coloredShadow(color = MaterialTheme.colors.primary, alpha = 0.020F, borderRadius = 0.dp, shadowRadius = 4.dp, offsetX = 0.dp,offsetY = 4.dp)
        .padding(16.dp))
    {
        //Row Image + Content
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Image(
                painter = rememberImagePainter(
                    data = thumbnailUrl
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            //Content
            Column {
                Text(text = "By ".plus(authors), style = typography.caption, color = text.copy(0.7F))
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = title, style = typography.subtitle1, color = text)
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow {
                    categories.forEach {
                        ChipView(category = it)
                    }
                }
            }
        }
    }
}

@Composable
fun ChipView(category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(primary.copy(.10F))
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, style = typography.caption, color = primary)
    }
}
