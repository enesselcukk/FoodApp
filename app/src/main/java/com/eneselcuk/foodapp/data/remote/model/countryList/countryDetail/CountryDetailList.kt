package com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDetailList(
    val meals: List<Meal?>? = null,
) : Parcelable