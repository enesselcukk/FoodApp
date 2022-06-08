package com.eneselcuk.foodapp.ui.country.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.Meal


class CountryDetailAdapter(private val click: DetailClick) :
    ListAdapter<Meal, CountryDetailHolder>(BaseDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryDetailHolder {
        return CountryDetailHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_country_detail,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: CountryDetailHolder, position: Int) {
        val positionDetail = getItem(position)
        holder.bind(positionDetail,click)
    }

    class DetailClick(val click: (meal: Meal) -> Unit) {
        fun onClickDetail(meal: Meal) = click(meal)
    }
}