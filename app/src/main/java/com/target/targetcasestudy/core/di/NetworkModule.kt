package com.target.targetcasestudy.core.di

import com.target.targetcasestudy.data.deals.remote.DealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Provides
    fun getTestNetwork(retrofit: Retrofit): DealApi {
        return retrofit.create(DealApi::class.java)
    }

}