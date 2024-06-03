package com.example.ecommerce.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoxCustom(
    text: String
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(brush = gradientColor(), shape = RoundedCornerShape(4.dp))
    )
    {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun gradientColor() : Brush {
    return Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF0BF7EB),
            Color(0xFF07C5FB),
            Color(0xFF088DF9)
        )
    )
}

@Preview
@Composable
private fun BoxCustomPreview() {
    Surface {
        BoxCustom(text = "Title")
    }
}