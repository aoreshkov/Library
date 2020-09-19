package app.oreshkov.library.data

import app.oreshkov.library.model.Book
import app.oreshkov.library.util.DataState
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<DataState.Success<List<Book>>>
}