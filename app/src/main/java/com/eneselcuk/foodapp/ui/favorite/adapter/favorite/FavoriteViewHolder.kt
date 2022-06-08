package com.eneselcuk.foodapp.ui.favorite.adapter.favorite

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.databinding.ItemFavoriteBinding

class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(foodEntity: FoodEntity, foodClick: FavoriteAdapter.FoodOnClick) {

        binding.apply {
            foodFavorite = foodEntity
            onClick = foodClick
        }
    }
}