package app.oreshkov.library.data

import app.oreshkov.library.data.local.BookDao
import app.oreshkov.library.data.local.BookLocalEntity
import kotlinx.coroutines.flow.Flow

class FakeBookDao : BookDao {
    override suspend fun insert(bookLocalEntity: BookLocalEntity): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(bookList: List<BookLocalEntity>) {
        TODO("Not yet implemented")
    }

    override fun getBooks(): Flow<List<BookLocalEntity>> {
        TODO("Not yet implemented")
    }

}