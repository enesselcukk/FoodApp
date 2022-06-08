package com.eneselcuk.foodapp.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.CountryDetailList
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.Meal
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCountry @Inject constructor(private val repository: FoodRepository) : ViewModel() {

    private val _country: MutableLiveData<CountryDetailList> = MutableLiveData()
    val country: LiveData<CountryDetailList> = _country

    private val _navigation: MutableLiveData<Meal?> = MutableLiveData()
    val navigation: LiveData<Meal?> = _navigation


    fun setDetailNavigation(name: Meal) {
        _navigation.postValue(name)
    }

    fun disPlay() {
        _navigation.postValue(null)
    }

    fun getDetailCountry(name: String) {
        viewModelScope.launch {
            val data = repository.getCountryDetailList(name)
            _country.postValue(data)
        }
    }


}