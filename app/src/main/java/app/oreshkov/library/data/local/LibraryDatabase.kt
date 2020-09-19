package app.oreshkov.library.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookLocalEntity::class ], version = 1)
abstract class LibraryDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object{
        const val DATABASE_NAME: String = "library-db"
    }
}