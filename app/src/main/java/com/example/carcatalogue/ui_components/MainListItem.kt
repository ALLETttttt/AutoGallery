package com.example.carcatalogue.ui_components

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carcatalogue.MainViewModule
import com.example.carcatalogue.ui.theme.Gray
import com.example.carcatalogue.ui.theme.itembg
import com.example.carcatalogue.ui.theme.mycolor
import com.example.carcatalogue.utils.ListItem


@Composable
fun MainListItem(
    mainViewModule: MainViewModule = hiltViewModel(),
    item: ListItem, onClick: (ListItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(13.dp)
            .clickable {
                onClick(item)
            },
        shape = RoundedCornerShape(13.dp),
        border = BorderStroke(1.dp, mycolor)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (image, text, favourite) = createRefs()

            AssetImage(
                imageName = item.imageName,
                content = item.title,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(mycolor)
                    .padding(10.dp)
                    .constrainAs(text) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            IconButton(
                onClick = {
                          mainViewModule.insertItem(
                              item.copy(isFav = !item.isFav)
                          )
                },
                modifier = Modifier.constrainAs(favourite) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favourites",
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(itembg)
                        .padding(6.dp),
                    tint = if (item.isFav) Color.Red else Gray
                )
            }
        }
    }
}

@Composable
fun AssetImage(imageName: String, content: String, modifier: Modifier) {
//    val input = LocalContext.current.assets.open(imageName)
    val context = LocalContext.current
    val assetManager = context.assets
    val inputStream = assetManager.open(imageName)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = content,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}