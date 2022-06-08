package com.eneselcuk.foodapp.di.appModule

import com.eneselcuk.foodapp.BuildConfig.BASE_URL
import com.eneselcuk.foodapp.data.remote.apiService.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.GsonBuilder



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    internal fun providesFoodApi(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .baseUrl(BASE_URL)
            .build()

    }

    @Singleton
    @Provides
    fun provideGetQuotes(retrofit: Retrofit): FoodService {
        return retrofit.create(FoodService::class.java)
    }

}