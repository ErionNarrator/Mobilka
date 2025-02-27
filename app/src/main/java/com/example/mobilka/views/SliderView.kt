package com.example.mobilka.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilka.R
import com.example.mobilka.data.PreferenceManager
import kotlinx.coroutines.launch


@Composable
fun SladerView(navController: NavController){

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF42A5F5))
            .padding(16.dp),
        contentAlignment = Alignment.Center

    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Добро",
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    color = Color.White
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = "пожаловать",
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    color = Color.White

                )
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),

                )

            }
            Spacer(Modifier.height(5.dp ))
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    painter = painterResource(id = R.drawable.onboard1),
                    contentDescription = null,
                    modifier = Modifier.size(500.dp)
                )

                Button(
                    onClick = {
                        scope.launch {
                            preferenceManager.setRegistered(true)
                            navController.navigate("mainPage"){
                                popUpTo("sladerPage") { inclusive = true }

                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)

                ) {
                    Text("Далее", fontSize = 18.sp, color = Color.Black)
                }
            }




        }
    }
}