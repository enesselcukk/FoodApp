package com.eneselcuk.foodapp.ui.search.search

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.databinding.ItemSearchBinding

class SearchHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(foodEntity: FoodEntity) {
        binding.foodFavorite = foodEntity
    }
}