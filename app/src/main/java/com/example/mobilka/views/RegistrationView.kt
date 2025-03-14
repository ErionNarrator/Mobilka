package com.example.mobilka.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilka.R
import com.example.mobilka.data.PreferenceManager
import kotlinx.coroutines.launch


@Composable
fun RegistrationView(navController: NavController){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val preferencesMigration = remember { PreferenceManager(context) }
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Регистрация",
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Заполните Свои Данные Или Продолжите через Социальные Медиа",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Column {
                    Text("Ваше имя", fontSize = 17.sp)
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text("xxxxxxx", color = Color(0x41000000)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFF7F7F9)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
                Column {
                    Text("Email", fontSize = 17.sp)
                    OutlinedTextField(
                        value = email,
                        onValueChange = {email = it},
                        placeholder = { Text("xyz@gmail.com", color = Color(0x41000000))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFF7F7F9)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Column {
                    Text("Пароль", fontSize = 17.sp)
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("•••••••••••", color = Color(0x41000000)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val imageRes =
                                if (passwordVisible) R.drawable.eye_open else R.drawable.eye_close

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = null
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            unfocusedContainerColor = Color(0xFFF7F7F9)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    CircleCheckbox(checked = isChecked, onCheckedChange = { isChecked = it })
                    TextButton(onClick = {}) {
                        Text(
                            "Даю согласие на обработку \nперсональных данных",
                            textDecoration = TextDecoration.Underline,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = {
                        scope.launch {
                            preferencesMigration.setRegistered(true)
                            navController.navigate("sladerPage"){
                                popUpTo("register") {inclusive = true}
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF48b2e7)
                    ),
                    shape = RoundedCornerShape(12.dp)

                ) {
                    Text("Войти", fontSize = 18.sp)
                }

            }
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text("Есть акканут? А если найду?!", fontSize = 17.sp)
                    TextButton(onClick = {
                        scope.launch {
                            navController.navigate("login")
                        }
                    }) { Text("Войти", fontSize = 17.sp) }
                }
            }
        }
    }
}

@Composable
fun CircleCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Box(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ){
        Box (
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .border(3.dp, if (checked) Color.Blue else Color.Gray, CircleShape)
                .background(if (checked) Color.Blue else Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ){
            if (checked){
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }
    }
}