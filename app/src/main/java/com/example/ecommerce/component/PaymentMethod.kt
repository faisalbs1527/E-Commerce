package com.example.ecommerce.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerce.R

@Composable
fun PaymentMethod() {
    val CheckPayment = remember {
        mutableStateOf(true)
    }
    val CardPayment = remember {
        mutableStateOf(false)
    }
    Column {
        OutlinedCard(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Row (Modifier.padding(start = 12.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                CustomCheckBox(
                    isChecked = CheckPayment.value,
                    radius = 16,
                    height = 28,
                    width = 28,
                    onCheckChange = {
                        CheckPayment.value = true
                        CardPayment.value = false
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_check),
                    contentDescription = "Check",
                    modifier = Modifier
                        .size(width = 45.dp, height = 28.dp)
                )
                Text(text = "Check / Money Order", modifier = Modifier.padding(start = 8.dp))
            }
        }
        OutlinedCard(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Row (Modifier.padding(start = 12.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                CustomCheckBox(
                    isChecked = CardPayment.value,
                    radius = 16,
                    height = 28,
                    width = 28,
                    onCheckChange = {
                        CardPayment.value = true
                        CheckPayment.value = false
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_credit),
                    contentDescription = "credit",
                    modifier = Modifier
                        .size(width = 45.dp, height = 28.dp)
                )
                Text(text = "Credit Card", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PaymentMethodPreview() {
    Surface {
        PaymentMethod()
    }
}