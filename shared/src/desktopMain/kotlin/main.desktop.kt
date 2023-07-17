import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.twoup.personalfinance.ui.MainComposeView
import com.twoup.personalfinance.viewmodel.ApplicationViewModel

//import com.twoup.personalfinance.Greeting

//@Composable
//fun MainView(){
////    val greeting = Greeting().greet()
//    Text("Hola ooo")
//}

@Composable
fun MainView(viewModel: ApplicationViewModel) {
    MainComposeView(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = true,
        modifier = Modifier,
        viewModel = viewModel
    )
}