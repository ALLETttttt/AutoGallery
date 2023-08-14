package com.example.carcatalogue.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.carcatalogue.R
import com.example.carcatalogue.ui.theme.mycolor
import com.example.carcatalogue.utils.DrawerEvents


@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(0.85f)) {
        Image(
            painter = painterResource(id = R.drawable.menu_bg),
            contentDescription = "menu_bg",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Header()
            Body() { event ->
                onEvent(event)
            }
        }
    }
}

@Composable
fun Header() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(13.dp),
        shape = RoundedCornerShape(13.dp),
        border = BorderStroke(1.dp, mycolor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.fon_bg),
                contentDescription = "fon",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Car Catalogue",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(mycolor)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun Body(onEvent: (DrawerEvents) -> Unit) {
    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { index, title ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, end = 13.dp, top = 5.dp, bottom = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = mycolor
                ),
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(DrawerEvents.OnItemClick(title, index))
                        }
                        .padding(10.dp)
                        .wrapContentWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}