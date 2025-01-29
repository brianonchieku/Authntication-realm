package com.example.authrealm.view

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authrealm.NetworkResponse
import com.example.authrealm.R
import com.example.authrealm.UserViewmodel

@Composable
fun SignupPage(viewmodel: UserViewmodel){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.lavender3), contentDescription =null,
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmpassword by remember { mutableStateOf("") }
            val signupStatus by viewmodel.signupStatus.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(signupStatus) {
                when (signupStatus) {
                    is NetworkResponse.Error -> {
                        Toast.makeText(context, (signupStatus as NetworkResponse.Error).message, Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResponse.Success -> {
                        Toast.makeText(context, (signupStatus as NetworkResponse.Success).message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {} // No toast for loading state
                }
            }
            Card(modifier = Modifier
                .wrapContentSize()
                .verticalScroll(rememberScrollState())
                .padding(30.dp)) {
                Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Register", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value =username , onValueChange ={username=it},
                        label = { Text(text = "Username") },
                        placeholder = { Text(text = "Enter Username") },
                        leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_person_24), contentDescription =null )
                        }, shape = RoundedCornerShape(20.dp)
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value =password , onValueChange ={password=it},
                        label = { Text(text = "password") },
                        placeholder = { Text(text = "Enter password") },
                        leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, shape = RoundedCornerShape(20.dp),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(value =confirmpassword , onValueChange ={confirmpassword=it},
                        label = { Text(text = "confirm password") },
                        placeholder = { Text(text = "confirm password") },
                        leadingIcon = {
                            Icon(painterResource(id = R.drawable.baseline_lock_24), contentDescription =null )
                        }, shape = RoundedCornerShape(20.dp),
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.size(20.dp))

                    Button(onClick ={
                        viewmodel.signup(username, password)
                    }, modifier = Modifier.width(250.dp), ) {
                        when(signupStatus){

                            is NetworkResponse.Loading -> {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                            }
                            else -> {
                                Text(text = "Register", fontWeight = FontWeight.Bold)
                            }

                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Already have an account?", fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {})



                }



            }

        }


    }

}