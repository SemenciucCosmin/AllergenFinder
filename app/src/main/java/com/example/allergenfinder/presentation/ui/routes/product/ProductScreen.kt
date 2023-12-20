package com.example.allergenfinder.presentation.ui.routes.product

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.allergenfinder.R
import com.example.allergenfinder.model.Product

@Composable
fun ProductScreen(product: Product) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.ic_no_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
        )

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        Text(
            text = "text",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
    }
}
