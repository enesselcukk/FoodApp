package com.eneselcuk.foodapp.ui.home.adapter.recommended

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.remote.model.random.Meal

class RecommendedAdapter(private val click: OnClick) :
    ListAdapter<Meal, RecommendedHolder>(BaseDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedHolder {
        return RecommendedHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_recommended,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RecommendedHolder, position: Int) {
        val rPosition = getItem(position)
        holder.bind(rPosition, click)
    }

    class OnClick(val click: (meal: Meal) -> Unit) {
        fun foodClick(meal: Meal) = click(meal)
    }
}