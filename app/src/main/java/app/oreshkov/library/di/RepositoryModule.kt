package app.oreshkov.library.di

import app.oreshkov.library.data.BookRepository
import app.oreshkov.library.data.BookRepositoryImpl
import app.oreshkov.library.data.local.BookDao
import app.oreshkov.library.data.local.BookLocalMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBookRepository(
        bookDao: BookDao,
        bookLocalMapper: BookLocalMapper
    ): BookRepository {
        return BookRepositoryImpl(bookDao, bookLocalMapper)
    }
}