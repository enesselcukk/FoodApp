package com.eneselcuk.foodapp.ui.home.adapter.country

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.remote.model.countryList.Meal
import com.eneselcuk.foodapp.databinding.ItemCountryBinding

class CountryHolder(private val binding: ItemCountryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(countryList: Meal, onClick: CountryAdapter.Click) {
        binding.country = countryList
        binding.click = onClick

    }
}