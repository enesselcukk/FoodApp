package com.eneselcuk.foodapp.ui.home.adapter.country

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.foodapp.data.remote.model.categories.Category
import com.eneselcuk.foodapp.data.remote.model.countryList.CountryList
import com.eneselcuk.foodapp.data.remote.model.countryList.Meal

object BaseDiffUtilCountry : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.strArea == newItem.strArea
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }

}