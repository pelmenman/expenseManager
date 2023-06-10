package com.example.expensemanager.di

import android.content.Context
import androidx.room.Room
import com.example.expensemanager.database.FinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(app, FinanceDatabase::class.java, "finance").build()

//    @Provides
//    @Singleton
//    fun provideViewModel(fragment: Fragment) : ViewModel {
//        val viewModel: ViewModel by fragment.viewModels()
//
//        return viewModel
//    }

    @Provides
    @Singleton
    fun provideDao(db : FinanceDatabase) = db.getDao()
}