package app.oreshkov.library.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import app.oreshkov.library.data.local.BookDao
import app.oreshkov.library.data.local.LibraryDatabase
import app.oreshkov.library.worker.SeedDatabaseWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LocalDataModule {

    @Singleton
    @Provides
    fun provideLibraryDatabase(@ApplicationContext context: Context): LibraryDatabase =
        Room.databaseBuilder(context, LibraryDatabase::class.java, LibraryDatabase.DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                }
            })
            .build()

    @Singleton
    @Provides
    fun provideBookDAO(libraryDatabase: LibraryDatabase): BookDao {
        return libraryDatabase.bookDao()
    }
}