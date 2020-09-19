package app.oreshkov.library.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import app.oreshkov.library.data.BookRepository
import app.oreshkov.library.model.Book
import app.oreshkov.library.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val bookRepository: BookRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Book>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Book>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBooksEvent -> {
                    bookRepository.getBooks()
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}