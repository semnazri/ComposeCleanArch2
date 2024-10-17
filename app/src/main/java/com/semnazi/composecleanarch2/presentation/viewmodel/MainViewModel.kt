package com.semnazi.composecleanarch2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semnazi.composecleanarch2.domain.model.User
import com.semnazi.composecleanarch2.domain.usecase.GetUserUseCase
import com.semnazi.composecleanarch2.presentation.intent.MainIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> get() = _state

    fun onIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.LoadUsers -> loadUsers()
            }
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            getUserUseCase.invoke()
                .onStart {
                    _state.value = MainState(isLoading = true)
                }
                .catch { exception ->
                    _state.value = MainState(error = exception.localizedMessage)
                }
                .collect { users ->
                    _state.value = MainState(users = users)
                }
        }
    }
}


data class MainState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)