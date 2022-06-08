package com.eneselcuk.foodapp.util

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.data.remote.model.categories.Category
import com.eneselcuk.foodapp.data.remote.model.foodId.Meal
import com.eneselcuk.foodapp.ui.home.adapter.categories.CategoriesAdapter


@BindingAdapter("app:foodCategoriesImage")
fun ImageView.setCategoriesUrl(imageIcon: String?) {
    Glide.with(context)
        .load(imageIcon)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:foodCategoriesRecyclerView")
fun Recycler(recyclerView: RecyclerView, data: List<Category>) {
    val adapterRcycler = recyclerView.adapter as CategoriesAdapter
    adapterRcycler.submitList(data)
}

@BindingAdapter("app:progressVisibility")
fun ProgressBar.visible(status: FoodStatus?) {

    when (status) {
        FoodStatus.DONE -> {
            this.visibility = View.GONE
        }
        FoodStatus.ERROR -> {
            this.visibility = View.VISIBLE
        }
        FoodStatus.LOADING -> {
            this.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("app:searchVisibility")
fun ImageView.searchVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}