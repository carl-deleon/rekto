package com.scccrt.rekto.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface UiEvent

interface UiState

interface UiSideEffect

const val SIDE_EFFECTS_KEY = "side-effects-key"

abstract class BaseViewModel<Event : UiEvent, ViewState : UiState, Effect : UiSideEffect> : ViewModel() {

    abstract fun setInitialState(): ViewState
    abstract fun handleEvents(event: Event)

    private val initialState: ViewState by lazy { setInitialState() }

    private val _uiState: MutableState<ViewState> = mutableStateOf(initialState)
    val uiState: State<ViewState> = _uiState

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _uiEffect: Channel<Effect> = Channel()
    val effect = _uiEffect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _uiEvent.collect {
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected fun setState(reducer: ViewState.() -> ViewState) {
        val newState = _uiState.value.reducer()
        _uiState.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _uiEffect.send(effectValue) }
    }
}