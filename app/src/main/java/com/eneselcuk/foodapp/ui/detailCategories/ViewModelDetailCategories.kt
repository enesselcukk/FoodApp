package com.eneselcuk.foodapp.ui.detailCategories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.foodapp.data.remote.model.categorieName.CategoriesName
import com.eneselcuk.foodapp.data.remote.model.categorieName.Meal
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetailCategories @Inject constructor(private val repository: FoodRepository) :
    ViewModel() {

    private val _detailCategories: MutableLiveData<CategoriesName> = MutableLiveData()
    val detailCategories: LiveData<CategoriesName> = _detailCategories

    private val _setNavigation: MutableLiveData<Meal?> = MutableLiveData()
    val setNavigation: LiveData<Meal?> = _setNavigation

    fun getCategories(name: String) {
        viewModelScope.launch {
            val categories = repository.getCategoriesDetail(name)
            _detailCategories.postValue(categories)
        }
    }

    fun navigation(categoriesName: Meal) {
        _setNavigation.postValue(categoriesName)
    }

    fun navigationDisplay() {
        _setNavigation.postValue(null)
    }
}