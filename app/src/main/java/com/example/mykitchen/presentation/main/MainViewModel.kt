package com.example.mykitchen.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.domain.usecase.CreateRecipeUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//View model ne connait pas la view. Il update seulement les variables
class MainViewModel(
    private val createRecipeUseCase: CreateRecipeUseCase //Koin injection
) :ViewModel(){

    val test:MutableLiveData<String> = MutableLiveData()

    /*fun WhenCallOtherFunctionFromAnotherPackage(){
        viewModelScope.launch {
            createRecipeUseCase.invoke(recipe)
        }
    }*/

}