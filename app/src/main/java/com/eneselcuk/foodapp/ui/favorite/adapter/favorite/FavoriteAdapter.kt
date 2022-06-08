package com.eneselcuk.foodapp.ui.favorite.adapter.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.local.FoodEntity


class FavoriteAdapter(private val click: FoodOnClick) :
    ListAdapter<FoodEntity, FavoriteViewHolder>(BaseDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_favorite,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoritePosition = getItem(position)
        holder.bind(favoritePosition, click)
    }

    class FoodOnClick(val click: (food: FoodEntity) -> Unit) {
        fun foodClick(food: FoodEntity) = click(food)
    }
}