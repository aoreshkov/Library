package app.oreshkov.library.data

import app.oreshkov.library.data.BookRepository
import app.oreshkov.library.model.Book
import app.oreshkov.library.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeBookRepository : BookRepository {
    override fun getBooks(): Flow<DataState.Success<List<Book>>> {
        return flowOf(DataState.Success(listOf()))
    }

}