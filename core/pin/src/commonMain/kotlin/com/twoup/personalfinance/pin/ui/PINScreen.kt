package com.twoup.personalfinance.pin.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twoup.personalfinance.resources.MR
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.desc


@Composable
fun PINScreen(onPinEntered: (String) -> Unit) {
//    CommonResources.titleSetUpPin()
//    CommonResources.getString(MR.strings.description_confirmPin)
    BasePinScreen(
        MR.strings.title_setUpPin.desc().localized(),
        MR.strings.description_setUpPin.desc().localized(),
        MR.strings.tip_setUpPin.desc().localized(),
        onPinEntered
    )

//    BasePinScreen("Type your PIN again",
//        "There will be NO 'Restore PIN' button. Make sure you remember it",
//        "Since you're going to be your own bank, we won't be able to help if you lose your PIN",
//        onPinEntered
//    )
}

@Composable
fun BasePinScreen(title: String, description : String, tip: String, onPinEntered: (String) -> Unit) {
    val pinState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Gray
        )

        val spaceTop = 102.dp

        val marginKeyFillOut = 16.dp

        Spacer(modifier = Modifier.height(spaceTop))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 1..6) {
                Oval(
                    fill = if (i <= pinState.value.length) Color.Black else Color.Transparent,
                    stroke = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(marginKeyFillOut))
            }
        }
        Spacer(modifier = Modifier.height(spaceTop))
        Text(
            text = tip,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        val marginHorizontalKeyPad = 16.dp
        val marginVerticalKeyPad = 2.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            Row(
                modifier = Modifier.padding(bottom = marginVerticalKeyPad),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NumberButton(number = "1", onClick = { pinState.value += "1" })
                NumberButton(number = "2", onClick = { pinState.value += "2" }, modifier = Modifier.padding(horizontal = marginHorizontalKeyPad))
                NumberButton(number = "3", onClick = { pinState.value += "3" })
            }
            Row(
                modifier = Modifier.padding(bottom = marginVerticalKeyPad),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NumberButton(number = "4", onClick = { pinState.value += "4" })
                NumberButton(number = "5", onClick = { pinState.value += "5" }, modifier = Modifier.padding(horizontal = marginHorizontalKeyPad))
                NumberButton(number = "6", onClick = { pinState.value += "6" })
            }
            Row(
                modifier = Modifier.padding(bottom = marginVerticalKeyPad),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NumberButton(number = "7", onClick = { pinState.value += "7" })
                NumberButton(number = "8", onClick = { pinState.value += "8" }, modifier = Modifier.padding(horizontal = marginHorizontalKeyPad))
                NumberButton(number = "9", onClick = { pinState.value += "9" })
            }
            Row(
                modifier = Modifier.padding(bottom = marginVerticalKeyPad),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.size(64.dp))
                NumberButton(number = "0", onClick = { pinState.value += "0" }, modifier = Modifier.padding(horizontal = marginHorizontalKeyPad))
                IconButton(
                    onClick = { if (pinState.value.isNotEmpty()) pinState.value = pinState.value.dropLast(1) },
                    modifier = Modifier.size(64.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
    }
    if (pinState.value.length == 6) {
        onPinEntered(pinState.value)
    }
}

@Composable
fun Oval(
    fill: Color,
    stroke: BorderStroke,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = fill, shape = CircleShape)
            .border(stroke, shape = CircleShape)
    )
}

@Composable
fun NumberButton(number: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(72.dp)
            .padding(2.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(text = number, fontSize = 24.sp)
    }
}

