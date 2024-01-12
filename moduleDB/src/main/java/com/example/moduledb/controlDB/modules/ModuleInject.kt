package com.example.moduledb.controlDB.modules

import android.content.Context
import com.example.moduledb.controlDB.data.remote.ServiceApi
import com.example.moduledb.controlDB.data.remote.server.BaseInterceptor
import com.example.moduledb.controlDB.data.remote.server.LiveNetworkMonitor
import com.example.moduledb.controlDB.data.remote.server.NetworkMonitor
import com.example.moduledb.controlDB.data.remote.source.InfoMapRemoteDataSource
import com.example.moduledb.controlDB.data.remote.source.RetrofitInfoMapDataSource
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
     * Funciones externas en archivo C++ para obtener las URL base correspondientes
     * para la consulta de servicios
     */
    private external fun getUriApiDev():String
    private external fun getUriApiPre():String
    private external fun getUriApiPro():String

    /**
     * En este apartado configuraremos las inyecciones correspondientes para los servicios de consulta
     * de API a Oracle
     */

    @Singleton
    @Provides
    fun provideInterceptor (networkMonitor: NetworkMonitor) = BaseInterceptor(networkMonitor)

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): ServiceApi {
        return Retrofit.Builder()
            .baseUrl(getUriApiPre())
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

    /**
     * En este apartado configuraremos las inyecciones correspondientes para los servicios de consulta
     * de API a AWS
     */
}