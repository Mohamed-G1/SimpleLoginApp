package com.example.loginsample.auth.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsample.data.network.Resource
import com.example.loginsample.data.repositories.AuthRepository
import com.example.loginsample.data.response.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            _loginResponse.value = repository.login(userName, password)
        }
    }

    fun saveToken(token: String) = viewModelScope.launch {
        repository.saveToken(token)
    }
}