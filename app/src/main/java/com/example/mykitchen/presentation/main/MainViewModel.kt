package com.example.mykitchen.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getRecipeUseCase: GetRecipeUseCase
) :ViewModel(){

    var listRecipe:MutableLiveData<List<Recipe>> = MutableLiveData()
    var recipeURL: MutableLiveData<String> = MutableLiveData()

    fun makeAPICall(query: String?) {
        //we go to a thread for background process to do APIcall
        viewModelScope.launch(Dispatchers.IO) {
            val recipeList = getRecipeUseCase.getAllRecipe(query)
            //Go back to main thread when we want tp update the list of recipes
            withContext(Dispatchers.Main){
                listRecipe.value = recipeList
            }
        }
    }

    fun getRecipeURL(id: Int) {
        //we go to a thread for background process to get the url of the recipe selected
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getRecipeURL(id)
            //Go back to main thread when we want tp update the url of the selected recipe
            withContext(Dispatchers.Main){
                recipeURL.value = response
            }
        }
    }
}