package com.eneselcuk.foodapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)

abstract class FoodDataBase : RoomDatabase() {

    abstract fun moviesDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDataBase? = null

        fun getDatabase(context: Context): FoodDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDataBase::class.java, "movies_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}