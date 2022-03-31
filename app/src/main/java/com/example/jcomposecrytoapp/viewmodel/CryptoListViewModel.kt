package com.example.jcomposecrytoapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jcomposecrytoapp.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.jcomposecrytoapp.util.Resource
import com.example.jcomposecrytoapp.model.CryptoListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CryptoListViewModel  @Inject constructor(
    private val repository: CryptoRepository):ViewModel(){
        var cryptoList= mutableStateOf<List<CryptoListItem>>(listOf())
        var errorMessage= mutableStateOf("")
        var isLoading= mutableStateOf(false)

    private var initialCryptoList= listOf<CryptoListItem>()
    private var isSearchStarting=true
    init {
    loadCryptos()
    }

    fun searchCryptoList(query:String){
        val listToSearch=if(isSearchStarting){
            cryptoList.value
        }
        else{
            initialCryptoList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value=initialCryptoList
                isSearchStarting=true
                return@launch
            }
            val results=listToSearch.filter {
                it.currency.contains(query.trim(),ignoreCase = true)
            }
            if(isSearchStarting){
                initialCryptoList=cryptoList.value
                isSearchStarting=false
            }
            cryptoList.value=results
        }
    }
    fun loadCryptos(){

        viewModelScope.launch {
            isLoading.value=true
            val result=repository.getCryptoList()
            when(result){

                is Resource.Success->{
                    val cryptoItems=result.data!!.mapIndexed { index, item ->
                        CryptoListItem(item.currency,item.price)
                    }


                    errorMessage.value=""
                    isLoading.value=false
                    cryptoList.value+=cryptoItems
                }
                is Resource.Error->{
                errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
        }

    }
    }



