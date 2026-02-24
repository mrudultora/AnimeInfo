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
import com.mrudultora.animeinfo.viewmodels.AnimeDetailViewModel

@Composable
fun AnimeDetailScreen(viewModel: AnimeDetailViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier,
            text = "Anime Id: ${viewModel.state.value.animeId}",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
        )
    }
}