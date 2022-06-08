package com.eneselcuk.foodapp.ui.home.adapter.recommended

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.foodapp.data.remote.model.random.Meal

object BaseDiffUtil : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem
}