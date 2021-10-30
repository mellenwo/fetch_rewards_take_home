package com.example.fetch_rewards_take_home.ui.main

import androidx.lifecycle.*
import com.example.fetch_rewards_take_home.model.User
import com.example.fetch_rewards_take_home.repository.UserRepository
import com.example.fetch_rewards_take_home.util.DataState
import dagger.assisted.Assisted
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<User>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<User>>>
        get() = _dataState

    fun setStateEvent(stateEvent: HomeStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is HomeStateEvent.GetUserEvents -> {
                    repository.getUsers()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is HomeStateEvent.None -> {}
            }
        }
    }

}

sealed class HomeStateEvent {
    object GetUserEvents : HomeStateEvent()
    object None : HomeStateEvent()
}