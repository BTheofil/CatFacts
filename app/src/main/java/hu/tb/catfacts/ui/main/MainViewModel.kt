package hu.tb.catfacts.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tb.catfacts.domain.repository.FactRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val factRepository: FactRepository
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    data class State(
        val fact: String? = null,
        val isLoading: Boolean = false,
        val isErrorHappened: String? = null,
    )

    fun getFact() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(
                    isLoading = true
                )
                val success = factRepository.getFact()
                if(success != null){
                    delay(5500)
                    _state.value = state.value.copy(
                        fact = success.fact,
                        isLoading = false
                    )
                } else {
                    _state.value = state.value.copy(
                        isErrorHappened = "Empty response found",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    isErrorHappened = e.message,
                    isLoading = false
                )
            }
        }
    }
}