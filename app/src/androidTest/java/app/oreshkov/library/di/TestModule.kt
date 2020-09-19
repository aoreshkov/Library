package app.oreshkov.library.di

import app.oreshkov.library.data.FakeBookDao
import app.oreshkov.library.data.FakeBookRepository
import app.oreshkov.library.data.BookRepository
import app.oreshkov.library.data.local.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestModule {

    @Singleton
    @Provides
    fun provideBookDao(): BookDao {
        return FakeBookDao()
    }

    @Singleton
    @Provides
    fun provideBookRepository(): BookRepository {
        return FakeBookRepository()
    }
}