package com.eneselcuk.foodapp.ui.country.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.Meal

object BaseDiffUtil : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(
        oldItem: Meal,
        newItem: Meal,
    ): Boolean {
        return oldItem == newItem
    }

}