package com.target.targetcasestudy.core.di

import com.target.targetcasestudy.data.deals.remote.DealRemoteSource
import com.target.targetcasestudy.data.deals.remote.DealRemoteSourceImpl
import com.target.targetcasestudy.data.deals.repo.DealsRepository
import com.target.targetcasestudy.data.deals.repo.DealsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    abstract fun dealRepository(item: DealsRepositoryImpl): DealsRepository

    @Binds
    abstract fun dealRemoteSource(item: DealRemoteSourceImpl): DealRemoteSource

}