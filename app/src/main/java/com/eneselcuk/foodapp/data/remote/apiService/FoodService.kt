package com.eneselcuk.foodapp.data.remote.apiService

import com.eneselcuk.foodapp.data.remote.model.categorieName.CategoriesName
import com.eneselcuk.foodapp.data.remote.model.categories.FoodCategories
import com.eneselcuk.foodapp.data.remote.model.countryList.CountryList
import com.eneselcuk.foodapp.data.remote.model.countryList.countryDetail.CountryDetailList
import com.eneselcuk.foodapp.data.remote.model.foodId.FoodSearchId
import com.eneselcuk.foodapp.data.remote.model.random.FoodRandom
import retrofit2.http.GET
import retrofit2.http.Query




interface FoodService {

    @GET("categories.php")
    suspend fun getFoodCategories(): FoodCategories

    @GET("filter.php?i=chicken_breast")
    suspend fun getRandom(): FoodRandom

    @GET("lookup.php")
    suspend fun getSearchId(
        @Query("i") i: String,
    ): FoodSearchId

    @GET("filter.php")
    suspend fun getCategoriesName(
        @Query("c") c: String,
    ): CategoriesName

    @GET("list.php?a=list")
    suspend fun getList(): CountryList

    @GET("filter.php")
    suspend fun getDetailList(
        @Query("a") a: String,
    ): CountryDetailList


}