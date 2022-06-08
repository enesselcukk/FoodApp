package com.eneselcuk.foodapp.data.remote.model.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val idCategory: String? = null,
    val strCategory: String? = null,
    val strCategoryDescription: String? = null,
    val strCategoryThumb: String? = null,
) : Parcelable