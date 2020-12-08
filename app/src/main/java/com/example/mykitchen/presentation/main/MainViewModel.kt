package com.example.mykitchen.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.API_KEY
import com.example.mykitchen.data.remote.RecipeApiService
import com.example.mykitchen.data.remote.RecipeResponse
import com.example.mykitchen.URL_LINK
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.usecase.CreateRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//View model ne connait pas la view. Il update seulement les variables
class MainViewModel(
    private val createRecipeUseCase: CreateRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase//Koin injection
) :ViewModel(){

    var listRecipe:MutableLiveData<List<Recipe>> = MutableLiveData()

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
}