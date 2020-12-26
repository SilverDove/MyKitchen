package com.example.mykitchen.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.usecase.AddRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


//View model ne connait pas la view. Il update seulement les variables
class MainViewModel(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase//Koin injection
) :ViewModel(){

    var listRecipe:MutableLiveData<List<Recipe>> = MutableLiveData()
    var recipeURL: MutableLiveData<String> = MutableLiveData()

    fun makeAPICall(query: String?) {
        //on passe dans un thread en background
        viewModelScope.launch(Dispatchers.IO) {
            val recipeList = getRecipeUseCase.getAllRecipe(query)
            //on se remet dans le Main thread (on est obligé lorsqu'on met a jour la vue via une LiveData
            withContext(Dispatchers.Main){
                listRecipe.value = recipeList
            }

            //on se remet dans le thread en background
        }
    }

    fun getRecipeURL(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getRecipeURL(id)
            //on se remet dans le Main thread (on est obligé lorsqu'on met a jour la vue via une LiveData
            withContext(Dispatchers.Main){
                recipeURL.value = response
            }
            //on se remet dans le thread en background
        }
    }
}