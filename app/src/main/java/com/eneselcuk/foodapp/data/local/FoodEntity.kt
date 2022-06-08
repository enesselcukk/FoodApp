package com.eneselcuk.foodapp.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "foodEntity")
@Parcelize
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "food_image")
    val image: String?,

    @ColumnInfo(name = "food_name")
    val food_name: String?,

    @ColumnInfo(name = "liked")
    var liked: Boolean?,

    @ColumnInfo(name = "youtube")
    var youtube: String?,

    @ColumnInfo(name = "Tags")
    var tags: String?,

    @ColumnInfo(name = "details")
    var details: String?,


) : Parcelable
