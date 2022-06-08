package com.eneselcuk.foodapp.ui.home.adapter.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.remote.model.categories.Category

class CategoriesAdapter(private val click: Click) :
    ListAdapter<Category, MyViewHolder>(BaseDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.view_categories,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categoriesIndex = getItem(position)
        holder.bind(categoriesIndex, click)
    }

    class Click(val click: (category: Category) -> Unit) {
        fun onClick(category: Category) = click(category)
    }

}