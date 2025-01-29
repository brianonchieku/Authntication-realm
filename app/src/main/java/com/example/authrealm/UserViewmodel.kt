package com.example.authrealm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authrealm.models.UserModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewmodel: ViewModel() {

    val realm = MyApp.realm

    private val _signupStatus = MutableStateFlow<String?>(null)
    val signupStatus = _signupStatus.asStateFlow()

    private val _loginStatus = MutableStateFlow<String?>(null)
    val loginStatus = _loginStatus.asStateFlow()

    fun signup(userName: String, password: String){
        viewModelScope.launch {

            realm.write {
                val existingUser = query<UserModel>("userName == $0", userName).first().find()
                if (existingUser != null){
                    _signupStatus.value= "user exists"
                } else{
                    val newUser = UserModel().apply {
                        this.userName = userName
                        this.password = password
                    }

                    copyToRealm(newUser, updatePolicy = UpdatePolicy.ALL)
                    _signupStatus.value = "signup successful"
                }

            }

        }
    }

    fun login(userName: String, password: String){
        viewModelScope.launch {
            val user = realm.query<UserModel>("userName ==$0 AND password ==$1").first().find()
            if(user != null){
                _loginStatus.value = "login successful"
            } else{
                _loginStatus.value = "invalid username or password"
            }
        }
    }
}