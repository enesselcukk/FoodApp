package com.eneselcuk.foodapp.di.roomModule

import android.content.Context
import androidx.room.Room
import com.eneselcuk.foodapp.data.local.FoodDao
import com.eneselcuk.foodapp.data.local.FoodDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): FoodDataBase {
        return Room.databaseBuilder(context, FoodDataBase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db: FoodDataBase,
    ): FoodDao = db.moviesDao()

}