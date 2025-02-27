package com.example.mobilka

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mobilka.data.PreferenceManager
import com.example.mobilka.navigation.AppNavigation
import com.example.mobilka.ui.theme.MobilkaTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobilkaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainApp(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val preferencesMigration = remember { PreferenceManager(context) }

    val isRegisterdStatic = preferencesMigration.isRegistered.collectAsState(initial = null)
    when (val isRegistered = isRegisterdStatic.value){
        null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        else ->{
            val startDestination = if (isRegistered) "login" else
                "mainPage"
            //"registration"
            AppNavigation(navController,startDestination)
        }
    }
}