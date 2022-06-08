package com.eneselcuk.foodapp.ui.country.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneselcuk.foodapp.data.remote.model.foodId.FoodSearchId
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelBottomSheet @Inject constructor(private val repository: FoodRepository) :
    ViewModel() {

    private val _countryId: MutableLiveData<FoodSearchId> = MutableLiveData()
    val countryId: LiveData<FoodSearchId> = _countryId

    fun getDetailId(id: String) {
        viewModelScope.launch {
            val foodId = repository.getId(id)
            _countryId.postValue(foodId)
        }
    }
}