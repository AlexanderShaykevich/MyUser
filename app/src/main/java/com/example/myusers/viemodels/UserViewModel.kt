package com.example.myusers.viemodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myusers.data.RepositoryImpl
import com.example.myusers.data.State
import com.example.myusers.data.usermodel.ApiResponse
import com.example.myusers.data.usermodel.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: RepositoryImpl
) : ViewModel() {
    private val _data = MutableStateFlow<ApiResponse?>(null)
    val data = _data.asStateFlow()
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getUsers(random = false)
    }

    fun getUsers(random: Boolean) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isError = false, isLoading = true)
            try {
                _data.value = if (random) repository.getUsers(true)
                else repository.getUsers(false)
                _state.value = _state.value.copy(isLoading = false)
            } catch (_: Exception) {
                _state.value = _state.value.copy(isError = true, isLoading = false)
            }
        }
    }

    fun findUser(email: String): User? {
        return data.value?.results?.find { it.email == email }
    }


}

