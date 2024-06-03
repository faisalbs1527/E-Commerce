package com.example.ecommerce.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun Title(text: String) {
    Text(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}