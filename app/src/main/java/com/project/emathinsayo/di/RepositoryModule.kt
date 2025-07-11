package com.project.emathinsayo.di

import com.project.emathinsayo.common.DataStorageHelper
import com.project.emathinsayo.data.AppDatabase
import com.project.emathinsayo.data.BookRepository
import com.project.emathinsayo.data.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(
        dataStorageHelper: DataStorageHelper,
        database: AppDatabase,
    ): BookRepository {
        return BookRepositoryImpl(dataStorageHelper, database)
    }
}