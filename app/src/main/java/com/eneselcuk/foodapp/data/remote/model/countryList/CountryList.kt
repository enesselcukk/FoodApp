package com.eneselcuk.foodapp.data.remote.model.countryList

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryList(
    val meals: List<Meal>,
) : Parcelable