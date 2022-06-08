package com.eneselcuk.foodapp.repository

import androidx.lifecycle.LiveData
import com.eneselcuk.foodapp.data.local.FoodDao
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.data.remote.apiService.FoodService
import com.eneselcuk.foodapp.data.remote.model.categorieName.CategoriesName
import com.eneselcuk.foodapp.data.remote.model.categories.FoodCategories
import com.eneselcuk.foodapp.data.remote.model.countryList.CountryList
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.CountryDetailList
import com.eneselcuk.foodapp.data.remote.model.foodId.FoodSearchId
import com.eneselcuk.foodapp.data.remote.model.random.FoodRandom
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FoodRepository @Inject constructor(
    private val foodApi: FoodService,
    private val foodDataBase: FoodDao,
) {
    suspend fun foodCategories(): FoodCategories = foodApi.getFoodCategories()
    suspend fun foodRandom(): FoodRandom = foodApi.getRandom()
    suspend fun getId(id: String): FoodSearchId = foodApi.getSearchId(id)
    suspend fun getCategoriesDetail(name: String): CategoriesName = foodApi.getCategoriesName(name)
    suspend fun getCountryList(): CountryList = foodApi.getList()
    suspend fun getCountryDetailList(name: String): CountryDetailList = foodApi.getDetailList(name)


    suspend fun insertFood(foodEntity: FoodEntity) = foodDataBase.insertFoodFavorite(foodEntity)
    suspend fun getAllFood(): List<FoodEntity> = foodDataBase.getAllFood()
    suspend fun deleteFood(id: Int?) = foodDataBase.deleteIdFood(id)
    fun searchData(name: String?): LiveData<List<FoodEntity>> = foodDataBase.searchFood(name)


}

