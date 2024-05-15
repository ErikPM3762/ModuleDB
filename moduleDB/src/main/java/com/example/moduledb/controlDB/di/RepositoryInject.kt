package com.example.moduledb.controlDB.di

import com.example.moduledb.controlDB.data.local.daos.MDbDetailStopDao
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.daos.MDbTheoricsByTypeStopDao
import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import com.example.moduledb.controlDB.domain.repository.StopsRepository
import com.example.services.data.server.AwsServiceApi
import com.example.services.data.server.OracleServiceApi
import com.example.services.data.source.IStopsDataSource
import com.example.services.di.ModuleInject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryInject {
    @Singleton
    @Provides
    fun provideStopsRepository(
        serviceApi: OracleServiceApi,
        awsServiceApi: AwsServiceApi,
        remoteDataSource: IStopsDataSource,
        stopsDao: MDbStopsDao,
        detailStopsDao: MDbDetailStopDao,
        theoricByTypeStop: MDbTheoricsByTypeStopDao,
    ): StopsRepository {
        return StopsRepositoryImpl(serviceApi, awsServiceApi, remoteDataSource, stopsDao, detailStopsDao,theoricByTypeStop )
    }
}