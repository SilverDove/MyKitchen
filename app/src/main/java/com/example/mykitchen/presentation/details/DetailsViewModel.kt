package com.example.mykitchen.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mykitchen.data.local.models.toEntity
import com.example.mykitchen.domain.entity.RecipeDetails
import com.example.mykitchen.domain.usecase.AddRecipeUseCase
import com.example.mykitchen.domain.usecase.GetRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel (
    private val addRecipeUseCase: AddRecipeUseCase,
    private val getRecipeUseCase: GetRecipeUseCase//Koin injection
) : ViewModel(){

    var recipeDetails: MutableLiveData<RecipeDetails> = MutableLiveData()

    fun makeAPICall(recipeURL: String) {
        //we go to a thread for background process to do the API call
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecipeUseCase.getRecipeFromURL(recipeURL)
            //Go back to main thread when we want to update the details of the current recipe
            withContext(Dispatchers.Main){
                recipeDetails.value = response
            }
        }
    }

    fun addRecipe(recipe: RecipeDetails){
        //we go to a thread for background process to add the recipe into the room database
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase.addRecipeToDB(recipe.toEntity())
        }
    }

    fun deleteRecipe(recipe: RecipeDetails){
        //we go to a thread for background process to delete the recipe into the room database
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase.removeRecipeFromDB(recipe.toEntity())
        }
    }

    fun ifExist(idRecipe: Int) : LiveData<Int>{//Check whether the recipe is in the room database or not
            return getRecipeUseCase.ifRecipeExists(idRecipe)
    }

}