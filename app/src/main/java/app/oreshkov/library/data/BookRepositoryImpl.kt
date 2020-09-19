package app.oreshkov.library.data

import app.oreshkov.library.data.local.BookDao
import app.oreshkov.library.data.local.BookLocalMapper
import app.oreshkov.library.util.DataState
import kotlinx.coroutines.flow.map

class BookRepositoryImpl
constructor(
    private val bookDao: BookDao,
    private val bookLocalMapper: BookLocalMapper
) : BookRepository {
    override fun getBooks() = bookDao
        .getBooks()
        .map { DataState.Success(bookLocalMapper.mapFromEntityList(it)) }
}