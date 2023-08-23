package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

val screenState = mutableStateOf<Screen>(Screen.Menu)

sealed interface Screen {
    object Menu : Screen
    object Calculator : Screen
    object Countdown : Screen
    object Countdown2 : Screen
    object GuessTheNumber : Screen
    object StateExercise2 : Screen
    object StoneScissorsPaper : Screen
    object TrafficLight : Screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (screenState.value) {
                        Screen.Menu -> Menu()
                        Screen.Calculator -> Calculator()
                        Screen.Countdown -> Countdown()
                        Screen.Countdown2 -> Countdown2()
                        Screen.GuessTheNumber -> GuessTheNumber()
                        Screen.StateExercise2 -> StateExercise2UI()
                        Screen.StoneScissorsPaper -> StoneScissorsPaper()
                        Screen.TrafficLight -> TrafficLight()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePlaygroundTheme {
        Greeting("Android")
    }
}