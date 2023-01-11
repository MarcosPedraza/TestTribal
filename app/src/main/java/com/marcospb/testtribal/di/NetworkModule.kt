package com.marcospb.testtribal.di


import com.marcospb.testtribal.data.remote.ChuckNorrisApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val BASE_URL = "https://api.chucknorris.io/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val htttpClient = OkHttpClient.Builder()
        val loggin = HttpLoggingInterceptor()
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
        htttpClient.addInterceptor(loggin)
        htttpClient.connectTimeout(10, TimeUnit.SECONDS)
        return htttpClient.build()
    }


    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient): ChuckNorrisApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChuckNorrisApi::class.java)
    }


}