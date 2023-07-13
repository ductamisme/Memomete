package com.twoup.personalfinance.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import com.twoup.personalfinance.Greeting
import com.twoup.personalfinance.ui.MainView
import com.twoup.personalfinance.viewmodel.ApplicationViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    private val applicationViewModel: ApplicationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView(viewModel = applicationViewModel)
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}
