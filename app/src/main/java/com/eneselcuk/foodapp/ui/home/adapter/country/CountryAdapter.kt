package com.eneselcuk.foodapp.ui.home.adapter.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.remote.model.countryList.Meal

class CountryAdapter(private val onClick: Click) :
    ListAdapter<Meal, CountryHolder>(BaseDiffUtilCountry) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val countryPosition = getItem(position)
        holder.bind(countryPosition, onClick)
    }

    class Click(val click: (country: Meal) -> Unit) {
        fun onClick(country: Meal) = click(country)
    }
}