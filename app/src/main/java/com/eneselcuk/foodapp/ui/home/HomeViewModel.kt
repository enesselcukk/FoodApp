package com.eneselcuk.foodapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.foodapp.data.remote.model.categories.Category
import com.eneselcuk.foodapp.data.remote.model.categories.FoodCategories
import com.eneselcuk.foodapp.data.remote.model.countryList.CountryList
import com.eneselcuk.foodapp.data.remote.model.foodId.FoodSearchId
import com.eneselcuk.foodapp.data.remote.model.random.FoodRandom
import com.eneselcuk.foodapp.data.remote.model.random.Meal
import com.eneselcuk.foodapp.repository.FoodRepository
import com.eneselcuk.foodapp.util.FoodStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {

    private val _foodCategories: MutableLiveData<FoodCategories> = MutableLiveData()
    val foodCategories: LiveData<FoodCategories> = _foodCategories

    private val _foodRandom: MutableLiveData<FoodRandom> = MutableLiveData()
    val foodRandom: LiveData<FoodRandom> = _foodRandom

    private val _navigation = MutableLiveData<Meal?>()
    val navigation: LiveData<Meal?>
        get() = _navigation

    private val _navigationCategories = MutableLiveData<Category?>()
    val navigationCategories: LiveData<Category?>
        get() = _navigationCategories

    private val _foodId: MutableLiveData<FoodSearchId?> = MutableLiveData()
    val foodId: LiveData<FoodSearchId?> = _foodId

    private val _progressBar = MutableLiveData<FoodStatus>()
    val progressBar: LiveData<FoodStatus> = _progressBar

    private val _countryList: MutableLiveData<CountryList> = MutableLiveData()
    val countryList: LiveData<CountryList> = _countryList

    private val _countryNavigation =
        MutableLiveData<com.eneselcuk.foodapp.data.remote.model.countryList.Meal?>()
    val countryNavigation: LiveData<com.eneselcuk.foodapp.data.remote.model.countryList.Meal?>
        get() = _countryNavigation

    fun setNavigation(food: com.eneselcuk.foodapp.data.remote.model.countryList.Meal) {
        _countryNavigation.postValue(food)
    }

    fun setCountryDisplay() {
        _countryNavigation.postValue(null)
    }

    fun setNavigation(food: Meal) {
        _navigation.postValue(food)
    }

    fun setCategories(categories: Category) {
        _navigationCategories.postValue(categories)
    }

    fun setCategoriesDisplay() {
        _navigationCategories.postValue(null)
    }

    fun displayComplated() {
        _navigation.postValue(null)
    }

    init {
        getAllCategories()
        getAllFoodRandom()
        getList()
    }

    private fun getAllCategories() {
        try {
            _progressBar.postValue(FoodStatus.LOADING)
            viewModelScope.launch {
                repository.foodCategories().let {
                    _foodCategories.postValue(it)
                    _progressBar.postValue(FoodStatus.DONE)
                }
            }
        } catch (ex: Exception) {
            _progressBar.postValue(FoodStatus.ERROR)
        }
    }

    private fun getAllFoodRandom() {
        try {
            _progressBar.postValue(FoodStatus.LOADING)

            viewModelScope.launch {
                repository.foodRandom().let {
                    _foodRandom.postValue(it)
                    _progressBar.postValue(FoodStatus.DONE)
                }
            }
        } catch (ex: Exception) {
            _progressBar.postValue(FoodStatus.ERROR)
        }
    }

    fun getId(id: String) {
        try {
            _progressBar.postValue(FoodStatus.LOADING)
            viewModelScope.launch {
                repository.getId(id).let { foodSearchId ->
                    _progressBar.postValue(FoodStatus.DONE)
                    return@let _foodId.postValue(foodSearchId)
                }
            }
        } catch (ex: Exception) {
            _progressBar.postValue(FoodStatus.ERROR)
        }
    }

    // CountryList
    private fun getList() {
        viewModelScope.launch {
            val list = repository.getCountryList()
            _countryList.postValue(list)
        }
    }
}




