import SwiftUI
import shared

struct ContentView: View {
// 	let greet = Greeting().greet()

	var body: some View {
		Text("HN vang con mua")
        ComposeController()
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
