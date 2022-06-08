package com.eneselcuk.foodapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface FoodDao {

    @Query("SELECT * FROM foodEntity")
    suspend fun getAllFood(): List<FoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodFavorite(moviesEntity: FoodEntity)

    @Query("DELETE FROM foodEntity WHERE id = :key")
    suspend fun deleteIdFood(key: Int?)

    @Query("SELECT * FROM foodEntity WHERE food_name LIKE :name")
    fun searchFood(name: String?): LiveData<List<FoodEntity>>
}
