package com.eneselcuk.foodapp.ui.home.adapter.categories

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.remote.model.categories.Category
import com.eneselcuk.foodapp.databinding.ViewCategoriesBinding

class MyViewHolder(private val binding: ViewCategoriesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        foodCategories: Category,
        onClick: CategoriesAdapter.Click,

        ) {
        binding.apply {
            viewModelCategories = foodCategories
            click = onClick
//            viewModelCategories = foodCategories
        }
    }
}