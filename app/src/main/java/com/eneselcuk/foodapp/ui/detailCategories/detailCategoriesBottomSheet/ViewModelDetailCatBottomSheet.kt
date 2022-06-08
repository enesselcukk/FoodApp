package com.eneselcuk.foodapp.ui.detailCategories.detailCategoriesBottomSheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.foodapp.data.remote.model.categorieName.Meal
import com.eneselcuk.foodapp.data.remote.model.foodId.FoodSearchId
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetailCatBottomSheet @Inject constructor(private val repository: FoodRepository) :
    ViewModel() {

    private val _getFood: MutableLiveData<FoodSearchId> =
        MutableLiveData()
    val getFood: LiveData<FoodSearchId> = _getFood

    private val _bottomSheet: MutableLiveData<Meal> = MutableLiveData()
    val bottomSheet: LiveData<Meal> = _bottomSheet

    fun getFood(id: String) {
        viewModelScope.launch {
            val name = repository.getId(id)
            _getFood.postValue(name)
        }
    }

    fun setCategories(meal: Meal) {
        _bottomSheet.postValue(meal)
    }
}