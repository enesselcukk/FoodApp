package com.eneselcuk.foodapp.ui.search.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.local.FoodEntity



class SearchAdapter :
    ListAdapter<FoodEntity, SearchHolder>(BaseDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_search,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val positionSearch = getItem(position)
        holder.bind(positionSearch)
    }
}