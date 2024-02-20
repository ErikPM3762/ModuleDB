package com.example.moduledb.controlDB.data.remote.server


import com.example.moduledb.controlDB.data.remote.request.AuthTokenAwsRequest
import com.example.moduledb.controlDB.utils.EnvironmentManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import javax.inject.Inject


class MDbBaseInterceptor @Inject constructor() {

    private val retrofitTimeout = 20.toLong()

    val oracleOkHttpClient = getOkHttpClient(InterceptorType.ORACLE)

    val awsOkHttpClient = getOkHttpClient(InterceptorType.AWS)

    val gsonAws = Gson()


    enum class InterceptorType {
        AWS, ORACLE
    }

    enum class Header(val header: String, val value: String) {
        Accept("Accept", "application/json"),
        Content_Type("Content-Type", "application/x-www-form-urlencoded")
    }

    private fun getOkHttpClient(type: InterceptorType) = HttpLoggingInterceptor().run {
        when (type) {
            InterceptorType.AWS -> {
                level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .followRedirects(false)
                    .followSslRedirects(false)
                    .readTimeout(retrofitTimeout, TimeUnit.SECONDS)
                    .connectTimeout(retrofitTimeout, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request()
                                .newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Bearer ${getAuthAWS(this)}"
                                )
                                .addHeader(
                                    "x-cache-api",
                                    gsonAws.toJson(chain.request()).substringAfter("arguments\":[")
                                        .substringBefore("],\"method").replace("ñ", "n")
                                )
                                .addHeader("Accept", "application/json")
                                .build()
                        )
                    }
                    .addInterceptor(this)
                    .build()
            }

            InterceptorType.ORACLE -> {
                level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .readTimeout(retrofitTimeout, TimeUnit.SECONDS)
                    .connectTimeout(retrofitTimeout, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Bearer $oracleAuthToken")
                                .addHeader("Accept", "application/json")
                                .build()
                        )
                    }
                    .addInterceptor(this)
                    .authenticator { _, response ->
                        // Refresh your access_token using a synchronous api request
                        val getOAuthTokenService = Retrofit.Builder()
                            .baseUrl(EnvironmentManager.uriApiOracle)
                            .client(OkHttpClient.Builder()
                                .readTimeout(retrofitTimeout, TimeUnit.SECONDS)
                                .connectTimeout(retrofitTimeout, TimeUnit.SECONDS)
                                .addInterceptor { chain ->
                                    chain.proceed(
                                        chain.request()
                                            .newBuilder()
                                            .addHeader(
                                                "Authorization",
                                                EnvironmentManager.authorizationOracle
                                            )
                                            .addHeader(
                                                "Content-Type",
                                                "application/x-www-form-urlencoded"
                                            )
                                            .addHeader("Accept", "application/json")
                                            .build()
                                    )
                                }
                                .addInterceptor(this)
                                .build()
                            )
                            .addCallAdapterFactory(CoroutineCallAdapterFactory())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()
                            .run {
                                create(OracleServiceApi::class.java)
                            }

                        val logger = Logger.getLogger(this.javaClass.name)
                        logger.info("Requesting Token from Lines Module")
                        val newAccessToken =
                            getOAuthTokenService.getAuthToken("client_credentials", "ADOAPIs")
                                .execute()
                        oracleAuthToken = if (!newAccessToken.isSuccessful) ""
                        else newAccessToken.body()?.access_token
                            ?: ""
                        if (oracleAuthToken.isNotEmpty()) logger.info("New access token from Lines Module $oracleAuthToken")

                        response.request.newBuilder()
                            .header("Authorization", "Bearer $oracleAuthToken")
                            .build()
                    }
                    .build()


            }
        }
    }


    private fun getAuthAWS(interceptor: HttpLoggingInterceptor): String {
        // Refresh your access_token using a synchronous api request
        val getOAuthTokenService = Retrofit.Builder()
            .baseUrl(EnvironmentManager.uriApiHardcode)
            .client(
                OkHttpClient.Builder()
                    .readTimeout(retrofitTimeout, TimeUnit.SECONDS)
                    .connectTimeout(retrofitTimeout, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request()
                                .newBuilder()
                                .addHeader("Authorization", EnvironmentManager.authorizationAws)
                                .addHeader("Accept", "application/json")
                                .addHeader(
                                    "x-cache-api",
                                    "100espanaprovincia_segoviasegovia652I20220715"
                                )
                                .build()
                        )
                    }
                    .addInterceptor(interceptor)
                    .build()
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .run {
                create(AwsServiceApi::class.java)
            }

        val newAccessToken = getOAuthTokenService.getAWSAuthToken(AuthTokenAwsRequest()).execute()
        awsAuthToken = if (newAccessToken.isSuccessful && newAccessToken.body() != null) {
            newAccessToken.body()!!.data.idToken
        } else ""
        return awsAuthToken
    }

    private val gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    companion object {
        var awsAuthToken = ""
        var oracleAuthToken = ""
    }
}