package com.example.carcatalogue.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.carcatalogue.utils.ListItem


@Composable
fun InfoScreen(item: ListItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AssetImage(
                imageName = item.imageName,
                content = item.title,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}