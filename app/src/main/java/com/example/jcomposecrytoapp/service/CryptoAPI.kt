package com.example.jcomposecrytoapp.service

import com.example.jcomposecrytoapp.model.Crypto
import com.example.jcomposecrytoapp.model.CryptoItem
import com.example.jcomposecrytoapp.model.CryptoList
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoAPI {

    @GET("prices")
    suspend fun  getCryptoList(
        @Query("key") key: String,
    ):CryptoList

    @GET("currencies")
    suspend fun getCrypto(
        @Query("key") key: String,
        @Query("ids") id:String,
        @Query("attributes") attributes: String
    ):Crypto
}