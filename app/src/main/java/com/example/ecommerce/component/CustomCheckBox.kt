package com.example.ecommerce.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Composable
fun CustomCheckBox(
    isChecked : Boolean,
    onCheckChange : (Boolean) -> Unit = {}) {
    Box(
        Modifier
            .size(width = 20.dp, height = 20.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD5D5DA),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onCheckChange(!isChecked)
            }
    ) {
        if(isChecked){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ok),
                contentDescription = "Box Icon" ,
                Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun CustomCheckBoxPreview() {
    Surface {
        CustomCheckBox(
            isChecked = true,
            onCheckChange = {}
        )
    }
}