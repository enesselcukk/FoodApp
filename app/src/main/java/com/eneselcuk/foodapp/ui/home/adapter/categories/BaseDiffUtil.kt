package com.eneselcuk.foodapp.ui.home.adapter.categories

import androidx.recyclerview.widget.DiffUtil
import com.eneselcuk.foodapp.data.remote.model.categories.Category

object BaseDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.idCategory == newItem.idCategory

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem
}