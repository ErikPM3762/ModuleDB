package com.example.moduledb.controlDB.data.remote.server


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class BaseInterceptor @Inject constructor(private val networkMonitor: NetworkMonitor)  {

    private val retrofitTimeout = 20.toLong()
    private external fun getUriApiDev(): String
    private external fun getUriApiPre(): String
    private external fun getUriApiPro(): String
    private external fun getAutorizationDev(): String
    private external fun getAutorizationPre(): String
    private external fun getAutorizationPro(): String

    val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .readTimeout(retrofitTimeout, TimeUnit.SECONDS)
            .connectTimeout(retrofitTimeout, TimeUnit.SECONDS)
            .addInterceptor{ chain ->
                if (networkMonitor.isConnected) {
                    chain.proceed(chain.request())
                }
                else {
                    throw NoNetworkException()
                }
            }
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer $authToken")
                        .addHeader("Accept", "application/json")
                        .build()
                )
            }
            .addInterceptor(this)
            .authenticator { route, response ->
                // Refresh your access_token using a synchronous api request
                println(baseURL)
                val getOAuthTokenService = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(OkHttpClient.Builder()
                        .readTimeout(retrofitTimeout, TimeUnit.SECONDS)
                        .connectTimeout(retrofitTimeout, TimeUnit.SECONDS)
                        .addInterceptor{ chain ->
                            if (networkMonitor.isConnected) {
                                chain.proceed(chain.request())
                            }
                            else {
                                throw NoNetworkException()
                            }
                        }
                        .addInterceptor { chain ->
                            chain.proceed(
                                chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", getAutorizationPre())
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                    .addHeader("Accept", "application/json")
                                    .build()
                            )
                        }
                        .addInterceptor(this)
                        .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .run {
                        create(OracleServiceApi::class.java)
                    }

                val newAccessToken = getOAuthTokenService.getAuthToken("client_credentials", "ADOAPIs").execute()
                authToken = if (newAccessToken.isSuccessful) {
                    if (newAccessToken.body()!=null) {
                        // Add new header to rejected request and retry it
                        newAccessToken.body()!!.access_token
                    }
                    else {
                        println("ENTRE AL ELSE")
                        ""
                    }
                } else {
                    ""
                }

                response.request.newBuilder()
                    .header("Authorization", "Bearer $authToken")
                    .build()
            }
            .build()
    }


    private val baseURL: String = getUriApiPre()
    private val gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    companion object {
        var authToken = ""
    }

}