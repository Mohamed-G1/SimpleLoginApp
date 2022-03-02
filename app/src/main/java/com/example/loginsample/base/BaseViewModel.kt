package com.example.loginsample.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginsample.auth.ui.login.AuthViewModel
import com.example.loginsample.repositories.AuthRepository

@Suppress("UNCHECKED_CAST")
class BaseViewModel(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            // here define all view models
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}