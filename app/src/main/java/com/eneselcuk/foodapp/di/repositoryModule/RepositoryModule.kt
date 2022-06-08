package com.eneselcuk.foodapp.di.repositoryModule


import com.eneselcuk.foodapp.data.local.FoodDao
import com.eneselcuk.foodapp.data.remote.apiService.FoodService
import com.eneselcuk.foodapp.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        api: FoodService,
        dao: FoodDao,
    ) = FoodRepository(api, dao)
}