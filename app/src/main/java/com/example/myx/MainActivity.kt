package com.example.myx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myx.ui.theme.MyxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "registration"){  // start destination should be mentioned based on below mentioned nodes
        composable(route="registration"){    //defining nodes for particular screens
            RegistrationScreen {
                navController.navigate("main/${it}") // simply it shows what to do when button is clicked and it represents args
            }
        }
        composable(route="login"){
            LoginScreen()
        }
        composable(route="main/{email}", arguments = listOf(
            navArgument(name="email") {
                type = NavType.StringType
            }
        )){
            val email = it.arguments!!.getString("email")
            MainScreen(email!!)
        }

    }
}

@Composable
fun RegistrationScreen(onClick:(email:String)->Unit){  // onClick is a function which return unit type that simply means void
    Text(text = "Registration Screen",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.clickable {
           // navController.navigate("main") //method 1
            onClick("aryangangwar00@gmail.com")
        }

    )
}

@Composable
fun LoginScreen(){
    Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun MainScreen(email:String){
    Text(text = "Main Screen /n Mail is $email ", style = MaterialTheme.typography.headlineMedium)
}


