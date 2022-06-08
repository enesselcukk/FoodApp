package com.eneselcuk.foodapp.data.remote.model.categorieName

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strMealThumb: String? = null,
) : Parcelable