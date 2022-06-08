package com.eneselcuk.foodapp.ui.detailCategories.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.remote.model.categorieName.Meal
import com.eneselcuk.foodapp.databinding.ItemCategoiresDetailBinding

class CategoriesHolder(private val binding: ItemCategoiresDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(categories: Meal, categoriesOnClick: CategoriesDetailAdapter.DetailCategoriesClick) {
        binding.categoriesName = categories
        binding.click = categoriesOnClick

    }
}