package com.example.jcomposecrytoapp.repository

import com.example.jcomposecrytoapp.model.Crypto
import com.example.jcomposecrytoapp.model.CryptoList
import com.example.jcomposecrytoapp.service.CryptoAPI
import com.example.jcomposecrytoapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Scope
import com.example.jcomposecrytoapp.util.Constants.API_KEY
import com.example.jcomposecrytoapp.util.Constants.CALL_ATTRIBUTES
import java.lang.Exception

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api:CryptoAPI
){
    suspend fun getCryptoList(): Resource<CryptoList>{

        val response=try {
            api.getCryptoList(API_KEY)
        } catch (e:Exception){
            return Resource.Error("error")
        }
        return Resource.Success(response)
    }
    suspend fun getCrypto(id:String):Resource<Crypto>{
        val response=try {
            api.getCrypto(API_KEY,id, CALL_ATTRIBUTES)
        }
        catch (e:Exception){
            return Resource.Error("error")
        }
        return Resource.Success(response)
    }
}