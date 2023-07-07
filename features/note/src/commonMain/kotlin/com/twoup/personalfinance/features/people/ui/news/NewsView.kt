package com.twoup.personalfinance.features.people.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewsView() {
    Scaffold(

    ) {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.fillMaxHeight().verticalScroll(scrollState)) {


            Text("OKEla 4321")
            Divider()

            Text("KMP amz framework")


            Divider()

        }
    }
}

