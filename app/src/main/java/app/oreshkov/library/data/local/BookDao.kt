package app.oreshkov.library.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookLocalEntity: BookLocalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bookList: List<BookLocalEntity>)

    @Query("SELECT * FROM books ORDER BY id")
    fun getBooks(): Flow<List<BookLocalEntity>>
}