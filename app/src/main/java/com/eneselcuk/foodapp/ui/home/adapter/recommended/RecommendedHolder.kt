package com.eneselcuk.foodapp.ui.home.adapter.recommended

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.remote.model.random.Meal
import com.eneselcuk.foodapp.databinding.ItemRecommendedBinding


class RecommendedHolder(private val binding: ItemRecommendedBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(foodRandom: Meal, onClick: RecommendedAdapter.OnClick) {
        binding.viewModelCategories = foodRandom
        binding.click = onClick
    }
}