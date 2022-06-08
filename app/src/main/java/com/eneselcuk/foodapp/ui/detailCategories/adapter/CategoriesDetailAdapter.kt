package com.eneselcuk.foodapp.ui.detailCategories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.remote.model.categorieName.Meal

class CategoriesDetailAdapter(private val detailClick: DetailCategoriesClick) :
    ListAdapter<Meal, CategoriesHolder>(BackDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_categoires_detail,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val positionCategories = getItem(position)
        holder.bind(positionCategories, detailClick)
    }

    class DetailCategoriesClick(val click: (meal: Meal) -> Unit) {
        fun onClick(meal: Meal) = click(meal)
    }

}