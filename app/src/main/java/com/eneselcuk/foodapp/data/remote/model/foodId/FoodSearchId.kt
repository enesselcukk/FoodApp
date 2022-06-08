package com.eneselcuk.foodapp.data.remote.model.foodId

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodSearchId(
    val meals: List<Meal>
):Parcelable