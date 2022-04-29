package com.nnt.core.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nnt.core.R
import com.nnt.jetpackcomposewithcleanarch.ui.theme.PrimaryColor

@Composable
fun Toolbar(title: String, navigateUp: ()->Unit, background: Color = PrimaryColor){
    Row(Modifier.fillMaxWidth()
        .background(background)) {
        Image(painterResource(id = R.drawable.ic_back), contentDescription = "navigateUp icon",
            modifier = Modifier.padding(16.dp, 0.dp, 12.dp, 16.dp)
                .clickable { navigateUp.invoke() })
        Text(text = title, color = Color.White)
    }
}