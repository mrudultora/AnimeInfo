package com.mrudultora.animeinfo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mrudultora.animeinfo.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = viewModel::onButtonClick
        ) {
            Text(
                modifier = Modifier,
                text = "Go to Anime Detail",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        }
    }
}