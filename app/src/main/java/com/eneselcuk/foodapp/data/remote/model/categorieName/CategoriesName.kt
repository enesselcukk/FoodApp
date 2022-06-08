package com.eneselcuk.foodapp.data.remote.model.categorieName

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesName(
    val meals: List<Meal>? = null,
) : Parcelable