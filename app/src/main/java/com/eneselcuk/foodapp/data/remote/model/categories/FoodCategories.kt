package com.eneselcuk.foodapp.data.remote.model.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodCategories(
    val categories: List<Category?>? = null,
) : Parcelable