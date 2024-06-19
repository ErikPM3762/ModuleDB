package com.example.moduledb.controlDB.di

import android.content.Context
import com.example.moduledb.controlDB.data.local.daos.MDbDetailStopDao
import com.example.moduledb.controlDB.data.local.daos.MDbStopsDao
import com.example.moduledb.controlDB.data.local.daos.MDbTheoricsByTypeStopDao
import com.example.moduledb.controlDB.data.remote.repository.StopsRepositoryImpl
import com.example.moduledb.controlDB.data.remote.server.AwsServiceApi
import com.example.moduledb.controlDB.data.remote.server.IApiHereServiceApi
import com.example.moduledb.controlDB.data.remote.server.LiveNetworkMonitor
import com.example.moduledb.controlDB.data.remote.server.MDbBaseInterceptor
import com.example.moduledb.controlDB.data.remote.server.NetworkMonitor
import com.example.moduledb.controlDB.data.remote.server.OracleServiceApi
import com.example.moduledb.controlDB.data.remote.source.ApiHereDataSource
import com.example.moduledb.controlDB.data.remote.source.IApiHereDataSource
import com.example.moduledb.controlDB.data.remote.source.IInfoMapDataSource
import com.example.moduledb.controlDB.data.remote.source.ILinesDataSource
import com.example.moduledb.controlDB.data.remote.source.IRegionsDataSource
import com.example.moduledb.controlDB.data.remote.source.IStopsDataSource
import com.example.moduledb.controlDB.data.remote.source.InfoMapDataSource
import com.example.moduledb.controlDB.data.remote.source.LinesDataSource
import com.example.moduledb.controlDB.data.remote.source.RegionsDataSource
import com.example.moduledb.controlDB.data.remote.source.StopDataSource
import com.example.moduledb.controlDB.domain.repository.StopsRepository
import com.example.moduledb.controlDB.utils.EnvironmentManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModuleInject {

    /**
     * En este apartado configuraremos las inyecciones correspondientes para los servicios de consulta
     * de API a Oracle
     */

    @Singleton
    @Provides
    fun provideInterceptor(networkMonitor: NetworkMonitor) = MDbBaseInterceptor()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): OracleServiceApi {
        return Retrofit.Builder()
            .baseUrl(EnvironmentManager.uriApiOracle)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(OracleServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideInfoMapDataSource(
        oracleServiceApi: OracleServiceApi,
        awsServiceApi: AwsServiceApi
    ): IInfoMapDataSource {
        return InfoMapDataSource(oracleServiceApi, awsServiceApi)
    }

    @Singleton
    @Provides
    fun provideMacroRegionsRemoteDataSource(
        serviceApi: OracleServiceApi,
        awsServiceApi: AwsServiceApi
    ): IRegionsDataSource {
        return RegionsDataSource(serviceApi, awsServiceApi)
    }

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
        return StopsRepositoryImpl(
            serviceApi,
            awsServiceApi,
            remoteDataSource,
            stopsDao,
            detailStopsDao,
            theoricByTypeStop
        )
    }

    @Singleton
    @Provides
    fun provideLinesRemoteDataSource(
        serviceApi: OracleServiceApi,
        awsServiceApi: AwsServiceApi
    ): ILinesDataSource {
        return LinesDataSource(serviceApi, awsServiceApi)
    }

    @Singleton
    @Provides
    fun provideStopRemoteDataSource(
        serviceApi: OracleServiceApi,
        serviceApiAws: AwsServiceApi
    ): IStopsDataSource {
        return StopDataSource(serviceApi, serviceApiAws)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: MDbBaseInterceptor): OkHttpClient {
        return interceptor.oracleOkHttpClient
    }

    @Singleton
    @Provides
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return LiveNetworkMonitor(context)
    }


    @Singleton
    @Provides
    fun provideApiHereService(interceptor: MDbBaseInterceptor): IApiHereServiceApi {
        val client = interceptor.apiHereOkHttpClient
        return Retrofit.Builder()
            .baseUrl("https://autosuggest.search.hereapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(IApiHereServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApiHereDataSource(service: IApiHereServiceApi): IApiHereDataSource {
        return ApiHereDataSource(service)
    }

    /**
     * En este apartado configuraremos las inyecciones correspondientes para los servicios de consulta
     * de API a AWS
     */

    @Singleton
    @Provides
    fun provideRetrofitLinesSpain(interceptor: MDbBaseInterceptor): AwsServiceApi {
        val client = interceptor.awsOkHttpClient
        return Retrofit.Builder()
            .baseUrl(EnvironmentManager.uriApiAws)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(AwsServiceApi::class.java)
    }
}