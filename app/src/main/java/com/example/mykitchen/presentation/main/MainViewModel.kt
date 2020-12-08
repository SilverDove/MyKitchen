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

    /*fun WhenCallOtherFunctionFromAnotherPackage(){
        viewModelScope.launch(Displatchers.IO) {
            createRecipeUseCase.invoke(recipe)
        }
    }*/

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
        //listRecipe.value = getRecipeUseCase.getAllRecipe(query)

        /*val retrofit = Retrofit.Builder()
            .baseUrl(URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RecipeApiService::class.java)

        api.getSearchResult(API_KEY, query).enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    listRecipe.value = response.body()?.results?: arrayListOf()
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                showError()
            }
        })*/
    }

    /*private fun showError() {
        println("API ERROR")
    }*/

}