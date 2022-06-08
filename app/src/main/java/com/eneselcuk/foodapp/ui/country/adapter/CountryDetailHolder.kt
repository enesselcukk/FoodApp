package com.eneselcuk.foodapp.ui.country.adapter

import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.Meal
import com.eneselcuk.foodapp.databinding.ItemCountryDetailBinding

class CountryDetailHolder(private val binding: ItemCountryDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(countryDetailList: Meal, countryClick: CountryDetailAdapter.DetailClick) {
        binding.countryDetail = countryDetailList
        binding.click = countryClick
    }
}