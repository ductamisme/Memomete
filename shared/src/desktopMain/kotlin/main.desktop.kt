import androidx.compose.runtime.Composable
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
    MainComposeView(viewModel = viewModel)
}