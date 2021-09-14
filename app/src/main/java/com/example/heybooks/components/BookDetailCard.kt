package com.example.heybooks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.heybooks.ui.theme.text
import com.example.heybooks.ui.theme.typography
import com.google.accompanist.flowlayout.FlowRow


@ExperimentalCoilApi
@Composable
fun BookDetailCard() {
    Box(
        Modifier
            .wrapContentSize()
            .padding(start = 20.dp, end = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colors.onSurface),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //Image
            Image(
                painter = rememberImagePainter(
                    data = "https://s3.amazonaws.com/AKIAJC5RLADLUMVRPFDQ.book-thumb-images/ableson.jpg"
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(240.dp, 140.dp)
                    .padding(12.dp)
                    .offset(
                        y = (-30).dp
                    )
            )
            //Content
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "By Spikey Sanju",
                    style = typography.caption,
                    textAlign = TextAlign.Center,
                    color = text.copy(0.7F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "The More of Less",
                    style = typography.subtitle1,
                    textAlign = TextAlign.Center,
                    color = text
                )
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow{
//                    categories.forEach{
//                        ChipView(category = it)
//                    }
                }
            }
        }

    }
}