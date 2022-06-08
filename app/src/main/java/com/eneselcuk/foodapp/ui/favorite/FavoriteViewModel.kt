package com.eneselcuk.foodapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {

    private val _getMutableDatabase: MutableLiveData<List<FoodEntity>> = MutableLiveData()
    val getLiveDatabase: LiveData<List<FoodEntity>> = _getMutableDatabase

    private val _favoriteNavigation: MutableLiveData<FoodEntity?> = MutableLiveData()
    val favoriteNavigation: LiveData<FoodEntity?> = _favoriteNavigation


    init {
        getAllDatabase()
    }

    fun getAllDatabase() {
        viewModelScope.launch {
            repository.getAllFood().let { listFoodEntity ->
                _getMutableDatabase.postValue(listFoodEntity)
            }
        }
    }

    fun deleteFoodID(foodId: Int?) {
        viewModelScope.launch {
            repository.deleteFood(foodId)
        }
    }

    fun setNavigation(foodEntity: FoodEntity) {
        _favoriteNavigation.postValue(foodEntity)
    }

    fun playDish() {
        _favoriteNavigation.postValue(null)
    }
}