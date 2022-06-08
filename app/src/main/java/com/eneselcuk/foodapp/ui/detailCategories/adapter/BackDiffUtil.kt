package com.eneselcuk.foodapp.ui.detailCategories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.foodapp.data.remote.model.categorieName.Meal

object BackDiffUtil : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem
}