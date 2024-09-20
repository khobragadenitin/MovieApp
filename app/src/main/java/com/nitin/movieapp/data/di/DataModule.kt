package com.nitin.movieapp.data.di

import com.nitin.movieapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit():Retrofit{
        val certificatePinner = CertificatePinner.Builder()
            .add("api.themoviedb.org","sha256/k1Hdw5sdSn5kh/gemLVSQD/P4i4IBQEY1tW4WNxh9XM=")
            .build()
        val client = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/").client(client).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

}