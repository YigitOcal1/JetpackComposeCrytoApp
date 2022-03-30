package com.example.jcomposecrytoapp.dependencyinjection

import com.example.jcomposecrytoapp.service.CryptoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import com.example.jcomposecrytoapp.util.Constants.BASE_URL
import com.example.jcomposecrytoapp.repository.CryptoRepository


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(
        api: CryptoAPI
    )= CryptoRepository(api)


    @Singleton
    @Provides
    fun provideCryptoAPI():CryptoAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CryptoAPI::class.java)
    }
}