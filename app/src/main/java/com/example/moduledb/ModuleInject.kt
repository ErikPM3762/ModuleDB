package com.example.moduledb

import android.content.Context
import com.example.moduledb.data.server.source.InfoMapRemoteDataSource
import com.example.moduledb.data.server.source.RetrofitInfoMapDataSource
import com.example.moduledb.data.server.remote.BaseInterceptor
import com.example.moduledb.data.server.remote.Constantes
import com.example.moduledb.data.server.remote.LiveNetworkMonitor
import com.example.moduledb.data.server.remote.NetworkMonitor
import com.example.moduledb.data.server.ServiceApi
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

    @Singleton
    @Provides
    fun provideInterceptor (networkMonitor: NetworkMonitor) = BaseInterceptor(networkMonitor)

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): ServiceApi {
        return Retrofit.Builder()
            .baseUrl(Constantes.URI_API_PRE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(serviceApi: ServiceApi): InfoMapRemoteDataSource {
        return RetrofitInfoMapDataSource(serviceApi)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: BaseInterceptor): OkHttpClient {
        return interceptor.okHttpClient
    }

    @Singleton
    @Provides
    fun provideNetworkMonitor (@ApplicationContext context : Context) : NetworkMonitor {
        return LiveNetworkMonitor(context)
    }
}