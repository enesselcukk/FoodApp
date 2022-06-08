package com.eneselcuk.foodapp.ui.detail.detailBottomSheet

import androidx.lifecycle.*
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.data.remote.model.foodId.Meal
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetailBottom @Inject constructor(private val foodRepository: FoodRepository) :
    ViewModel() {
    //
    private val _insertFood: MutableLiveData<Meal> = MutableLiveData()
    val insertFood: MutableLiveData<Meal> = _insertFood

    private val _getFood: MutableLiveData<List<FoodEntity>> = MutableLiveData()
    val getFood: LiveData<List<FoodEntity>> = _getFood


    fun addRemoteData(meal: Meal) {
        _insertFood.postValue(meal)
    }

    fun getAllFood() {
        viewModelScope.launch {
            foodRepository.getAllFood().let {
                _getFood.postValue(it)
            }
        }
    }

    fun addFood(situation: Boolean) {
        insertFood.value.let {
            it?.let { meal ->
                viewModelScope.launch {
                    foodRepository.insertFood(FoodEntity(
                        id = meal.idMeal?.toInt(),
                        food_name = meal.strMeal,
                        image = meal.strMealThumb,
                        liked = situation,
                        youtube = meal.strYoutube,
                        tags = meal.strTags,
                        details = meal.strSource
                    ))
                }
            }
        }
    }


    fun deleteFood(id: Int?) {
        viewModelScope.launch {
            foodRepository.deleteFood(id)
        }
    }
}