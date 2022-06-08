package com.eneselcuk.foodapp.ui.search


import androidx.lifecycle.*
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {
    fun getSearchData(searchName: String?): LiveData<List<FoodEntity>> =
        repository.searchData(searchName)


}
