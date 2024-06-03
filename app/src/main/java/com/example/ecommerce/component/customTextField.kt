package com.example.ecommerce.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.R

@Composable
fun TextFieldCustom(
    label: String,
    value: String,
    isTralingIcon : Boolean,
    onValueChange : (String) -> Unit
){
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text= label,
                fontSize = 12.sp,
                color = Color(0xFF7D828B)
            ) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFCDD1D4),
            unfocusedIndicatorColor  = Color(0xFFCDD1D4)
        ),
        trailingIcon = {
            if(isTralingIcon){
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_dropdown), contentDescription = null)
            }
            else null
        }
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    Surface {
        TextFieldCustom(
            value = "",
            onValueChange = {},
            isTralingIcon = true,
            label = "Address"
        )
    }
}