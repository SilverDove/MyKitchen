package com.example.mykitchen.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.domain.entity.Recipe
import com.example.mykitchen.domain.usecase.AddRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel (
    private val addRecipeUseCase: AddRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase//Koin injection
) : ViewModel() {

    var listFavoriteRecipe: MutableLiveData<List<Recipe>> = MutableLiveData()
    var recipeURL: MutableLiveData<String> = MutableLiveData()

    fun getAllRecipeFromDB() {
        //we go to a thread for background process to get all recipes from the Room database
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getAllRecipeFromDB()
            //Go back to main thread when we want tp update the list of favorite recipes
            withContext(Dispatchers.Main) {
                listFavoriteRecipe.value = response
            }
        }
    }

    fun getRecipeURL(id: Int) {
        //we go to a thread for background process to get the url of the selected recipe
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getRecipeURL(id)
            //Go back to main thread when we want to update the current url
            withContext(Dispatchers.Main){
                recipeURL.value = response
            }
        }
    }

    fun deleteAll() {
        //we go to a thread for background process to delete all the favorite recipes from the Room database
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase.removeAllFromDB()
            val response = getRecipeUseCase.getAllRecipeFromDB()
            //Go back to main thread when we want to update the list of favorite recipe
            withContext(Dispatchers.Main) {
                listFavoriteRecipe.value = response
            }
        }
    }
}
