package com.eneselcuk.foodapp.ui.search.search

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.foodapp.data.local.FoodEntity

object BaseDiffUtil : DiffUtil.ItemCallback<FoodEntity>() {
    override fun areItemsTheSame(oldItem: FoodEntity, newItem: FoodEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FoodEntity, newItem: FoodEntity): Boolean =
        oldItem == newItem

}