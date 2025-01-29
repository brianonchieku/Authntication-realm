package com.example.authrealm

sealed class NetworkResponse {
    data class Success(val message: String): NetworkResponse()
    data class Error(val message: String): NetworkResponse()
    object Loading: NetworkResponse()
}